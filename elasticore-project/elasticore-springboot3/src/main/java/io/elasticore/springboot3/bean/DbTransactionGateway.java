package io.elasticore.springboot3.bean;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.elasticore.runtime.port.DbmsService;
import io.elasticore.springboot3.dbms.DbmsSqlExecutor;
import io.elasticore.springboot3.dbms.meta.SqlQueryInfo;
import io.elasticore.springboot3.util.ReflectUtils;
import jakarta.persistence.*;
import org.apache.commons.jexl3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Transaction Gateway Service for executing dynamic queries with multiple data sources.
 */
@Service
public class DbTransactionGateway implements DbmsSqlExecutor {
    private final EntityManagerFactory defaultEntityManagerFactory;
    private final Map<String, EntityManagerFactory> entityManagerFactories;


    @Autowired
    private ResourceLoader resourceLoader;

    private static ClassLoader classLoader;

    private static final JexlEngine jexl = new JexlBuilder().create();

    private static ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    private static Map<String, SqlQueryInfo> sqlQueryInfoMap = new HashMap<>();

    /**
     *
     */
    @Autowired
    public DbTransactionGateway(EntityManagerFactory defaultEntityManagerFactory) throws IOException {
        this.defaultEntityManagerFactory = defaultEntityManagerFactory;
        this.entityManagerFactories = new HashMap<>();
        this.entityManagerFactories.put("primary", defaultEntityManagerFactory);
    }



    /**
     * for multi datasource
     */
    @Autowired(required = false)
    public void setEntityManagerFactories(Map<String, EntityManagerFactory> entityManagerFactories) throws IOException {
        if (entityManagerFactories != null && !entityManagerFactories.isEmpty()) {
            for (Map.Entry<String, EntityManagerFactory> entry : entityManagerFactories.entrySet()) {
                String key = entry.getKey();
                EntityManagerFactory value = entry.getValue();

                this.entityManagerFactories.put(key, value);

                if (key.endsWith("EntityManagerFactory")) {
                    String simplifiedKey = key.substring(0, key.length() - "EntityManagerFactory".length());
                    if (!simplifiedKey.isEmpty()) {
                        this.entityManagerFactories.put(simplifiedKey, value);
                    }
                }
            }
        }
    }



