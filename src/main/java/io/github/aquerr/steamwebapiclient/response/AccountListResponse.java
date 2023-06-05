package io.github.aquerr.steamwebapiclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Class representing the result of <a href="https://partner.steamgames.com/doc/webapi/IGameServersService#GetAccountList">https://partner.steamgames.com/doc/webapi/IGameServersService#GetAccountList</a>
 */
@Data
public class AccountListResponse implements SteamWebApiResponse {

    /**
     * The response.
     */
    @JsonProperty("response")
    private Response response;

    @Data
    public static class Response {

        /**
         * Unknown (Probably iss account banned).
         */
        @JsonProperty("is_banned")
        private boolean isBanned;

        /**
         * Unknown (Probably account time to expire).
         */
        @JsonProperty("expires")
        private long expires;

        /**
         * Unknown (Probably steamId).
         */
        @JsonProperty("actor")
        private long actor;

        /**
         * Unknown.
         */
        @JsonProperty("last_action_time")
        private long lastActionTime;
    }
}
