package io.github.aquerr.steamwebapiclient;

import io.github.aquerr.steamwebapiclient.exception.ClientException;
import io.github.aquerr.steamwebapiclient.request.NewsForAppRequest;
import io.github.aquerr.steamwebapiclient.response.NewsForAppResponse;
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
     *
     * @throws ClientException if:
     * - request could not be sent due to an error
     * - response has not been received
     * - response could not be parsed
     * - any other error occurs
     */
    public NewsForAppResponse getNewsForApp(NewsForAppRequest request) throws ClientException {
        return this.steamHttpClient.get(
                SteamWebApiInterfaceMethod.I_STEAM_NEWS_GET_NEWS_FOR_APP,
                API_VERSION_2,
                request,
                NewsForAppResponse.class
        );
    }

}
