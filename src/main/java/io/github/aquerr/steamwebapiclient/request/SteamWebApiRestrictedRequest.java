package io.github.aquerr.steamwebapiclient.request;

/**
 * Interface representing the restricted steam api request.
 * <p>
 * It requires the api key.
 */
public interface SteamWebApiRestrictedRequest extends SteamWebApiRequest {
    String getApiKey();

    void setApiKey(String apiKey);
}
