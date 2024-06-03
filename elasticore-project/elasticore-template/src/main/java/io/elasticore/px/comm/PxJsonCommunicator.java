package io.elasticore.px.comm;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import kr.co.kblife.cbh.jps.config.log.PacketLogUtil;
import kr.co.kblife.cbh.jps.extention.exception.BizProcessException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.NoOpCacheManager;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import javax.xml.bind.annotation.XmlElement;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.*;

@RequiredArgsConstructor
@Component
@Slf4j
public class PxJsonCommunicator {

    @Value("${px.ip}")
    String PX_IP;
    @Value("${px.port}")
    int PX_PORT;

    @Value("${px.jsonPacketDir:}")
    private String jsonDebugPacketDir;

    private final WebClient webClient; // WebClient 주입
    //private static RestTemplate restTemplate;

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        //objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS,true);
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, false);
    }

    @Autowired(required = false)
    private CacheManager cacheManager;

    @Autowired
    private PacketLogUtil packetLogUtil;


    /**
     * 해당 class에서 사용할 기본 Map 구현체
     * field 순서 때문에 LinkedHashMap 를 사용
     *
     * @return
     */
    private Map getDefaultMap() {
        return new LinkedHashMap();
    }


    /**
     * JSON 데이터 구조중 'deploymentReference' 부분
     *
     * @return
     */
    private Map getDeploymentReferenceMap() {
        Map resp = getDefaultMap();
        resp.put("name", "JavaPXCalculationDeployment");
        resp.put("versionSelector", "last");
        return resp;
    }


    /**
     * JSON 데이터 (입력)구조중 'calculationData' 부분
     *
     * @param obj
     * @return
     */
    @SneakyThrows
    private Map getCalculationData(Object obj) {
        Map result = parse(obj, true);
        Map resp = getDefaultMap();

        String clsName = obj.getClass().getSimpleName();
        String keyName = clsName.replaceAll("Request", "");
        resp.put(keyName, result);
        return resp;
    }

    /**
     * JSON 데이터 구조중 'calculations' 부분
     *
     * @param respObj
     * @return
     */
    @SneakyThrows
    private List getCalculations(Object respObj) {
        Map result = parse(respObj, false);
        Map resp = getDefaultMap();

        String clsName = respObj.getClass().getSimpleName();
        String keyName = clsName.replaceAll("Request", "");
        resp.put(keyName, result);


        List list = new ArrayList();
        Map basket = getDefaultMap();
        basket.put("instances", resp);

        list.add(basket);

        return list;
    }


    // 원복용
    private RestTemplate getRestTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(3 * 60 * 1000);
        factory.setReadTimeout(10 * 60 * 1000);

        return new RestTemplate(factory);
    }

