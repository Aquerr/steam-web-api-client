package io.github.aquerr.steamwebapiclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Class representing the result of <a href="https://partner.steamgames.com/doc/webapi/IGameServersService#GetAccountPublicInfo">https://partner.steamgames.com/doc/webapi/IGameServersService#GetAccountPublicInfo</a>
 */
@Data
public class AccountPublicInfoResponse implements SteamWebApiResponse {

    /**
     * The response.
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
         * The id of the app that this token is for.
         */
        @JsonProperty("appid")
        private int appId;
    }
}
