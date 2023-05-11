package io.github.aquerr.steamwebapiclient.request;

/**
 * Interface representing the restricted steam api request.
 *
 * It requires the api key.
 */
public interface SteamWebApiRestrictedRequest extends SteamWebApiRequest
{
    void setApiKey(String apiKey);

    String getApiKey();
}
