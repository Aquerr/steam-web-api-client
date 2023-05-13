package io.github.aquerr.steamwebapiclient;

import io.github.aquerr.steamwebapiclient.util.SteamHttpClient;

/**
 * Main Entry point of the API.
 *
 * Immutable. It is recommended to only have one instance of this client.
 */
public class SteamWebApiClient {
    public static final String BASE_WEB_API_URI = "https://api.steampowered.com";
    public static final String API_VERSION_1 = "v1";
    public static final String API_VERSION_2 = "v2";

    private final SteamWorkshopWebApiClient workshopWebApiClient;
    private final SteamUtilWebApiClient steamUtilWebApiClient;

    /**
     * Creates an instance of {@link SteamWebApiClient}.
     *
     * Note: Api token is not required for the client to function.
     *       However, it is needed by most of the api calls,
     *       therefore execution of such will result in an exception or an empty result.
     *
     * @param apiToken the steam api token, optional.
     */
    public SteamWebApiClient(String apiToken) {
        SteamHttpClient steamHttpClient = new SteamHttpClient(BASE_WEB_API_URI, apiToken);
        this.workshopWebApiClient = new SteamWorkshopWebApiClient(steamHttpClient);
        this.steamUtilWebApiClient = new SteamUtilWebApiClient(steamHttpClient);
    }

    public SteamWorkshopWebApiClient getWorkshopWebApiClient()
    {
        return workshopWebApiClient;
    }

    public SteamUtilWebApiClient getSteamUtilWebApiClient() {
        return steamUtilWebApiClient;
    }
}