/*
    private RestTemplate getRestTemplate() {
        if(restTemplate != null) {
            return restTemplate;
        }

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(3*60*1000);
        factory.setReadTimeout(10 *60 *1000);


        HttpClient httpClient = HttpClientBuilder.create()
                .setMaxConnTotal(200)
                .setMaxConnPerRoute(100)
                .build();

        factory.setHttpClient(httpClient);

        RestTemplate newRestTemplate = new RestTemplate(factory);;
        restTemplate = newRestTemplate;
        return restTemplate;
    }
*/

    /**
     * WebClient 호출 방식 (PX의 JSON endpoint로 데이터 전송하여 Map 형태로 반환 받음.)
     *
     * @param jsonTxt
     * @return
     */
    protected Map callPxServiceByJSON_webclient(String jsonTxt) throws URISyntaxException, RestClientException {

        HashMap resultMap = null;

        String resultString = webClient.mutate()
                .baseUrl("http://" + this.PX_IP + ":" + this.PX_PORT)
                .build()
                .post()
                .uri("/v1/calculation")
                .bodyValue(jsonTxt)
                .accept(MediaType.APPLICATION_JSON)
                .header("Content-type", "application/json; charset=UTF-8;")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // jmeter목적 request 구문추출
        if (packetLogUtil.isEnable()) {
            try {
                resultMap = objectMapper.readValue(resultString, HashMap.class);

                packetLogUtil.log(jsonTxt, resultString, "json");
            } catch (JsonProcessingException npe) {

                log.error("parsing error");
            }
        } else {
            try {
                resultMap = objectMapper.readValue(resultString, HashMap.class);
            } catch (JsonProcessingException npe) {
                log.error("parsing error");
            }
        }

        if (resultMap != null && resultMap.containsKey("error")) {
            log.error("PX response error ");
        }


        return resultMap;
    }

    /**
     * RestTemplete 호출 방식 (PX의 JSON endpoint로 데이터 전송하여 Map 형태로 반환 받음.)
     *
     * @param jsonTxt
     * @return
     */
    protected Map callPxServiceByJSON(String jsonTxt) throws URISyntaxException, RestClientException {


        HashMap resultMap = null;

        RestTemplate restTemplate = getRestTemplate();

        URI uri = new URI("http://" + this.PX_IP + ":" + this.PX_PORT + "/v1/calculation");

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));


        // jmeter목적 request 구문추출
        if (packetLogUtil.isEnable()) {
            ResponseEntity<String> responseEntity =
                    restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<String>(jsonTxt, headers), String.class);

            String respJsonTxt = responseEntity.getBody();
            packetLogUtil.log(jsonTxt, respJsonTxt, "json");

            //JsonProcessingException, JsonMappingException
            try {
                resultMap = objectMapper.readValue(respJsonTxt, HashMap.class);

            } catch (JsonProcessingException npe) {

                log.error("parsing error");
            }

        } else {

            ResponseEntity<HashMap> responseEntity =
                    restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<String>(jsonTxt, headers), HashMap.class);

            resultMap = responseEntity.getBody();
        }

        if (resultMap != null && resultMap.containsKey("error")) {
            log.error("PX response error ");
        }


        return resultMap;


    }


    /**
     * 메인 method
     *
     * @param requestObject
     * @param responseObject
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T executePXCalculator(Object requestObject, T responseObject) throws RuntimeException {

        long createXmlStart = System.currentTimeMillis();

        Map reqJsonMap = getDefaultMap();
        reqJsonMap.put("deploymentReference", getDeploymentReferenceMap());
        reqJsonMap.put("calculationData", getCalculationData(requestObject));
        reqJsonMap.put("calculations", getCalculations(responseObject));


        String jsonTxt = "";
        try {
            jsonTxt = objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(reqJsonMap);
        } catch (JsonProcessingException jpe) {
            throw new RuntimeException("REST.ERROR");
        }


        Cache cache = null;
        if (cacheManager != null && !(cacheManager instanceof NoOpCacheManager)) {
            cache = cacheManager.getCache("px.java.json.cache");
        }


        String key = null;
        if (cache != null) {
            key = requestObject.getClass().getSimpleName() + "_" + jsonTxt.length() + ":" + jsonTxt.hashCode();
            Cache.ValueWrapper wrapper = cache.get(key);
            if (wrapper != null) {
                log.info("HIT_CACHE :::::: " + key);
                return (T) wrapper.get();
            }

        }

        //System.err.println(jsonTxt);
        //saveFile(jsonTxt);


        try {
            Map respJsonMap = callPxServiceByJSON(jsonTxt);
            Map errorMap = null;
            if (respJsonMap.get("error") instanceof Map)
                errorMap = (Map) respJsonMap.get("error");

            // error가 있는 경우
            if (errorMap != null) {
                String errorId = null;
                if (errorMap.get("id") instanceof String)
                    errorId = (String) errorMap.get("id");

                String erroMsg = null;
                if (errorMap.get("message") instanceof String)
                    erroMsg = (String) errorMap.get("message");


                if (errorId == null || errorId.length() == 0)
                    errorId = "UNDEFINED";
                if (erroMsg == null || erroMsg.length() == 0)
                    erroMsg = "UNDEFINED";

                throw new BizProcessException(errorId, erroMsg);
            }

            List calculationsList = null;
            if (respJsonMap.get("calculations") instanceof List)
                calculationsList = (List) respJsonMap.get("calculations");

            Map calcResultMap = null;
            if (calculationsList != null && calculationsList.get(0) instanceof Map)
                calcResultMap = (Map) calculationsList.get(0);

            Map bodyMap = null;
            if (calcResultMap != null && calcResultMap.get("results") instanceof Map)
                bodyMap = (Map) calcResultMap.get("results");


            Object targetNewInstance = responseObject.getClass().newInstance();

            Object resultObj = parse(bodyMap, null, targetNewInstance);

            if (cache != null) {
                // error 가 없는 경우만 cache
                cache.put(key, resultObj);
            }


            return (T) resultObj;
        } catch (BizProcessException bpe) {
            throw bpe;
        } catch (RestClientException rce) {
            log.error("RestClientException error ", rce);
            throw new BizProcessException("REST.ERROR", "Communication error.");
        } catch (URISyntaxException uri) {
            throw new BizProcessException("REST.ERROR", "URI error.");
        } catch (NullPointerException npe) {
            throw new BizProcessException("PARSER.ERR", "Parsing error");
        } catch (Exception e) {
            log.error("error ", e);
            throw new BizProcessException("JAVAPX.ERR", "Runtime Exception");
        } finally {
            //log.info("request [JSON] parsing End ::::::::::::::::  " + (System.currentTimeMillis()-createXmlStart) );
        }
    }

    /**
     * @param field
     * @return
     */
    public Class getFieldGenericType(Field field) {
        if (ParameterizedType.class.isAssignableFrom(field.getGenericType().getClass())) {
            ParameterizedType genericType = (ParameterizedType) field.getGenericType();

            Type[] types = genericType.getActualTypeArguments();

            Class c = ((Class) (types[0]));

            return c;
        }
        return field.getType();
    }

    /**
     * @param field
     * @return
     */
    public String getFieldGenericTypeName(Field field) {
        return getFieldGenericType(field).getSimpleName();
    }

    /**
     * @param requestObject
     * @param isRequest
     * @return
     * @throws Exception
     */
    public List parse(List requestObject, boolean isRequest) throws Exception {
        ArrayList list = new ArrayList();

        for (Object item : requestObject) {
            Map result = parse(item, isRequest);
            list.add(result);
        }

        return list;
    }

    /**
     * @param requestObject
     * @param isRequest
     * @return
     * @throws Exception
     */
    public Map parse(Object requestObject, boolean isRequest) throws Exception {

        if (requestObject == null) return null;


        Map result = getDefaultMap();

        Class curObjClass = requestObject.getClass();


        Map featureMap = getDefaultMap();
        Map linksMap = getDefaultMap();

        for (Field field : curObjClass.getDeclaredFields()) {

            field.setAccessible(true);

            String keyName = field.getName();
            // ucoverage검색 예외문
            if ("$jacocoData".equals(keyName)) continue;

            // ucoverage검색 예외문
            if ("$jacocoData".equals(keyName)) continue;


            //String fieldTypeName = field.getType().getName();
            //if ("$jacocoData".equals(fieldTypeName))	continue;

            Object val = field.get(requestObject);

            boolean isTupleType = isTupleType(field);
            if (val == null) {
                if (isRequest) continue;

                if (List.class.isAssignableFrom(field.getType()) && !isTupleType) {
                    val = new ArrayList<>();
                } else {
                    val = new HashMap<>(); // empty object
                }
            }

            if (isRequest && val == null) continue;

            if (field.getType().isPrimitive() || field.getType().getName().indexOf("java.lang") == 0) {

                featureMap.put(keyName, val);
            } else if (isTupleType) {

                if (!isRequest) {
                    featureMap.put(keyName, new HashMap<>());
                } else {
                    featureMap.put(keyName, val);
                }

            } else {
                String listKeyName = null;
                Object parseVal = null;
                if (val instanceof List) {
                    List list = (List) val;
                    listKeyName = this.getFieldGenericTypeName(field);

                    Map basket = new HashMap();

                    if (list == null || list.size() == 0) {
                        parseVal = null;
                    } else if (list.size() == 1) {
                        Object x = list.get(0);
                        if (x != null) {
                            basket.put(listKeyName, parse(x, isRequest));
                            parseVal = basket;
                        }
                    } else {
                        basket.put(listKeyName, parse(list, isRequest));
                        parseVal = basket;

                    }

                } else if (val != null) {
                    listKeyName = field.getType().getSimpleName();

                    Map basket = new HashMap();
                    basket.put(listKeyName, parse(val, isRequest));
                    parseVal = basket;

                }

                if (parseVal != null)
                    linksMap.put(listKeyName, parseVal);
            }

        }

        if (featureMap.size() > 0) {
            result.put("features", featureMap);
        }

        if (linksMap.size() > 0) {
            result.put("links", linksMap);
        }

        return result;
    }


    /**
     * @param field
     * @return
     */
    private boolean isTupleType(Field field) {
        return PxTupleList.isExists(field.getName());
    }


    /**
     * <pre>
     *     "CBHPolicy": {
     *         "features": {
     *
     *         },
     *         "links": {
     *             "CBHRider": {
     *
     *             }
     *
     *         }
     *
     *     }
     * </pre>
     *
     * @param resultMap
     * @return
     */
    protected Object parse(Map<String, Object> resultMap, String mainKeyName, Object targetObj) {
        String firstKey = resultMap.keySet().iterator().next();
        if (mainKeyName == null) {
            mainKeyName = firstKey;
        }

        if (targetObj == null) {
            targetObj = getTargetObject(mainKeyName);
        }
        Class targetObjClass = targetObj.getClass();
        Field[] fields = targetObjClass.getFields();

        // List 또는 Map 될수 있음.
        Object bodyObj = resultMap.get(mainKeyName);


        if (bodyObj instanceof List) {
            List<Map> bodyItemList = (List) bodyObj;

            List returnList = new ArrayList();
            try {
                for (Map bodyItem : bodyItemList) {
                    Object itemObj = targetObjClass.newInstance();
                    parseBody(bodyItem, itemObj);
                    returnList.add(itemObj);
                }
            } catch (RuntimeException e1) {
                log.error("{}", e1.getCause());
            } catch (Throwable e) {
                log.error("{}", e.getCause());
            }
            return returnList;
        }

        Map bodyMap = (Map) bodyObj;

        parseBody(bodyMap, targetObj);

        return targetObj;
    }


    private static Map<Field, String> keyNameMap = new HashMap<>();

    /**
     * DTO 에 정의딘 필드명을 기본으로 JSON 키값으로 사용
     * 아래 순서에 따른 JSON 키값을 찾는다.
     * JsonProperty -> XmlElement-> 필드명
     *
     * @param f
     * @return
     */
    private String getKeyName(Field f) {


        String keyNm = keyNameMap.get(f);
        if (keyNm != null) return keyNm;

        JsonProperty jsonProperty = f.getAnnotation(JsonProperty.class);
        if (jsonProperty != null) {
            keyNm = jsonProperty.value();
        }
        if (keyNm != null) {
            keyNameMap.put(f, keyNm);
            return keyNm;
        }

        XmlElement xmlElement = f.getAnnotation(XmlElement.class);
        if (xmlElement != null) {
            keyNm = xmlElement.name();
        }

        if (keyNm != null) {
            keyNameMap.put(f, keyNm);
            return keyNm;
        }

        return f.getName();
    }

    /**
     * @param val
     * @param field
     * @return
     */
    private Object getTransformType(Object val, Field field) {

        if (val != null) {
            // String
            if (field.getType() == String.class) {

                if (val instanceof String) {
                } else {
                    val = val.toString();
                }
            } else if (field.getType() == Double.class) {
                if (val instanceof Double) {
                } else {
                    val = Double.valueOf(val.toString());
                }
            } else if (field.getType() == Integer.class) {
                if (val instanceof Integer) {
                } else {
                    val = Integer.valueOf(val.toString());
                }
            }
        }

        return val;
    }


    /**
     * @param bodyMap
     * @param targetObj
     * @return
     */
    protected Object parseBody(Map bodyMap, Object targetObj) {

        Class targetObjClass = targetObj.getClass();
        Field[] fields = targetObjClass.getFields();

        Map featuresMap = null;
        if (bodyMap.get("features") instanceof Map) {
            featuresMap = (Map) bodyMap.get("features");
        }

        Map linksMap = null;
        if (bodyMap.get("links") instanceof Map) {
            linksMap = (Map) bodyMap.get("links");
        }

        if (featuresMap != null) {

            //Iterator<String> keys = featuresMap.keySet().iterator();
            //while (keys.hasNext())
            for (Field field : fields) {
                //String keyNm = keys.next();
                String keyNm = getKeyName(field);

                // features
                if (featuresMap.containsKey(keyNm)) {
                    try {
                        //Field field = targetObjClass.getField(keyNm);

                        Object val = featuresMap.get(keyNm);

                        if (field.getType().isPrimitive() || field.getType().getName().indexOf("java.lang.") == 0) {

                        } else if (List.class.isAssignableFrom(field.getType())) {
                            Class genericType = getFieldGenericType(field);
                            List objectList = new ArrayList();
                            List<Map> list = (List<Map>) val;
                            for (Map item : list) {
                                Object resultItem = parse(item, genericType);
                                objectList.add(resultItem);
                            }
                            val = objectList;
                        }

                        if (val != null) {
                            val = getTransformType(val, field);
                            field.set(targetObj, val);
                        }


                    } catch (IllegalArgumentException e) {
                        continue;
                    } catch (Throwable e) {
                        continue;
                    }
                }
                // links
                else if (linksMap != null && linksMap.containsKey(keyNm)) {
                    Object linkItemResult = linksMap.get(keyNm);

                    try {
                        // List 형태인지
                        if (List.class.isAssignableFrom(field.getType())) {

                            Class genericType = this.getFieldGenericType(field);

                            Object itemObj = genericType.newInstance();

                            List fieldValList = new ArrayList();
                            if (linkItemResult instanceof List) {

                                List<Map> linkItemList = (List) linkItemResult;
                                for (Map item : linkItemList) {
                                    Object parsedObj = parse(item, keyNm, itemObj);
                                    fieldValList.add(parsedObj);
                                }
                            } else {

                                Map linkItemMap = (Map) linkItemResult;
                                Object parsedObj = parse(linkItemMap, keyNm, itemObj);

                                if (parsedObj instanceof List) {
                                    fieldValList = (List) parsedObj;
                                } else {
                                    fieldValList.add(parsedObj);
                                }

                            }

                            field.set(targetObj, fieldValList);

                        } else {
                            Map linkItemMap = (Map) linkItemResult;
                            Object parsedObj = parse(linkItemMap, keyNm, field.getType().newInstance());

                            if (parsedObj instanceof List) {
                                List itemTmpList = (List) parsedObj;
                                if (itemTmpList != null) {
                                    field.set(targetObj, itemTmpList.get(0));
                                }

                            } else {
                                field.set(targetObj, parsedObj);
                            }


                        }
                    } catch (IllegalArgumentException iae) {
                        log.error("{}", iae.getCause());
                    } catch (Throwable e) {
                        log.error("{}", e.getCause());
                    }
                }


            }

        }


        // 초기화
        /*
        for(Field f : fields) {

            if (List.class.isAssignableFrom(f.getType())) {

                try {
                    Object val = f.get(targetObj);


                    if (val == null) {
                        f.set(targetObj, new ArrayList<>());
                    }
                }catch (IllegalAccessException ie){

                }
            }
        }*/


        return targetObj;
    }

    /**
     * @param objClass
     * @param typeName
     * @return
     */
    private Field findType(Class objClass, String typeName) {
        Field[] fields = objClass.getFields();

        String keyNmUpper = typeName.toUpperCase();
        for (Field field : fields) {
            //if( typeName.equals(field.getType().getSimpleName()) )

            if (keyNmUpper.equals(field.getName().toUpperCase())) {
                return field;
            }
        }

        return null;
    }

    @SneakyThrows
    protected Object parse(Map map, Class typeClass) {
        Object targetObj = typeClass.newInstance();

        Field[] fields = typeClass.getFields();

        //Iterator<String> keys = map.keySet().iterator();
        //while (keys.hasNext())
        for (Field field : fields) {
            //String keyNm = keys.next();
            String keyNm = getKeyName(field);
            Object val = map.get(keyNm);
            if (val == null) continue;

            try {
                //Field field = typeClass.getField(keyNm);
                val = getTransformType(val, field);
                field.set(targetObj, val);
            } catch (ParsingException e1) {
                log.error("{}", e1.getCause());

            } catch (Throwable e) {
                log.error("{}", e.getCause());

            }
        }
        return targetObj;
    }


    @SneakyThrows
    protected Object getTargetObject(String keyName) {
        String clsNm = "kr.co.kblife.cbh.jps.calculation.dto.premium.response." + keyName;
        return Class.forName(clsNm).newInstance();
    }

}
