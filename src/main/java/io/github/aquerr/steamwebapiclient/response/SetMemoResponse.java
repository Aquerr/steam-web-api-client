package io.github.aquerr.steamwebapiclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Class representing the result of <a href="https://partner.steamgames.com/doc/webapi/IGameServersService#SetMemo">https://partner.steamgames.com/doc/webapi/IGameServersService#SetMemo</a>
 */
@Data
public class SetMemoResponse implements SteamWebApiResponse {

    /**
     * The response. (Always empty)
     */
    @JsonProperty("response")
    private DeleteAccountResponse.Response response;

    @Data
    public static class Response {

    }
}
