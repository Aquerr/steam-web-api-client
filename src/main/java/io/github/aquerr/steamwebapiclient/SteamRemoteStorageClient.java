package io.github.aquerr.steamwebapiclient;

import io.github.aquerr.steamwebapiclient.request.PublishedFileDetailsRequest;
import io.github.aquerr.steamwebapiclient.response.PublishedFileDetailsResponse;
import io.github.aquerr.steamwebapiclient.util.UrlEncodedForm;
import io.github.aquerr.steamwebapiclient.util.SteamHttpClient;

import java.util.HashMap;
import java.util.Map;

import static io.github.aquerr.steamwebapiclient.SteamWebApiClient.API_VERSION_1;

public class SteamRemoteStorageClient {
    private final SteamHttpClient steamHttpClient;

    SteamRemoteStorageClient(final SteamHttpClient steamHttpClient) {
        this.steamHttpClient = steamHttpClient;
    }

    /**
     * Retrieves published file (i.e. workshop item) details.
     *
     * @param request the request as {@link PublishedFileDetailsRequest}
     * @return the response as {@link PublishedFileDetailsResponse}
     */
    public PublishedFileDetailsResponse getPublishedFileDetails(PublishedFileDetailsRequest request) {

        Map<String, String> parameters = new HashMap<>();
        parameters.put("itemcount", String.valueOf(request.getItemCount()));
        for (int i = 0; i < request.getItemCount(); i++) {
            parameters.put("publishedfileids[" + i + "]", String.valueOf(request.getPublishedFileIds().get(i)));
        }

        return this.steamHttpClient.post(
                SteamWebApiInterface.I_STEAM_REMOTE_STORAGE,
                SteamWebApiInterface.Method.GET_PUBLISHED_FILE_DETAILS,
                API_VERSION_1,
                UrlEncodedForm.of(parameters),
                PublishedFileDetailsResponse.class
        );
    }
}
