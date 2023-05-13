package io.github.aquerr.steamwebapiclient;

import io.github.aquerr.steamwebapiclient.request.ServerInfoRequest;
import io.github.aquerr.steamwebapiclient.request.SupportedApiListRequest;
import io.github.aquerr.steamwebapiclient.response.ServerInfoResponse;
import io.github.aquerr.steamwebapiclient.response.SupportedApiListResponse;
import io.github.aquerr.steamwebapiclient.util.SteamHttpClient;

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
     */
    public ServerInfoResponse getServerInfo(ServerInfoRequest request) {
        return this.steamHttpClient.get(
                SteamWebApiInterface.I_STEAM_WEB_API_UTIL,
                SteamWebApiInterface.Method.GET_SERVER_INFO,
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
     */
    public SupportedApiListResponse getSupportedApiList(SupportedApiListRequest request) {
        return this.steamHttpClient.get(
                SteamWebApiInterface.I_STEAM_WEB_API_UTIL,
                SteamWebApiInterface.Method.GET_SUPPORTED_API_LIST,
                API_VERSION_1,
                request,
                SupportedApiListResponse.class
        );
    }
}
