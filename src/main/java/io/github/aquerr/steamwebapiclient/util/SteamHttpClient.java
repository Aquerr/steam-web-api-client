package io.github.aquerr.steamwebapiclient.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.aquerr.steamwebapiclient.SteamWebApiInterfaceMethod;
import io.github.aquerr.steamwebapiclient.annotation.SteamRequestQueryParam;
import io.github.aquerr.steamwebapiclient.request.SteamWebApiRequest;
import io.github.aquerr.steamwebapiclient.request.SteamWebApiRestrictedRequest;
import io.github.aquerr.steamwebapiclient.response.SteamWebApiResponse;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * A wrapper around Java {@link HttpClient} to make steam api calls.
 */
public class SteamHttpClient {

    private final String apiKey;
    private final String baseUrl;

    private final HttpClient httpClient;

    private final ObjectMapper objectMapper;

    /**
     * Basic constructor for {@link SteamHttpClient}.
     * Requires baseUrl and apiKey.
     *
     * @param baseUrl the base url.
     * @param apiKey the api key, can be null.
     */
    public SteamHttpClient(String baseUrl, String apiKey) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;

        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();

        this.objectMapper = new ObjectMapper()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .findAndRegisterModules();
    }

    /**
     * Constructs {@link SteamHttpClient}.
     * Requires baseUrl and apiKey.
     * Allows passing custom {@link ObjectMapper} that will be used for serialization and deserialization of requests and responses.
     *
     * @param baseUrl the base url.
     * @param apiKey the api key, can be null.
     * @param objectMapper the object mapper.
     */
    public SteamHttpClient(String baseUrl, String apiKey, ObjectMapper objectMapper) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;

        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();

        this.objectMapper = objectMapper.copy();
    }

    /**
     * Constructs {@link SteamHttpClient}.
     * Requires baseUrl and apiKey.
     * Allows passing custom {@link HttpClient} that will be used for contacting the Steam Web Api.
     *
     * @param baseUrl the base url.
     * @param apiKey the api key, can be null.
     * @param httpClient the http client.
     */
    public SteamHttpClient(String baseUrl, String apiKey, HttpClient httpClient) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;

        this.httpClient = httpClient;

        this.objectMapper = new ObjectMapper()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .findAndRegisterModules();
    }

    /**
     * Constructs {@link SteamHttpClient}.
     * Requires baseUrl and apiKey.
     * Allows passing custom {@link HttpClient} and {@link ObjectMapper} that will be used for
     * contacting the Steam Web Api and serialization and deserialization of requests and responses.
     *
     * @param baseUrl the base url.
     * @param apiKey the api key, can be null.
     * @param httpClient the http client.
     * @param objectMapper the object mapper.
     */
    public SteamHttpClient(String baseUrl, String apiKey, HttpClient httpClient, ObjectMapper objectMapper) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;

        this.httpClient = httpClient;

        this.objectMapper = objectMapper.copy();
    }

    public <T extends SteamWebApiResponse> T get(
            SteamWebApiInterfaceMethod apiInterfaceMethod,
            String version,
            SteamWebApiRequest steamWebApiRequest,
            Class<T> responseClass) {

        if (apiInterfaceMethod == null || version == null || responseClass == null) {
            throw new IllegalArgumentException("apiInterfaceMethod, version and responseClass cannot be null!");
        }

        StringBuilder uriPathBuilder = new StringBuilder(buildUrl(apiInterfaceMethod, version));
        if (steamWebApiRequest != null) {
            uriPathBuilder.append(toQueryString(toQueryParams(steamWebApiRequest)));
        }

        URI requestUri = URI.create(uriPathBuilder.toString());

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(requestUri)
                .build();
        try {
            HttpResponse<String> response = this.httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                ObjectNode responseObjectNode = objectMapper.readValue(response.body(), ObjectNode.class);
                SteamWebApiResponse steamWebApiResponse = objectMapper.treeToValue(responseObjectNode, responseClass);
                return (T)steamWebApiResponse;
            }
        }
        catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public <T extends SteamWebApiResponse> T get(
            SteamWebApiInterfaceMethod apiInterfaceMethod,
            String version,
            SteamWebApiRestrictedRequest steamWebApiRestrictedRequest,
            Class<T> responseClass) {

        if (apiInterfaceMethod == null || version == null || responseClass == null) {
            throw new IllegalArgumentException("apiInterfaceMethod, version and responseClass cannot be null!");
        }

        if (steamWebApiRestrictedRequest != null) {
            steamWebApiRestrictedRequest.setApiKey(apiKey);
        }
        return get(apiInterfaceMethod, version, (SteamWebApiRequest) steamWebApiRestrictedRequest, responseClass);
    }

    private String toQueryString(Map<String, String> queryParams) {
        if (queryParams.isEmpty())
            return "";

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("?");
        queryParams.entrySet().stream()
                .filter(queryParam -> queryParam.getValue() != null)
                .forEach(queryParam -> stringBuilder.append(queryParam.getKey()).append("=").append(queryParam.getValue())
                        .append("&"));

        if (stringBuilder.charAt(stringBuilder.length() - 1) == '&') {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

    private Map<String, String> toQueryParams(SteamWebApiRequest steamWebApiRequest) {
        Map<String, String> params = new HashMap<>();
        Field[] fields = steamWebApiRequest.getClass().getDeclaredFields();
        for (final Field field : fields) {
            if (!field.isAnnotationPresent(SteamRequestQueryParam.class)) {
                continue;
            }

            String name = getSteamRequestParamNameFromField(field);
            String fieldValue = getFieldValueAsString(steamWebApiRequest, field);
            if (fieldValue != null) {
                params.put(name, URLEncoder.encode(fieldValue, StandardCharsets.UTF_8));
            }
        }
        return params;
    }

    private String getSteamRequestParamNameFromField(Field field) {
        try {
            SteamRequestQueryParam steamRequestQueryParam = field.getAnnotation(SteamRequestQueryParam.class);
            if (steamRequestQueryParam.value() != null && !steamRequestQueryParam.value().trim().equals("")) {
                return steamRequestQueryParam.value();
            }
            return field.getName();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String getFieldValueAsString(SteamWebApiRequest steamWebApiRequest, Field field) {
        try {
            field.setAccessible(true);
            Object value = field.get(steamWebApiRequest);
            field.setAccessible(false);
            if (value != null) {
                return String.valueOf(value);
            }
            else return null;
        }
        catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public <T extends SteamWebApiResponse> T post(SteamWebApiInterfaceMethod apiInterfaceMethod,
                                                  String version,
                                                  UrlEncodedForm urlEncodedForm,
                                                  Class<T> responseClass) {
        if (apiInterfaceMethod == null || version == null || responseClass == null || urlEncodedForm == null) {
            throw new IllegalArgumentException("apiInterfaceMethod, version, urlEncodedForm and responseClass cannot be null!");
        }

        URI requestUri = URI.create(buildUrl(apiInterfaceMethod, version));

        byte[] formBytes = urlEncodedForm.getAsByteArray();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofByteArray(formBytes))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .uri(requestUri)
                .build();
        try {
            HttpResponse<String> response = this.httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                ObjectNode responseObjectNode = objectMapper.readValue(response.body(), ObjectNode.class);
                SteamWebApiResponse steamWebApiResponse = objectMapper.treeToValue(responseObjectNode, responseClass);
                return (T)steamWebApiResponse;
            }
        }
        catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private String buildUrl(final SteamWebApiInterfaceMethod apiInterfaceMethod, final String version) {
        final StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(this.baseUrl);
        urlBuilder.append("/");
        urlBuilder.append(apiInterfaceMethod.getInterfaceName());
        urlBuilder.append("/");
        urlBuilder.append(apiInterfaceMethod.getMethodName());
        urlBuilder.append("/");
        urlBuilder.append(version);
        return urlBuilder.toString();
    }
}
