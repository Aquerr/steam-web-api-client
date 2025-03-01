package io.github.aquerr.steamwebapiclient;

import io.github.aquerr.steamwebapiclient.exception.ClientException;
import io.github.aquerr.steamwebapiclient.request.GetDetailsRequest;
import io.github.aquerr.steamwebapiclient.request.WorkShopQueryFilesRequest;
import io.github.aquerr.steamwebapiclient.response.FileDetailsResponse;
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
     *
     * @throws ClientException if:
     * - request could not be sent due to an error
     * - response has not been received
     * - response could not be parsed
     * - any other error occurs
     */
    public WorkShopQueryResponse queryFiles(WorkShopQueryFilesRequest request) throws ClientException {
        return this.steamHttpClient.get(
                SteamWebApiInterfaceMethod.I_PUBLISHED_FILE_SERVICE_QUERY_FILES,
                API_VERSION_1,
                request,
                WorkShopQueryResponse.class
        );
    }

    /**
     * Gets information about a set of published files
     */
    public FileDetailsResponse getDetails(GetDetailsRequest request) throws ClientException {
        return this.steamHttpClient.get(
                SteamWebApiInterfaceMethod.I_PUBLISHED_FILE_SERVICE_GET_DETAILS,
                API_VERSION_1,
                request,
                FileDetailsResponse.class
        );
    }
}
