package io.github.aquerr.steamwebapiclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.aquerr.steamwebapiclient.util.SteamHttpClient;

import java.net.http.HttpClient;

/**
 * Main entry point of the API.
 * Instances of {@link SteamWebApiClient} immutable.
 * It is recommended to have only one instance of this class.
 */
public class SteamWebApiClient {
    public static final String BASE_WEB_API_URI = "https://api.steampowered.com";
    public static final String API_VERSION_1 = "v1";
    public static final String API_VERSION_2 = "v2";

    private final SteamPublishedFileWebApiClient steamPublishedFileWebApiClient;
    private final SteamUtilWebApiClient steamUtilWebApiClient;
    private final SteamPlayerWebApiClient steamPlayerWebApiClient;
    private final SteamRemoteStorageClient steamRemoteStorageClient;
    private final SteamNewsWebApiClient steamNewsWebApiClient;
    private final SteamGameServersServiceApiClient steamGameServersServiceApiClient;

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
        this.steamPublishedFileWebApiClient = new SteamPublishedFileWebApiClient(steamHttpClient);
        this.steamUtilWebApiClient = new SteamUtilWebApiClient(steamHttpClient);
        this.steamPlayerWebApiClient = new SteamPlayerWebApiClient(steamHttpClient);
        this.steamRemoteStorageClient = new SteamRemoteStorageClient(steamHttpClient);
        this.steamNewsWebApiClient = new SteamNewsWebApiClient(steamHttpClient);
        this.steamGameServersServiceApiClient = new SteamGameServersServiceApiClient(steamHttpClient);
    }

    /**
     * Gets the {@link SteamPublishedFileWebApiClient}
     *
     * This client lets search workshop items, like mods, collections etc.
     *
     * @return the {@link SteamPublishedFileWebApiClient}
     */
    public SteamPublishedFileWebApiClient getSteamPublishedFileWebApiClient()
    {
        return steamPublishedFileWebApiClient;
    }

    /**
     * Gets the {@link SteamUtilWebApiClient}
     * @return the {@link SteamUtilWebApiClient}
     */
    public SteamUtilWebApiClient getSteamUtilWebApiClient() {
        return steamUtilWebApiClient;
    }

    /**
     * Gets the {@link SteamRemoteStorageClient}
     * @return the {@link SteamRemoteStorageClient}
     */
    public SteamRemoteStorageClient getSteamRemoteStorageClient() {
        return steamRemoteStorageClient;
    }

    /**
     * Gets the {@link SteamPlayerWebApiClient}
     * @return the {@link SteamPlayerWebApiClient}
     */
    public SteamPlayerWebApiClient getPlayerWebApiClient() {
        return steamPlayerWebApiClient;
    }

    /**
     * Gets the {@link SteamNewsWebApiClient}
     * @return the {@link SteamNewsWebApiClient}
     */
    public SteamNewsWebApiClient getNewsWebApiClient() {
        return steamNewsWebApiClient;
    }

    /**
     * Gets the {@link SteamGameServersServiceApiClient}
     * @return the {@link SteamGameServersServiceApiClient}
     */
    public SteamGameServersServiceApiClient getSteamGameServersServiceApiClient() {
        return steamGameServersServiceApiClient;
    }


}
