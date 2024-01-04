package io.github.aquerr.steamwebapiclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Class representing the result of <a href="https://partner.steamgames.com/doc/webapi/IGameServersService#ResetLoginToken">https://partner.steamgames.com/doc/webapi/IGameServersService#ResetLoginToken</a>
 */
@Data
public class ResetLoginTokenResponse implements SteamWebApiResponse {

    /**
     * The response.
     */
    @JsonProperty("response")
    private Response response;

    @Data
    public static class Response {

        /**
         * New login token.
         */
        @JsonProperty("login_token")
        private String loginToken;
    }
}
