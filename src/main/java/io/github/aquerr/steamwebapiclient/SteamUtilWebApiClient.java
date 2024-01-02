package io.github.aquerr.steamwebapiclient;

import io.github.aquerr.steamwebapiclient.exception.ClientException;
import io.github.aquerr.steamwebapiclient.request.ServerInfoRequest;
import io.github.aquerr.steamwebapiclient.request.SupportedApiListRequest;
import io.github.aquerr.steamwebapiclient.response.ServerInfoResponse;
import io.github.aquerr.steamwebapiclient.response.SupportedApiListResponse;

import static io.github.aquerr.steamwebapiclient.SteamWebApiClient.API_VERSION_1;

/**
 * Steam web api util client.
 */
public class SteamUtilWebApiClient {

    private final SteamHttpClient steamHttpClient;

    SteamUtilWebApiClient(SteamHttpClient steamHttpClient) {
        this.steamHttpClient = steamHttpClient;
    }

    /**
     * Retrieves steam server info.
     *
     * @param request the request as {@link ServerInfoRequest}
     * @return the response as {@link ServerInfoRequest}
     *
     * @throws ClientException if:
     * - request could not be sent due to an error
     * - response has not been received
     * - response could not be parsed
     * - any other error occurs
     */
    public ServerInfoResponse getServerInfo(ServerInfoRequest request) throws ClientException {
        return this.steamHttpClient.get(
                SteamWebApiInterfaceMethod.I_STEAM_WEB_API_UTIL_GET_SERVER_INFO,
                API_VERSION_1,
                request,
                ServerInfoResponse.class
        );
    }

    /**
     * Retrieves supported api list for given api key (supplied while creating {@link SteamWebApiClient})
     *
     * @param request the request as {@link SupportedApiListRequest}
     * @return the response as {@link SupportedApiListResponse}
     *
     * @throws ClientException if:
     * - request could not be sent due to an error
     * - response has not been received
     * - response could not be parsed
     * - any other error occurs
     */
    public SupportedApiListResponse getSupportedApiList(SupportedApiListRequest request) throws ClientException {
        return this.steamHttpClient.get(
                SteamWebApiInterfaceMethod.I_STEAM_WEB_API_UTIL_GET_SUPPORTED_API_LIST,
                API_VERSION_1,
                request,
                SupportedApiListResponse.class
        );
    }
}
