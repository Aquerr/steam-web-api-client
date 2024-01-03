package io.github.aquerr.steamwebapiclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Class representing the result of <a href="https://partner.steamgames.com/doc/webapi/IGameServersService#DeleteAccount">https://partner.steamgames.com/doc/webapi/IGameServersService#DeleteAccount</a>
 */
@Data
public class DeleteAccountResponse implements SteamWebApiResponse {

    /**
     * The response. (Always empty)
     */
    @JsonProperty("response")
    private Response response;

    @Data
    public static class Response {

    }
}
