package io.github.aquerr.steamwebapiclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

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
        private String actor;

        /**
         * Unknown.
         */
        @JsonProperty("last_action_time")
        private long lastActionTime;

        /**
         * The list of GSLT (Game Server Login Tokens).
         */
        @JsonProperty("servers")
        private List<LoginToken> loginTokens;

        @Data
        public static class LoginToken {

            /**
             * The steam id.
             */
            @JsonProperty("steamid")
            private String steamId;

            /**
             * The id of the app that this token is for.
             */
            @JsonProperty("appid")
            private int appId;

            /**
             * The login token value.
             */
            @JsonProperty("login_token")
            private String value;

            /**
             * The memo/description of the token.
             */
            @JsonProperty("memo")
            private String memo;

            /**
             * Determines if the token is deleted.
             */
            @JsonProperty("is_deleted")
            private boolean isDeleted;

            /**
             * Determines if the token has expired.
             */
            @JsonProperty("is_expired")
            private boolean isExpired;

            /**
             * (Unsure) Last login time.
             */
            @JsonProperty("rt_last_logon")
            private long lastLogon;
        }
    }
}