    @Override
    @Transactional(readOnly = true)
    public <I, O> List<O> inqueryList(DbmsService dbmsSvcMeta, String methodName, I input, Class<O> outputType) throws IOException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        String processId = dbmsSvcMeta.id() + "." + methodName;
        String datasource = dbmsSvcMeta.datasource();
        return executeQuery(dbmsSvcMeta, methodName, input, outputType, datasource);
    }


    @Override
    @Transactional(readOnly = true)
    public <I, O> Page<O> inqueryPage(DbmsService dbmsSvcMeta, String methodName, I input, Class<O> outputType, Pageable pageable) throws IOException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        String processId = dbmsSvcMeta.id() + "." + methodName;
        String datasource = dbmsSvcMeta.datasource();
        return executePagedQuery(dbmsSvcMeta, methodName, input, outputType, pageable, datasource, true);
    }


    @Override
    @Transactional(readOnly = true)
    public <I, O> O inqueryFirst(DbmsService dbmsSvcMeta, String methodName, I input, Class<O> outputType) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String processId = dbmsSvcMeta.id() + "." + methodName;
        String datasource = dbmsSvcMeta.datasource();
        Pageable pageable = PageRequest.of(0, 1);
        Page<O> page = executePagedQuery(dbmsSvcMeta, methodName, input, outputType, pageable, datasource, false);

        return page.stream().findFirst().orElse(null);
    }


    @Transactional
    public int executeUpdate(DbmsService dbmsSvcMeta, String methodName, Object input) throws IOException, InvocationTargetException, NoSuchMethodException {
        String dataSource = dbmsSvcMeta.datasource();

            EntityManager entityManager = getEntityManager(dataSource);
            SqlQueryInfo entry = findSqlQueryInfo(dbmsSvcMeta,methodName);
            if (entry == null) {
                throw new RuntimeException("Process ID not found: " + entry.getId());
            }

            String processedQuery = processConditionalQuery(entry.getSql(), input);
            Query query = entityManager.createNativeQuery(processedQuery);
            bindQueryParameters(query, processedQuery, input);
            return query.executeUpdate();

    }

    protected String extractSortCode(Object input) {
        if (input == null) return null;

        String sortColumnNm= null;
        boolean sortAscending = true;

        if (input instanceof Map<?, ?> map) {
            Object sortVal = map.get("sortCode");
            if(sortVal != null)
                return sortVal.toString();

            sortVal = map.get("sortColumn");
            if(sortVal!=null)
                sortColumnNm = sortVal.toString();
         }

        Class c= input.getClass();
        try {
            Method method = c.getMethod("getSortCode");
            Object result = method.invoke(input);
            if(result!=null)
                return result.toString();
        } catch (Exception e) { }

        try {
            Method method = c.getMethod("getSortColumn");
            Object result = method.invoke(input);
            if(result!=null) {
                sortColumnNm = result.toString();

                Method method2 = c.getMethod("getSortAscending");
                Object result2 = method2.invoke(input);
                if (Boolean.FALSE == result2) {
                    return sortColumnNm + "-";
                }

                return sortColumnNm + "+";
            }

        } catch (Exception e) { }

        return null;
    }


    private String appendSortClause(String sql, Object input) {
        String sortCode = extractSortCode(input);
        if (sortCode == null || sortCode.isBlank()) return sql;

        //   title+,name --> title ASC, name DESC
        String orderByClause = Arrays.stream(sortCode.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(sort -> {
                    String field = sort.substring(0, sort.length() - 1);
                    char directionSymbol = sort.charAt(sort.length() - 1);
                    String direction = (directionSymbol == '-') ? "DESC" : "ASC";
                    return field + " " + direction;
                })
                .collect(Collectors.joining(", "));

        if (orderByClause.isEmpty()) return sql;

        return sql + " ORDER BY " + orderByClause;
    }


    @Transactional(readOnly = true)
    public <I, O> List<O> executeQuery(DbmsService dbmsSvcMeta, String methodName, I input, Class<O> outputType, String dataSource) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, IOException {

            EntityManager entityManager = getEntityManager(dataSource);

            //ProcessConfig config = loadProcessConfig("process");
            //ProcessEntry entry = config.getProcessById(processId);
            //ProcessEntry entry = getProcessEntry(processId);
            SqlQueryInfo entry = findSqlQueryInfo(dbmsSvcMeta,methodName);

            if (entry == null) {
                throw new RuntimeException("Process ID not found: " + entry.getId());
            }

            String processedQuery = processConditionalQuery(entry.getSql(), input);

            Query query;
            if (entry.getNativeQuery()) {
                query = entityManager.createNativeQuery(processedQuery, Tuple.class);
            } else {
                query = entityManager.createQuery(processedQuery, Tuple.class);
            }

            bindQueryParameters(query, processedQuery, input);
            List<Tuple> resultList = query.getResultList();

            if (Map.class.isAssignableFrom(outputType)) {
                return (List<O>) resultList.stream()
                        .map(this::convertTupleToCamelCaseMap)
                        .collect(Collectors.toList());
            } else {
                return (List<O>) resultList.stream()
                        .map(tuple -> convertTupleToDto(tuple, outputType))
                        .collect(Collectors.toList());
            }

    }


    /**
     *
     * @param dbmsSvcMeta
     * @param methodName
     * @param input
     * @param outputType
     * @param pageable
     * @param dataSource
     * @param checkTotalCount
     * @return
     * @param <I>
     * @param <O>
     */
    @Transactional(readOnly = true)
    public <I, O> Page<O> executePagedQuery(DbmsService dbmsSvcMeta, String methodName, I input, Class<O> outputType, Pageable pageable, String dataSource, boolean checkTotalCount) throws IOException, RuntimeException, InvocationTargetException, NoSuchMethodException {

            EntityManager entityManager = getEntityManager(dataSource);
            SqlQueryInfo entry = findSqlQueryInfo(dbmsSvcMeta,methodName);
            if (entry == null) {
                throw new RuntimeException("Process ID not found: " + entry.getId());
            }
            
            String processedQuery = processConditionalQuery(entry.getSql(), input);

            int totalRows = 0;
            if(checkTotalCount) {
                String countQueryStr = "SELECT COUNT(*) FROM (" + processedQuery + ") AS X";
                Query countQuery = entityManager.createNativeQuery(countQueryStr);
                bindQueryParameters(countQuery, processedQuery, input);
                totalRows = ((Number) countQuery.getSingleResult()).intValue();
            }


            Query query;
            if (entry.getNativeQuery()) {
                query = entityManager.createNativeQuery(processedQuery, Tuple.class);
            } else {
                query = entityManager.createQuery(processedQuery, Tuple.class);
            }
            bindQueryParameters(query, processedQuery, input);

            query.setFirstResult((int) pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
            List<Tuple> resultList = query.getResultList();

            if (!checkTotalCount && resultList != null) {
                totalRows = resultList.size();
            }

            List<O> mappedResults;
            if (outputType == Map.class) {
                mappedResults = (List<O>) resultList.stream()
                        .map(this::convertTupleToCamelCaseMap)
                        .collect(Collectors.toList());
            } else {
                mappedResults = (List<O>) resultList.stream()
                        .map(tuple -> convertTupleToDto(tuple, outputType))
                        .collect(Collectors.toList());
            }

            return new PageImpl<>(mappedResults, pageable, totalRows);
    }


    public static boolean isValidJavaIdentifier(String str) {
        return str.matches("[a-zA-Z_$][a-zA-Z\\d_$]*");
    }

    private String processConditionalQuery(String query, Object input) {
        int chk1 = query.indexOf("${");
        int chk2 = query.indexOf("/* if");

        if(chk1>0) {
            try {
                query = replaceQueryParameters(query, input);
            }catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        if(chk2<0) {
            return query;
        }


        String[] lines = query.split("\n");
        StringBuilder processedQuery = new StringBuilder();


        boolean isBlockScope = false;
        boolean isBlockCondition = false;
        for (String line : lines) {
            if (isBlockScope) {
                if (line.indexOf("/*") >= 0 && line.indexOf("if-end") > 0) {
                    isBlockScope = false;
                } else if (isBlockCondition) {
                    processedQuery.append(line).append("\n");
                }
                continue;
            }

            Matcher matcher = Pattern.compile("/\\* if:(.*?) \\*/").matcher(line);
            boolean conditionMet = false;

            if (matcher.find()) {
                String condition = matcher.group(1).trim();



                if (isValidJavaIdentifier(condition)) {
                    String paramName = condition;
                    conditionMet = input instanceof Map ? ((Map<?, ?>) input).containsKey(paramName) && ((Map<?, ?>) input).get(paramName) != null : hasValue(input, paramName);
                } else {
                    conditionMet = evaluateCondition(condition, input);
                }

                isBlockCondition = false;
                if (line.trim().indexOf("/*") == 0) {
                    isBlockScope = true;
                    isBlockCondition = conditionMet;
                }
                if (!conditionMet) {
                    continue;
                }

            }
            if (!isBlockScope) {
                if (conditionMet) {
                    int p = line.indexOf("/*");
                    line = line.substring(0, p - 1);
                }
                processedQuery.append(line).append("\n");
            }
        }

        //return processedQuery.toString().trim();
        // apply sort
        return appendSortClause(processedQuery.toString(), input);

    }

    private boolean evaluateCondition(String condition, Object input) {
        try {
            JexlContext context = new MapContext();
            context.set("input", input);
            JexlExpression expression = jexl.createExpression(condition);
            return (boolean) expression.evaluate(context);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean hasValue(Object input, String paramName) {
        try {
            String getterMethod = "get" + Character.toUpperCase(paramName.charAt(0)) + paramName.substring(1);
            Method method = input.getClass().getMethod(getterMethod);
            Object value = method.invoke(input);
            if (value != null && value instanceof String) {
                String txtVal = (String) value;
                return !txtVal.isEmpty();
            }
            return value != null;
        } catch (Exception e) {
            return false;
        }
    }

    private EntityManager getEntityManager(String dataSource) {
        if (dataSource == null || dataSource.isEmpty()) {
            dataSource = "primary";
        }

        EntityManagerFactory emf = entityManagerFactories.getOrDefault(dataSource, defaultEntityManagerFactory);
        return EntityManagerFactoryUtils.getTransactionalEntityManager(emf);
    }

    private Map<String, Object> convertTupleToCamelCaseMap(Tuple tuple) {
        Map<String, Object> map = new LinkedHashMap<>();
        tuple.getElements().forEach(e -> {
            String camelCaseKey = toCamelCase(e.getAlias());
            map.put(camelCaseKey, tuple.get(e));
        });
        return map;
    }

    private <O> O convertTupleToDto(Tuple tuple, Class<O> outputType) {
        try {
            O dto = outputType.getDeclaredConstructor().newInstance();
            for (TupleElement<?> element : tuple.getElements()) {
                String colName = element.getAlias();
                String fieldName = toCamelCase(colName);
                Field field = ReflectUtils.getField(outputType, fieldName);
                if(field==null) field = ReflectUtils.getField(outputType, colName);
                if (field != null) {
                    Object value = tuple.get(element);
                    Object convertedValue = convertValue(value, field.getType());
                    field.set(dto, convertedValue);
                }
            }
            return dto;
        } catch (Exception e) {
            throw new RuntimeException("Failed to map tuple to DTO: " + outputType.getSimpleName(), e);
        }
    }


    private Object convertValue(Object value, Class<?> targetType) {
        if (value == null) return null;

        Class<?> sourceType = value.getClass();

        if (targetType.isAssignableFrom(sourceType)) {
            return value;
        }

        // Number
        if (value instanceof Number num) {
            if (targetType == Long.class || targetType == long.class) return num.longValue();
            if (targetType == Integer.class || targetType == int.class) return num.intValue();
            if (targetType == Double.class || targetType == double.class) return num.doubleValue();
            if (targetType == Float.class || targetType == float.class) return num.floatValue();
            if (targetType == Short.class || targetType == short.class) return num.shortValue();
            if (targetType == Byte.class || targetType == byte.class) return num.byteValue();
        }

        // Boolean
        if (targetType == Boolean.class || targetType == boolean.class) {
            if (value instanceof Boolean b) return b;
            if (value instanceof String s) return Boolean.parseBoolean(s);
            if (value instanceof Number n) return n.intValue() != 0;
        }

        // java.sql.Date -> java.time.LocalDate
        if (value instanceof java.sql.Date sqlDate && targetType == java.time.LocalDate.class) {
            return sqlDate.toLocalDate();
        }

        // java.sql.Timestamp -> LocalDateTime or Instant
        if (value instanceof java.sql.Timestamp timestamp) {
            if (targetType == java.time.LocalDateTime.class) {
                return timestamp.toLocalDateTime();
            } else if (targetType == java.time.Instant.class) {
                return timestamp.toInstant();
            }
        }

        // java.sql.Time -> LocalTime
        if (value instanceof java.sql.Time sqlTime && targetType == java.time.LocalTime.class) {
            return sqlTime.toLocalTime();
        }


        if (targetType == String.class) {
            return value.toString();
        }

        throw new IllegalArgumentException(
                "Cannot convert value of type " + sourceType.getName() + " to " + targetType.getName()
        );
    }



    private String replaceQueryParameters(String sql, Object input) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Pattern pattern = Pattern.compile("\\$\\{(\\w+)}");
        Matcher matcher = pattern.matcher(sql);
        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            String paramName = matcher.group(1);
            Object value;

            if (input instanceof Map) {
                value = ((Map<?, ?>) input).get(paramName);
            } else {
                String getterMethod = "get" + Character.toUpperCase(paramName.charAt(0)) + paramName.substring(1);
                Method method = input.getClass().getMethod(getterMethod);
                value = method.invoke(input);
            }

            matcher.appendReplacement(result, Matcher.quoteReplacement(value != null ? value.toString() : ""));
        }

        matcher.appendTail(result);
        return result.toString();
    }


    /**
     *
     * @param query
     * @param sql
     * @param input
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    private void bindQueryParameters(Query query, String sql, Object input) throws NoSuchMethodException, InvocationTargetException {
        Pattern pattern = Pattern.compile("(?<!:):(\\w+)");
        Matcher matcher = pattern.matcher(sql);
        while (matcher.find()) {
            try {
                String paramName = matcher.group(1);
                Object value;
                if (input instanceof Map) {
                    value = ((Map<?, ?>) input).get(paramName);
                } else {
                    String getterMethod = "get" + Character.toUpperCase(paramName.charAt(0)) + paramName.substring(1);
                    Method method = input.getClass().getMethod(getterMethod);
                    value = method.invoke(input);
                }
                query.setParameter(paramName, value);
            }catch (IllegalAccessException iae) {}
        }
    }

    private static String getProjectRootDirectory() {
        try {
            Path currentPath = Paths.get("").toAbsolutePath();
            return currentPath.normalize().toString();
        } catch (Exception e) {
            throw new RuntimeException("Failed to determine project root directory", e);
        }
    }


    private ClassLoader getMainClassLoader() {
        if(classLoader!=null)
            return classLoader;

        for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
            try {
                Class<?> cls = Class.forName(element.getClassName());
                if (cls.isAnnotationPresent(SpringBootApplication.class)) {
                    classLoader = cls.getClassLoader();
                    break;
                }
            } catch (ClassNotFoundException e) {
            }

        }
        if(classLoader==null) {
            classLoader = getClass().getClassLoader();
        }
        return classLoader;
    }

    private String getNativeSqlId(String sqlSourcePath, String portSvcName, String methodName) {
        String id = sqlSourcePath+"!"+portSvcName+"."+methodName;
        return id;
    }

    private String getNativeSqlId(DbmsService dbmsSvcMeta, String methodName) {
        String idWithDomain = dbmsSvcMeta.id(); // with domain id;
        int p = idWithDomain.indexOf(".");

        String portSvcName = idWithDomain.substring(p+1);
        String id = dbmsSvcMeta.sqlSource()+"!"+portSvcName+"."+methodName;
        return id;
    }

    private String getBooleanStr(Object val, String defaultVal) {

        if(val instanceof String) {
            if("true".equals(val)) {
                return "true";
            }else if("false".equals(val)) {
                return "false";
            }
        }
        if(val instanceof Boolean) {
            Boolean b = (Boolean) val;
            if(Boolean.FALSE.equals(b)) {
                return "false";
            }else {
                return "true";
            }
        }

        return defaultVal;
    }

    private void setSqlQueryInfo(String sqlSourcePath, String portSvcName, Map<String, Map> values,long lastModifiedTime) {

        Map<String, Map> methods =  values.get("methods");
        methods.forEach((methodName, value) -> {

            String sqlTxt = (String) value.get("query");
            String nativeQuery = getBooleanStr(value.get("nativeQuery") , "true");

            String id = getNativeSqlId(sqlSourcePath, portSvcName, methodName);
            String ref = (String) value.get("ref");


            SqlQueryInfo q = new SqlQueryInfo();
            q.setId(id);
            q.setSql(sqlTxt);
            q.setNativeQuery(nativeQuery);
            q.setLastModifiedTime(lastModifiedTime);
            q.setRef(ref);

            sqlQueryInfoMap.put(id,q);
        });

    }

    private SqlQueryInfo findSqlQueryInfo(DbmsService dbmsSvcMeta,String methodName) throws IOException {

        String sqlSourcePath = dbmsSvcMeta.sqlSource();

        String sqlId = getNativeSqlId(dbmsSvcMeta, methodName);

        File sqlSourceFile=null;

        boolean isReloadable = false;
        InputStream is = null;


        try {
            // dev mode
            String devSrcPath = new File(getMainClassLoader().getResource(".").getFile())
                    .getParentFile().getParentFile().getParentFile().getParent()
                    + "/src/main/resources/" + sqlSourcePath;
            File devSrcFile = new File(devSrcPath);

            if (devSrcFile.exists() && devSrcFile.isFile()) {
                sqlSourceFile = devSrcFile;
                isReloadable = true;
                is = new FileInputStream(devSrcFile);
            } else {
                is = getMainClassLoader().getResourceAsStream(sqlSourcePath);
                if (is == null) {
                    throw new FileNotFoundException("resource not found in JAR: " + sqlSourcePath);
                }
                //sqlSourceFile = new File(Objects.requireNonNull(getMainClassLoader().getResource(sqlSourcePath)).getFile());
            }

            if (sqlSourceFile != null && !sqlSourceFile.exists()) {
                throw new FileNotFoundException("source file not found: " + sqlSourceFile.getAbsolutePath());
            }

            SqlQueryInfo sqlQueryInfo = sqlQueryInfoMap.get(sqlId);
            long lastModifiedTime = -1;

            if(sqlSourceFile!=null) {
                lastModifiedTime = sqlSourceFile.lastModified();
            }

            if (sqlQueryInfo != null && (!isReloadable || sqlQueryInfo.getLastModifiedTime() == lastModifiedTime)) {
                return sqlQueryInfo;

            }
            Map<String, Map> map = mapper.readValue(is, LinkedHashMap.class);
            Map<String, Map> port = map.get("port");

            long lastModifiedTime2 = lastModifiedTime;
            port.forEach((portSvcName, value) -> {
                //setSqlQueryInfo(String sqlSourcePath, String portSvcName, Map<String, Map> values,long lastModifiedTime)
                setSqlQueryInfo(sqlSourcePath, portSvcName, value, lastModifiedTime2);
            });

            SqlQueryInfo queryInfo = sqlQueryInfoMap.get(sqlId);
            if (queryInfo != null) {
                String ref = queryInfo.getRef();
                if (ref != null && !ref.isEmpty()) {
                    queryInfo = findSqlQueryInfo(dbmsSvcMeta, ref);
                }
            }

            return queryInfo;
        }finally {
            if(is!=null)
                is.close();
        }
    }


    private String toCamelCase(String columnName) {
        String[] parts = columnName.split("_");
        StringBuilder camelCaseString = new StringBuilder(parts[0].toLowerCase());
        for (int i = 1; i < parts.length; i++) {
            camelCaseString.append(Character.toUpperCase(parts[i].charAt(0))).append(parts[i].substring(1).toLowerCase());
        }
        return camelCaseString.toString();
    }
}
