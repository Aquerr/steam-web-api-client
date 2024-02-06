package io.github.aquerr.steamwebapiclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.aquerr.steamwebapiclient.annotation.SteamRequestQueryParam;
import io.github.aquerr.steamwebapiclient.exception.ClientException;
import io.github.aquerr.steamwebapiclient.exception.HttpClientException;
import io.github.aquerr.steamwebapiclient.request.SteamWebApiRequest;
import io.github.aquerr.steamwebapiclient.request.SteamWebApiRestrictedRequest;
import io.github.aquerr.steamwebapiclient.response.SteamWebApiResponse;
import io.github.aquerr.steamwebapiclient.util.UrlEncodedForm;

import java.lang.reflect.Field;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * A wrapper around Java {@link HttpClient} to perform Steam API calls.
 */
class SteamHttpClient {

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
    SteamHttpClient(String baseUrl, String apiKey) {
        this(baseUrl, apiKey, defaultHttpClient(), defaultObjectMapper());
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
    SteamHttpClient(String baseUrl, String apiKey, ObjectMapper objectMapper) {
        this(baseUrl, apiKey, defaultHttpClient(), objectMapper);
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
    SteamHttpClient(String baseUrl, String apiKey, HttpClient httpClient) {
        this(baseUrl, apiKey, httpClient, defaultObjectMapper());
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
    SteamHttpClient(String baseUrl, String apiKey, HttpClient httpClient, ObjectMapper objectMapper) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.httpClient = httpClient;
        this.objectMapper = objectMapper.copy();
    }

    public <T extends SteamWebApiResponse> T get(
            SteamWebApiInterfaceMethod apiInterfaceMethod,
            String version,
            SteamWebApiRequest steamWebApiRequest,
            Class<T> responseClass) throws ClientException {

        try {
            if (apiInterfaceMethod == null || version == null || responseClass == null) {
                throw new IllegalArgumentException("apiInterfaceMethod, version and responseClass cannot be null!");
            }

            populateApiKeyIfRestrictedRequest(steamWebApiRequest);

            StringBuilder uriPathBuilder = new StringBuilder(buildUrl(apiInterfaceMethod, version));
            if (steamWebApiRequest != null) {
                uriPathBuilder.append(toQueryString(toQueryParams(steamWebApiRequest)));
            }

            URI requestUri = URI.create(uriPathBuilder.toString());

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(requestUri)
                    .build();

            HttpResponse<String> response = this.httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == HttpStatus.OK.getCode()) {
                return parseResponse(response, responseClass);
            } else {
                throw new HttpClientException(prepareHttpErrorMessage(response), response.statusCode());
            }
        } catch (Exception exception) {
            throw wrapInClientException(exception);
        }
    }

    public <T extends SteamWebApiResponse> T post(SteamWebApiInterfaceMethod apiInterfaceMethod,
                                                  String version,
                                                  SteamWebApiRequest request,
                                                  Class<T> responseClass) throws ClientException {
        try {
            if (apiInterfaceMethod == null || version == null || responseClass == null || request == null) {
                throw new IllegalArgumentException("apiInterfaceMethod, version, request and responseClass cannot be null!");
            }

            populateApiKeyIfRestrictedRequest(request);

            Map<String, String> params = toQueryParams(request);
            String requestJson = objectMapper.writeValueAsString(request);
            params.put("input_json", URLEncoder.encode(requestJson, StandardCharsets.UTF_8));

            URI requestUri = URI.create(buildUrl(apiInterfaceMethod, version) + toQueryString(params));

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(" ")) // I know, it is crazy... but otherwise Steam API would not understand the request... really...
                    .uri(requestUri)
                    .build();

            HttpResponse<String> response = this.httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == HttpStatus.OK.getCode()) {
                return parseResponse(response, responseClass);
            } else {
                throw new HttpClientException(prepareHttpErrorMessage(response), response.statusCode());
            }
        } catch (Exception exception) {
            throw wrapInClientException(exception);
        }
    }

    private String prepareHttpErrorMessage(HttpResponse<String> httpResponse)
    {
        String httpStatus = Optional.ofNullable(HttpStatus.findByCode(httpResponse.statusCode()))
                .orElse(HttpStatus.BAD_REQUEST).name();
        return httpResponse.statusCode() + " "
                + httpStatus + " Response body: " + httpResponse.body();
    }

    private ClientException wrapInClientException(Exception exception) {
        if (exception instanceof HttpClientException)
            return (HttpClientException)exception;
        else return new ClientException(exception);
    }

    private <T extends SteamWebApiResponse> T parseResponse(HttpResponse<String> response, Class<T> responseClass) throws JsonProcessingException {
        ObjectNode responseObjectNode = objectMapper.readValue(response.body(), ObjectNode.class);
        SteamWebApiResponse steamWebApiResponse = objectMapper.treeToValue(responseObjectNode, responseClass);
        return (T)steamWebApiResponse;
    }

    public <T extends SteamWebApiResponse> T post(SteamWebApiInterfaceMethod apiInterfaceMethod,
                                                  String version,
                                                  UrlEncodedForm urlEncodedForm,
                                                  Class<T> responseClass) throws ClientException {
        try {
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

            HttpResponse<String> response = this.httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == HttpStatus.OK.getCode()) {
                return parseResponse(response, responseClass);
            } else {
                throw new HttpClientException(prepareHttpErrorMessage(response), response.statusCode());
            }
        } catch (Exception exception) {
            throw wrapInClientException(exception);
        }
    }

    private void populateApiKeyIfRestrictedRequest(SteamWebApiRequest steamWebApiRequest) {
        if (steamWebApiRequest instanceof SteamWebApiRestrictedRequest) {
            SteamWebApiRestrictedRequest request = (SteamWebApiRestrictedRequest) steamWebApiRequest;
            request.setApiKey(apiKey);
        }
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
                if (value instanceof Collection<?>) {
                    Collection<?> collection = (Collection<?>) value;
                    return collectionToString(collection);
                }
                return String.valueOf(value);
            }
            else return null;
        }
        catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private String collectionToString(Collection<?> collection) {
        if (collection.isEmpty()) {
            return null;
        }
        return Arrays.stream(collection.toArray())
                .reduce("", (base, element) -> base + "," + element)
                .toString().substring(1);
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

    private static HttpClient defaultHttpClient() {
        return HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();
    }

    private static ObjectMapper defaultObjectMapper() {
        return new ObjectMapper()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE)
                .findAndRegisterModules();
    }
}
