package io.github.aquerr.steamwebapiclient;

import io.github.aquerr.steamwebapiclient.request.NewsForAppRequest;
import io.github.aquerr.steamwebapiclient.response.NewsForAppResponse;
import io.github.aquerr.steamwebapiclient.util.SteamHttpClient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import static io.github.aquerr.steamwebapiclient.SteamWebApiClient.API_VERSION_2;

/**
 * Steam web api news client.
 */
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class SteamNewsWebApiClient {

    private final SteamHttpClient steamHttpClient;

    /**
     * 	Get the news for the specified app.
     */
    public NewsForAppResponse getNewsForApp(NewsForAppRequest request) {
        return this.steamHttpClient.get(
                SteamWebApiInterface.I_STEAM_NEWS,
                SteamWebApiInterface.Method.GET_NEWS_FOR_APP,
                API_VERSION_2,
                request,
                NewsForAppResponse.class
        );
    }

}
