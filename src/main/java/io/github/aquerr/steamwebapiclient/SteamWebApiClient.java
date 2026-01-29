package io.github.aquerr.steamwebapiclient;

import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.cfg.EnumFeature;
import tools.jackson.databind.json.JsonMapper;

import java.net.http.HttpClient;
import java.time.Duration;

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
    private final SteamEconServiceWebApiClient steamEconServiceWebApiClient;

    private SteamWebApiClient(String baseUrl, String apiKey, HttpClient httpClient, ObjectMapper objectMapper) {
        this(new SteamHttpClient(baseUrl, apiKey, httpClient, objectMapper));
    }

    private SteamWebApiClient(SteamHttpClient steamHttpClient) {
        this.steamPublishedFileWebApiClient = new SteamPublishedFileWebApiClient(steamHttpClient);
        this.steamUtilWebApiClient = new SteamUtilWebApiClient(steamHttpClient);
        this.steamPlayerWebApiClient = new SteamPlayerWebApiClient(steamHttpClient);
        this.steamRemoteStorageClient = new SteamRemoteStorageClient(steamHttpClient);
        this.steamNewsWebApiClient = new SteamNewsWebApiClient(steamHttpClient);
        this.steamGameServersServiceApiClient = new SteamGameServersServiceApiClient(steamHttpClient);
        this.steamEconServiceWebApiClient = new SteamEconServiceWebApiClient(steamHttpClient);
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

    /**
     * Gets the {@link SteamEconServiceWebApiClient}
     * @return the {@link SteamEconServiceWebApiClient}
     */
    public SteamEconServiceWebApiClient getSteamEconServiceWebApiClient() {
        return steamEconServiceWebApiClient;
    }

    /**
     * Creates new builder for SteamWebApiClient.
     * @return the builder.
     */
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String baseUrl = BASE_WEB_API_URI;
        private String apiKey;
        private HttpClient httpClient = defaultHttpClient();
        private ObjectMapper objectMapper = defaultObjectMapper();

        /**
         * Optionally sets the base url to use for contacting steam web api.
         *
         * NOTE: You do not need to use this method as it uses steam web api url by default.
         * This method is mainly available to allow stubbing the client in tests.
         *
         * @param baseUrl the base url.
         * @return the builder.
         */
        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        /**
         * Sets Steam Web API Key
         *
         * Note: Api token is not required for the client to function.
         *       However, it is needed by most of the api calls,
         *       therefore, if left empty, invocation of such APIs will result in an exception or an empty result.
         *
         * @param apiKey the Steam Web API Key
         * @return the builder.
         */
        public Builder apiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        /**
         * Optionally sets the {@link HttpClient} that will be used for contacting the Steam Web Api.
         *
         * Note: Default {@link HttpClient} is set to timeout after 5 seconds.
         * You can use this builder method to use your own {@link HttpClient}
         *
         * @param httpClient the {@link HttpClient}.
         * @return the builder.
         */
        public Builder httpClient(HttpClient httpClient) {
            this.httpClient = httpClient;
            return this;
        }

        /**
         * Optionally sets custom {@link ObjectMapper} that will be used serialization and deserialization of requests and responses.
         *
         * Note: Default {@link ObjectMapper} is set to fail on unknown properties and read unknown enum values using default value.
         * You can use this builder method to use your own {@link ObjectMapper}
         *
         * @param objectMapper the {@link ObjectMapper}.
         * @return the builder.
         */
        public Builder objectMapper(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
            return this;
        }

        /**
         * Builds new immutable SteamWebApiClient
         * @return the {@link SteamWebApiClient}
         */
        public SteamWebApiClient build() {
            return new SteamWebApiClient(baseUrl, apiKey, httpClient, objectMapper);
        }
    }

    protected static HttpClient defaultHttpClient() {
        return HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();
    }

    protected static ObjectMapper defaultObjectMapper() {
        return JsonMapper.builder()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .enable(EnumFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE)
                .findAndAddModules()
                .build();
    }
}
