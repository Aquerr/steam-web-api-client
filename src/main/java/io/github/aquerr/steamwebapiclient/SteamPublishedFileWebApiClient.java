package io.github.aquerr.steamwebapiclient;

import io.github.aquerr.steamwebapiclient.request.WorkShopQueryFilesRequest;
import io.github.aquerr.steamwebapiclient.response.WorkShopQueryResponse;

import static io.github.aquerr.steamwebapiclient.SteamWebApiClient.API_VERSION_1;

/**
 * Steam web api workshop client.
 */
public class SteamPublishedFileWebApiClient
{

    private final SteamHttpClient steamHttpClient;

    SteamPublishedFileWebApiClient(SteamHttpClient steamHttpClient) {
        this.steamHttpClient = steamHttpClient;
    }

    /**
     * Queries workshop.
     *
     * @param request the request as {@link WorkShopQueryFilesRequest}
     * @return the response as {@link WorkShopQueryResponse}
     */
    public WorkShopQueryResponse queryFiles(WorkShopQueryFilesRequest request) {
        return this.steamHttpClient.get(
                SteamWebApiInterfaceMethod.I_PUBLISHED_FILE_SERVICE_QUERY_FILES,
                API_VERSION_1,
                request,
                WorkShopQueryResponse.class
        );
    }
}
