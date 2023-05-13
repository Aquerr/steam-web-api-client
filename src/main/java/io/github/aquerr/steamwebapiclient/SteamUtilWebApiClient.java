package io.github.aquerr.steamwebapiclient;

import io.github.aquerr.steamwebapiclient.request.ServerInfoRequest;
import io.github.aquerr.steamwebapiclient.response.ServerInfoResponse;
import io.github.aquerr.steamwebapiclient.util.SteamHttpClient;

import static io.github.aquerr.steamwebapiclient.SteamWebApiClient.API_VERSION_1;

public class SteamUtilWebApiClient {

    private final SteamHttpClient steamHttpClient;

    SteamUtilWebApiClient(SteamHttpClient steamHttpClient) {
        this.steamHttpClient = steamHttpClient;
    }

    public ServerInfoResponse getServerInfo(ServerInfoRequest serverInfoRequest) {
        return this.steamHttpClient.get(
                SteamWebApiInterface.I_STEAM_WEB_API_UTIL,
                SteamWebApiInterface.Method.GET_SERVER_INFO,
                API_VERSION_1,
                serverInfoRequest,
                ServerInfoResponse.class
        );
    }
}
