package io.github.aquerr.steamwebapiclient.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.aquerr.steamwebapiclient.SteamWebApiInterface;
import io.github.aquerr.steamwebapiclient.annotation.SteamRequestQueryParam;
import io.github.aquerr.steamwebapiclient.request.SteamWebApiRequest;
import io.github.aquerr.steamwebapiclient.request.SteamWebApiRestrictedRequest;
import io.github.aquerr.steamwebapiclient.response.SteamWebApiResponse;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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

    public <T extends SteamWebApiResponse> T get(
            SteamWebApiInterface apiInterface,
            SteamWebApiInterface.Method apiMethod,
            String version,
            SteamWebApiRequest steamWebApiRequest,
            Class<T> responseClass) {
        StringBuilder uriPathBuilder = new StringBuilder();
        uriPathBuilder.append(this.baseUrl);
        uriPathBuilder.append("/");
        uriPathBuilder.append(apiInterface.getInterfaceName());
        uriPathBuilder.append("/");
        uriPathBuilder.append(apiMethod.getMethodName());
        uriPathBuilder.append("/");
        uriPathBuilder.append(version);
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
            SteamWebApiInterface apiInterface,
            SteamWebApiInterface.Method apiMethod,
            String version,
            SteamWebApiRestrictedRequest steamWebApiRestrictedRequest,
            Class<T> responseClass) {
        if (steamWebApiRestrictedRequest != null) {
            steamWebApiRestrictedRequest.setApiKey(apiKey);
        }
        return get(apiInterface, apiMethod, version, (SteamWebApiRequest) steamWebApiRestrictedRequest, responseClass);
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

    private Map<String, String> toQueryParams(SteamWebApiRequest steamWebApiRequest)
    {
        Map<String, String> params = new HashMap<>();
        Field[] fields = steamWebApiRequest.getClass().getDeclaredFields();
        for (final Field field : fields)
        {
            if (!field.isAnnotationPresent(SteamRequestQueryParam.class))
                continue;

            try
            {
                SteamRequestQueryParam steamRequestQueryParam = field.getAnnotation(SteamRequestQueryParam.class);
                field.setAccessible(true);
                Object value = field.get(steamWebApiRequest);
                field.setAccessible(false);
                params.put(steamRequestQueryParam.name(), value != null ? String.valueOf(value) : null);
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }
        return params;
    }
}
