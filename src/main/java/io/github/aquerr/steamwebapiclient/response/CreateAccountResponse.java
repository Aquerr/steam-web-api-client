package io.github.aquerr.steamwebapiclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Class representing the result of <a href="https://partner.steamgames.com/doc/webapi/IGameServersService#CreateAccount">https://partner.steamgames.com/doc/webapi/IGameServersService#CreateAccount</a>
 */
@Data
public class CreateAccountResponse implements SteamWebApiResponse {

    /**
     * The response.
     */
    @JsonProperty("response")
    private CreateAccountResponse.Response response;

    @Data
    public static class Response {

        /**
         * The steam identifier of the GSLT (Game Server Login Token).
         */
        @JsonProperty("steamid")
        private String steamId;

        /**
         * The login token value.
         */
        @JsonProperty("login_token")
        private String loginToken;
    }
}
