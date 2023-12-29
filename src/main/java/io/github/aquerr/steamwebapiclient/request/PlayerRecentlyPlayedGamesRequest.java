package io.github.aquerr.steamwebapiclient.request;

import io.github.aquerr.steamwebapiclient.annotation.SteamRequestQueryParam;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The request representing <a href="https://partner.steamgames.com/doc/webapi/IPlayerService#GetRecentlyPlayedGames">https://partner.steamgames.com/doc/webapi/IPlayerService#GetRecentlyPlayedGames</a>
 */
@Builder
@Getter
@Setter
@ToString
public class PlayerRecentlyPlayedGamesRequest implements SteamWebApiRestrictedRequest {

        /**
         * Steamworks Web API user authentication key.
         *
         * Added automatically by the {@link io.github.aquerr.steamwebapiclient.SteamWebApiClient} if not added manually.
         */
        @SteamRequestQueryParam("key")
        @Builder.Default
        private String key = "";

        /**
         * Steam user id to be asked for data.
         */
        @SteamRequestQueryParam("steamid")
        private long steamId;

        /**
         * Limit of results to be returned.
         */
        @SteamRequestQueryParam("count")
        private int count;

    @Override
    public void setApiKey(String apiKey)
    {
        this.key = apiKey;
    }

    @Override
    public String getApiKey() { return this.key; }
}
