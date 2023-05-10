package io.github.aquerr.steamwebapiclient;

import io.github.aquerr.steamwebapiclient.request.SteamWebApiRequest;
import io.github.aquerr.steamwebapiclient.request.WorkShopQueryFilesRequest;
import io.github.aquerr.steamwebapiclient.response.WorkShopQueryResponse;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static io.github.aquerr.steamwebapiclient.SteamWebApiClient.API_VERSION;
import static io.github.aquerr.steamwebapiclient.SteamWebApiClient.BASE_WEB_API_URI;
import static io.github.aquerr.steamwebapiclient.SteamWebApiClient.appendQueryParamsToUri;
import static java.lang.String.format;

public class SteamWorkshopWebApiClient {
    private static final String SERVICE_I_PUBLISHED_FILE = "IPublishedFileService";
    private static final String METHOD_QUERY_FILES = "QueryFiles";

    private final String apiKey;
    private final HttpClient httpClient;

    SteamWorkshopWebApiClient(String apiKey, HttpClient httpClient) {
        this.apiKey = apiKey;
        this.httpClient = httpClient;
    }

    public WorkShopQueryResponse queryFiles(WorkShopQueryFilesRequest workshopQueryFilesRequest) {
        workshopQueryFilesRequest.setKey(apiKey);

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(appendQueryParamsToUri(BASE_WEB_API_URI.resolve(format("/%s/%s/%s", SERVICE_I_PUBLISHED_FILE, METHOD_QUERY_FILES, API_VERSION)),
                        SteamWebApiRequest.toQueryParams(workshopQueryFilesRequest)))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                //TODO: Map response string to json and then to api response object.
                return new WorkShopQueryResponse();
            }
        }
        catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new WorkShopQueryResponse();
    }
}
