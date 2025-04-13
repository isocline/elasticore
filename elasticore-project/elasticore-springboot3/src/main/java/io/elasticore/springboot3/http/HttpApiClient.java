package io.elasticore.springboot3.http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.elasticore.runtime.port.HttpAuthProvider;
import io.netty.channel.ChannelOption;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;
import java.net.URI;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class HttpApiClient {

    private static final Logger log = LoggerFactory.getLogger(HttpApiClient.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static WebClient createWebClient(String url, boolean ignoreSSL) {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
                .responseTimeout(Duration.ofMillis(20000))
                .doOnConnected(conn -> conn
                        .addHandlerLast(new ReadTimeoutHandler(20000, TimeUnit.MILLISECONDS))
                        .addHandlerLast(new WriteTimeoutHandler(10000, TimeUnit.MILLISECONDS)));

        if (ignoreSSL) {
            httpClient = httpClient.secure(sslContextSpec -> {
                try {
                    sslContextSpec.sslContext(SslContextBuilder.forClient()
                            .trustManager(InsecureTrustManagerFactory.INSTANCE).build());
                } catch (SSLException e) {
                    log.error(e.getMessage(), e);
                }
            });
        }

        return WebClient.builder()
                .baseUrl(url)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    public static WebClient createWebClient(String url) {
        return createWebClient(url, false);
    }

    public static <V, T> Mono<T> exchange(HttpMethod httpMethod, String url, String path, V sendObj, ParameterizedTypeReference<T> responseType) {
        return exchange(httpMethod, createWebClient(url), path, sendObj, responseType, null, MediaType.APPLICATION_JSON);
    }

    public static <V, T> Mono<T> exchange(HttpMethod httpMethod, String url, String path, V sendObj, ParameterizedTypeReference<T> responseType,
                                          List<Map<String, String>> headerMapList) {
        return exchange(httpMethod, createWebClient(url), path, sendObj, responseType, headerMapList, MediaType.APPLICATION_JSON);
    }


    public static <V, T> Mono<T> exchange(HttpMethod httpMethod, String url, String path, V sendObj, ParameterizedTypeReference<T> responseType,
                                          List<Map<String, String>> headerMapList, HttpAuthProvider provider, String contentType) {

        Object inputObject = sendObj;
        if (provider != null) {
            if (headerMapList == null) {
                headerMapList = new ArrayList<>();
            }
            Object replaceInputObj = provider.process(sendObj, headerMapList);
            if(replaceInputObj!=null) {
                inputObject = replaceInputObj;
            }
        }
        MediaType mediaType = MediaType.APPLICATION_JSON;
        if(contentType!=null) {
            mediaType = MediaType.parseMediaType(contentType);
        }
        return exchange(httpMethod, createWebClient(url), path, inputObject, responseType, headerMapList,mediaType);
    }


    public static <V, T> Mono<T> exchange(HttpMethod httpMethod, WebClient webClient, String path, V sendObj, ParameterizedTypeReference<T> responseType,
                                          List<Map<String, String>> headerMapList) {
        return exchange(httpMethod, webClient, path, sendObj, responseType, headerMapList, MediaType.APPLICATION_JSON);
    }

    public static <V, T> Mono<T> exchange(HttpMethod httpMethod, String url, String path, V sendObj, ParameterizedTypeReference<T> responseType,
                                          List<Map<String, String>> headerMapList, MediaType mediaType) {
        return exchange(httpMethod, createWebClient(url), path, sendObj, responseType, headerMapList, mediaType);
    }

    public static <V, T> Mono<T> exchange(HttpMethod httpMethod, WebClient webClient, String path, V sendObj, ParameterizedTypeReference<T> responseType,
                                          List<Map<String, String>> headerMapList, MediaType mediaType) {
        WebClient.RequestBodyUriSpec requestSpec = webClient.method(httpMethod);

        if (httpMethod == HttpMethod.GET) {
            URI inputUri = URI.create(path); // path  "/v1/catalogs/bmw/cars2/?modelId=xxx&page=1" OK
            String pathOnly = inputUri.getPath(); // "/v1/catalogs/bmw/cars2"

            MultiValueMap<String, String> mergedParams = new LinkedMultiValueMap<>();
            mergedParams.addAll(UriComponentsBuilder.fromUri(inputUri).build().getQueryParams()); // path query
            mergedParams.addAll(getQueryParamConvert(sendObj)); // sendObj

            return configureRequest(requestSpec.uri(uriBuilder -> {
                URI uri = uriBuilder
                        .path(pathOnly)
                        .queryParams(mergedParams)
                        .build();
                log.debug("HTTP[GET] URI: {}", uri);
                return uri;
            }), sendObj, headerMapList, mediaType)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> clientResponse.bodyToMono(String.class)
                            .map(body -> new WebClientException(clientResponse.statusCode().value(), body)))
                    .bodyToMono(responseType);
        } else {
            String[] pathParts = path.split("\\?", 2);

            String basePath = pathParts[0];
            String queryStr = pathParts.length > 1 ? pathParts[1] : null;

            return configureRequest(
                    requestSpec.uri(uriBuilder -> {
                        uriBuilder.path(basePath);
                        if (queryStr != null) {
                            String[] queryParams = queryStr.split("&");
                            for (String param : queryParams) {
                                String[] keyValue = param.split("=", 2);
                                String key = keyValue[0];
                                String value = keyValue.length > 1 ? keyValue[1] : "";
                                uriBuilder.queryParam(key, value);
                            }
                        }
                        return uriBuilder.build();
                    }),
                    sendObj,
                    headerMapList,
                    mediaType
            )
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> clientResponse.bodyToMono(String.class)
                            .map(body -> new WebClientException(clientResponse.statusCode().value(), body)))
                    .bodyToMono(responseType);
        }
    }


    public static <V, T> Mono<ResponseEntity<T>> exchangeResponseEntity(HttpMethod httpMethod, String url, String path, V sendObj, ParameterizedTypeReference<T> responseType,
                                                                        List<Map<String, String>> headerMapList) {
        return exchangeResponseEntity(httpMethod, createWebClient(url), path, sendObj, responseType, headerMapList, MediaType.APPLICATION_JSON);
    }


    public static <V, T> Mono<ResponseEntity<T>> exchangeResponseEntity(
            HttpMethod httpMethod, WebClient webClient, String path, V sendObj,
            ParameterizedTypeReference<T> responseType, List<Map<String, String>> headerMapList, MediaType mediaType) {

        WebClient.RequestBodyUriSpec requestSpec = webClient.method(httpMethod);

        if (httpMethod == HttpMethod.GET) {
            return configureRequest(requestSpec.uri(uriBuilder -> uriBuilder.path(path)
                    .queryParams(getQueryParamConvert(sendObj)).build()), sendObj, headerMapList, mediaType)
                    .exchangeToMono(response -> {
                        response.headers().asHttpHeaders().forEach((key, value) ->
                                log.info("Response Header: {} -> {}", key, value));

                        return response.toEntity(responseType);
                    });
        } else {
            String[] pathParts = path.split("\\?", 2);
            String basePath = pathParts[0];
            String queryStr = pathParts.length > 1 ? pathParts[1] : null;

            return configureRequest(requestSpec.uri(uriBuilder -> {
                uriBuilder.path(basePath);
                if (queryStr != null) {
                    String[] queryParams = queryStr.split("&");
                    for (String param : queryParams) {
                        String[] keyValue = param.split("=", 2);
                        String key = keyValue[0];
                        String value = keyValue.length > 1 ? keyValue[1] : "";
                        uriBuilder.queryParam(key, value);
                    }
                }
                return uriBuilder.build();
            }), sendObj, headerMapList, mediaType)
                    .exchangeToMono(response -> {

                        response.headers().asHttpHeaders().forEach((key, value) ->
                                log.info("Response Header: {} -> {}", key, value));

                        return response.toEntity(responseType);
                    });
        }
    }


    public static <V, T> Flux<T> exchangeForFlux(HttpMethod httpMethod, String url, String path, V sendObj, ParameterizedTypeReference<T> responseType,
                                                 List<Map<String, String>> headerMapList, MediaType mediaType) {
        return exchangeForFlux(httpMethod, createWebClient(url), path, sendObj, responseType, headerMapList, mediaType);
    }

    public static <V, T> Flux<T> exchangeForFlux(HttpMethod httpMethod, WebClient webClient, String path, V sendObj, ParameterizedTypeReference<T> responseType,
                                                 List<Map<String, String>> headerMapList, MediaType mediaType) {
        WebClient.RequestBodyUriSpec requestSpec = webClient.method(httpMethod);
        return configureRequest(requestSpec.uri(uriBuilder -> uriBuilder.path(path)
                .queryParams(getQueryParamConvert(sendObj)).build()), sendObj, headerMapList, mediaType)
                .retrieve()
                .bodyToFlux(responseType);
    }


    private static WebClient.RequestBodySpec configureRequest(WebClient.RequestBodySpec requestSpec, Object sendObj,
                                                              List<Map<String, String>> headerMapList, MediaType mediaType) {

        if (headerMapList != null) {
            requestSpec.headers(httpHeaders -> httpHeaders.addAll(getHeaders(headerMapList)));
        }

        /*
        if (ObjectUtils.isNotEmpty(sendObj)) {
            //String jsonBody = OBJECT_MAPPER.writeValueAsString(sendObj);
            //log.info("Request Body: {}", jsonBody);
            requestSpec.bodyValue(sendObj);
        }
         */

        if (ObjectUtils.isNotEmpty(sendObj)) {
            if (mediaType != null && MediaType.MULTIPART_FORM_DATA.equals(mediaType)) {
                if (sendObj instanceof MultiValueMap) {
                    requestSpec.body(BodyInserters.fromMultipartData((MultiValueMap<String, ?>) sendObj));
                } else {
                    throw new IllegalArgumentException("MULTIPART_FORM_DATA requires MultiValueMap as body");
                }
            } else {
                requestSpec.bodyValue(sendObj);
            }
        }

        return requestSpec;
    }

    private static BodyInserter<Object, ReactiveHttpOutputMessage> getBodyInserter(Object sendObj, MediaType mediaType) {
        if (MediaType.APPLICATION_JSON.equals(mediaType)) {
            return BodyInserters.fromValue(sendObj);
        } else if (MediaType.APPLICATION_XML.equals(mediaType)) {
            return BodyInserters.fromValue(sendObj.toString());
        } else {
            return BodyInserters.fromValue(sendObj.toString());
        }
    }

    private static <V> MultiValueMap<String, String> getQueryParamConvert(V sendObj) {
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        if (sendObj != null) {
            Map<String, String> paramMap = OBJECT_MAPPER.convertValue(sendObj, new TypeReference<Map<String, String>>() {
            });
            paramMap.entrySet().stream()
                    .filter(entry -> entry.getValue() != null)
                    .forEach(entry -> multiValueMap.add(entry.getKey(), entry.getValue()));
        }
        return multiValueMap;
    }

    private static <V> MultiValueMap<String, String> getQueryParamConvert_old(V sendObj) {
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        if (sendObj != null) {
            multiValueMap.setAll(OBJECT_MAPPER.convertValue(sendObj, new TypeReference<>() {
            }));
        }
        return multiValueMap;
    }

    private static MultiValueMap<String, String> getHeaders(List<Map<String, String>> headerMapList) {
        HttpHeaders headers = new HttpHeaders();
        if (headerMapList != null) {
            headerMapList.forEach(map -> map.forEach(headers::add));
        }
        return headers;
    }


    public static class WebClientException extends RuntimeException {

        private final int status;

        public WebClientException(int status) {
            super();
            this.status = status;
        }

        public WebClientException(int status, String message) {
            super(message);
            this.status = status;
        }

        public int getStatus() {
            return this.status;
        }
    }


}
