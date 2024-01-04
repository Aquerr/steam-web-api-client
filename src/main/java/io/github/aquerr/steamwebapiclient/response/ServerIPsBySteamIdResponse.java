package io.github.aquerr.steamwebapiclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.aquerr.steamwebapiclient.response.shared.Server;
import lombok.Data;

import java.util.List;

/**
 * Class representing the result of <a href="https://partner.steamgames.com/doc/webapi/IGameServersService#GetServerIPsBySteamID">https://partner.steamgames.com/doc/webapi/IGameServersService#GetServerIPsBySteamID</a>
 */
@Data
public class ServerIPsBySteamIdResponse implements SteamWebApiResponse {

    /**
     * The response.
     */
    @JsonProperty("response")
    private Response response;

    @Data
    public static class Response {

        /**
         * The list of servers.
         */
        @JsonProperty("servers")
        private List<Server> servers;
    }
}
