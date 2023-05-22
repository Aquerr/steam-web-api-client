package io.github.aquerr.steamwebapiclient;

import io.github.aquerr.steamwebapiclient.request.WorkShopQueryFilesRequest;
import io.github.aquerr.steamwebapiclient.response.WorkShopQueryResponse;
import io.github.aquerr.steamwebapiclient.util.SteamHttpClient;

import static io.github.aquerr.steamwebapiclient.SteamWebApiClient.API_VERSION_1;

/**
 * Steam web api workshop client.
 */
public class SteamWorkshopWebApiClient {

    private final SteamHttpClient steamHttpClient;

    SteamWorkshopWebApiClient(SteamHttpClient steamHttpClient) {
        this.steamHttpClient = steamHttpClient;
    }

    public WorkShopQueryResponse queryFiles(WorkShopQueryFilesRequest workshopQueryFilesRequest) {
        return this.steamHttpClient.get(
                SteamWebApiInterfaceMethod.I_PUBLISHED_FILE_SERVICE_QUERY_FILES,
                API_VERSION_1,
                workshopQueryFilesRequest,
                WorkShopQueryResponse.class
        );
    }
}
