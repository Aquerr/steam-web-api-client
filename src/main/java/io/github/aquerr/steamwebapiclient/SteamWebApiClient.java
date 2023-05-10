package io.github.aquerr.steamwebapiclient;

import java.net.URI;
import java.util.Map;

public class SteamWebApiClient {
    public static final URI BASE_WEB_API_URI = URI.create("https://api.steampowered.com");
    public static final String API_VERSION = "v1";

    private final String apiToken;

    public SteamWebApiClient(String apiToken) {
        this.apiToken = apiToken;
    }

    static URI appendQueryParamsToUri(URI uri, Map<String, String> queryParams) {
        return uri.resolve(toQueryString(queryParams));
    }

    static String toQueryString(Map<String, String> queryParams) {
        if (queryParams.isEmpty())
            return "";

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("?");
        queryParams.entrySet().stream()
                .forEach(stringStringEntry -> stringBuilder.append(stringStringEntry.getKey()).append("=").append(stringStringEntry.getValue())
                        .append("&"));

        if (stringBuilder.charAt(stringBuilder.length() - 1) == '&') {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }
}