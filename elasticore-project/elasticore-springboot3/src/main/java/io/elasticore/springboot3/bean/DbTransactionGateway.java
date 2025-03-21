package io.elasticore.springboot3.bean;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.elasticore.runtime.port.DbmsService;
import io.elasticore.springboot3.dbms.DbmsSqlExecutor;
import io.elasticore.springboot3.dbms.meta.SqlQueryInfo;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
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
    public <I, O> List<O> inqueryList(DbmsService dbmsSvcMeta, String methodName, I input, Class<O> outputType) {
        String processId = dbmsSvcMeta.id() + "." + methodName;
        String datasource = dbmsSvcMeta.datasource();
        return executeQuery(dbmsSvcMeta, methodName, input, outputType, datasource);
    }


    @Override
    public <I, O> Page<O> inqueryPage(DbmsService dbmsSvcMeta, String methodName, I input, Class<O> outputType, Pageable pageable) {
        String processId = dbmsSvcMeta.id() + "." + methodName;
        String datasource = dbmsSvcMeta.datasource();
        return executePagedQuery(dbmsSvcMeta, methodName, input, outputType, pageable, datasource, true);
    }


    @Override
    public <I, O> O inqueryFirst(DbmsService dbmsSvcMeta, String methodName, I input, Class<O> outputType) {
        String processId = dbmsSvcMeta.id() + "." + methodName;
        String datasource = dbmsSvcMeta.datasource();
        Pageable pageable = PageRequest.of(0, 1);
        Page<O> page = executePagedQuery(dbmsSvcMeta, methodName, input, outputType, pageable, datasource, false);

        return page.stream().findFirst().orElse(null);
    }


    @Transactional
    public int executeUpdate(DbmsService dbmsSvcMeta, String methodName, Object input) {
        String dataSource = dbmsSvcMeta.datasource();
        try {
            EntityManager entityManager = getEntityManager(dataSource);
            SqlQueryInfo entry = findSqlQueryInfo(dbmsSvcMeta,methodName);
            if (entry == null) {
                throw new RuntimeException("Process ID not found: " + entry.getId());
            }

            String processedQuery = processConditionalQuery(entry.getSql(), input);
            Query query = entityManager.createNativeQuery(processedQuery);
            bindQueryParameters(query, processedQuery, input);
            return query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    @Transactional(readOnly = true)
    public <I, O> List<O> executeQuery(DbmsService dbmsSvcMeta, String methodName, I input, Class<O> outputType, String dataSource) {
        try {
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

            if (outputType == Map.class) {
                return (List<O>) resultList.stream()
                        .map(this::convertTupleToCamelCaseMap)
                        .collect(Collectors.toList());
            } else {
                return (List<O>) resultList.stream()
                        .map(tuple -> convertTupleToDto(tuple, outputType))
                        .collect(Collectors.toList());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
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
    public <I, O> Page<O> executePagedQuery(DbmsService dbmsSvcMeta, String methodName, I input, Class<O> outputType, Pageable pageable, String dataSource, boolean checkTotalCount) {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
            return Page.empty();
        }
    }


    public static boolean isValidJavaIdentifier(String str) {
        return str.matches("[a-zA-Z_$][a-zA-Z\\d_$]*");
    }

    private String processConditionalQuery(String query, Object input) {
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
        return processedQuery.toString().trim();
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
                String fieldName = toCamelCase(element.getAlias());
                try {
                    Field field = outputType.getDeclaredField(fieldName);
                    field.setAccessible(true);
                    field.set(dto, tuple.get(element));
                } catch (NoSuchFieldException nsfe) {
                }
            }
            return dto;
        } catch (Exception e) {
            throw new RuntimeException("Failed to map tuple to DTO: " + outputType.getSimpleName(), e);
        }
    }

    private void bindQueryParameters(Query query, String sql, Object input) throws Exception {
        Pattern pattern = Pattern.compile(":(\\w+)");
        Matcher matcher = pattern.matcher(sql);
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
            query.setParameter(paramName, value);
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


            SqlQueryInfo q = new SqlQueryInfo();
            q.setId(id);
            q.setSql(sqlTxt);
            q.setNativeQuery(nativeQuery);
            q.setLastModifiedTime(lastModifiedTime);

            sqlQueryInfoMap.put(id,q);
        });

    }

    private SqlQueryInfo findSqlQueryInfo(DbmsService dbmsSvcMeta,String methodName) throws IOException {

        String sqlSourcePath = dbmsSvcMeta.sqlSource();

        String sqlId = getNativeSqlId(dbmsSvcMeta, methodName);

        File sqlSourceFile;

        boolean isReloadable = false;

        // dev mode
        String devSrcPath = new File(getMainClassLoader().getResource(".").getFile())
                .getParentFile().getParentFile().getParentFile().getParent()
                + "/src/main/resources/" + sqlSourcePath;
        File devSrcFile = new File(devSrcPath);

        if (devSrcFile.exists() && devSrcFile.isFile()) {
            sqlSourceFile = devSrcFile;
            isReloadable = true;
        } else {
            sqlSourceFile = new File(Objects.requireNonNull(getMainClassLoader().getResource(sqlSourcePath)).getFile());
        }

        if (!sqlSourceFile.exists() ) {
            throw new FileNotFoundException("source file not found: " + sqlSourceFile.getAbsolutePath());
        }

        SqlQueryInfo sqlQueryInfo = sqlQueryInfoMap.get(sqlId);
        long lastModifiedTime = sqlSourceFile.lastModified();
        if(sqlQueryInfo!=null && (!isReloadable || sqlQueryInfo.getLastModifiedTime() == lastModifiedTime) ) {
            return sqlQueryInfo;

        }
        Map<String, Map> map = mapper.readValue(sqlSourceFile, LinkedHashMap.class);
        Map<String, Map> port = map.get("port");

        port.forEach((portSvcName, value) -> {
            //setSqlQueryInfo(String sqlSourcePath, String portSvcName, Map<String, Map> values,long lastModifiedTime)
            setSqlQueryInfo(sqlSourcePath,portSvcName, value,lastModifiedTime);
        });

        return sqlQueryInfoMap.get(sqlId);
    }


    private String toCamelCase(String columnName) {
        String[] parts = columnName.split("_");
        StringBuilder camelCaseString = new StringBuilder(parts[0]);
        for (int i = 1; i < parts.length; i++) {
            camelCaseString.append(Character.toUpperCase(parts[i].charAt(0))).append(parts[i].substring(1));
        }
        return camelCaseString.toString();
    }
}
