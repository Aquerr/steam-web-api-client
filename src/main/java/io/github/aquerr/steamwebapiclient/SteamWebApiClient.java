package io.github.aquerr.steamwebapiclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.aquerr.steamwebapiclient.util.SteamHttpClient;

import java.net.http.HttpClient;

/**
 * Main Entry point of the API.
 * Instances of {@link SteamWebApiClient} immutable.
 * It is recommended to only have one instance of this class.
 */
public class SteamWebApiClient {
    public static final String BASE_WEB_API_URI = "https://api.steampowered.com";
    public static final String API_VERSION_1 = "v1";
    public static final String API_VERSION_2 = "v2";

    private final SteamWorkshopWebApiClient workshopWebApiClient;
    private final SteamUtilWebApiClient steamUtilWebApiClient;
    private final SteamPlayerWebApiClient steamPlayerWebApiClient;

    /**
     * Creates an instance of {@link SteamWebApiClient}.
     *
     * Note: Api token is not required for the client to function.
     *       However, it is needed by most of the api calls,
     *       therefore execution of such will result in an exception or an empty result.
     *
     * @param apiKey the steam api key, can be null.
     */
    public SteamWebApiClient(String apiKey) {
        this(new SteamHttpClient(BASE_WEB_API_URI, apiKey));
    }

    /**
     * Constructs {@link SteamWebApiClient}.
     * Allows passing custom {@link HttpClient} that will be used for contacting the Steam Web Api.
     *
     * @param apiKey the api key, optional.
     * @param httpClient the http client that will be used for contacting the Steam Web Api.
     */
    public SteamWebApiClient(String apiKey, HttpClient httpClient) {
        this(new SteamHttpClient(BASE_WEB_API_URI, apiKey, httpClient));
    }

    /**
     * Constructs {@link SteamWebApiClient}.
     * Allows passing custom {@link ObjectMapper} that will be used for serialization and deserialization of requests and responses.
     *
     * @param apiKey the api key, optional.
     * @param objectMapper custom object mapper that will be used serialization and deserialization of requests and responses.
     */
    public SteamWebApiClient(String apiKey, ObjectMapper objectMapper) {
        this(new SteamHttpClient(BASE_WEB_API_URI, apiKey, objectMapper));
    }

    /**
     * Constructs {@link SteamWebApiClient}.
     * Allows passing custom {@link HttpClient} and {@link ObjectMapper} that will be used for
     * contacting the Steam Web Api and serialization and deserialization of requests and responses.
     *
     * @param apiKey the api key, optional.
     * @param httpClient the http client that will be used for contacting the Steam Web Api.
     * @param objectMapper custom object mapper that will be used serialization and deserialization of requests and responses.
     */
    public SteamWebApiClient(String apiKey, HttpClient httpClient, ObjectMapper objectMapper) {
        this(new SteamHttpClient(BASE_WEB_API_URI, apiKey, httpClient, objectMapper));
    }

    private SteamWebApiClient(SteamHttpClient steamHttpClient) {
        this.workshopWebApiClient = new SteamWorkshopWebApiClient(steamHttpClient);
        this.steamUtilWebApiClient = new SteamUtilWebApiClient(steamHttpClient);
        this.steamPlayerWebApiClient = new SteamPlayerWebApiClient(steamHttpClient);
    }

    /**
     * Gets the {@link SteamWorkshopWebApiClient}
     * @return the {@link SteamWorkshopWebApiClient}
     */
    public SteamWorkshopWebApiClient getWorkshopWebApiClient()
    {
        return workshopWebApiClient;
    }

    /**
     * Gets the {@link SteamUtilWebApiClient}
     * @return the {@link SteamUtilWebApiClient}
     */
    public SteamUtilWebApiClient getSteamUtilWebApiClient() {
        return steamUtilWebApiClient;
    }

    /**
     * Gets the {@link SteamPlayerWebApiClient}
     * @return the {@link SteamPlayerWebApiClient}
     */
    public SteamPlayerWebApiClient getPlayerWebApiClient() {
        return steamPlayerWebApiClient;
    }


}
