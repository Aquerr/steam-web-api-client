package io.github.aquerr.steamwebapiclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Class representing the result of <a href="https://partner.steamgames.com/doc/webapi/IGameServersService#QueryLoginToken">https://partner.steamgames.com/doc/webapi/IGameServersService#QueryLoginToken</a>
 */
@Data
public class QueryLoginTokenResponse implements SteamWebApiResponse {

    /**
     * The response
     */
    @JsonProperty("response")
    private Response response;

    @Data
    public static class Response {

        /**
         * The steam id of GSLT.
         */
        @JsonProperty("steamid")
        private String steamId;

        /**
         * Determines if GSLT is banned.
         */
        @JsonProperty("is_banned")
        private boolean isBanned;

        /**
         * Determines if GSLT expires and when.
         */
        @JsonProperty("expires")
        private long expires;
    }
}
