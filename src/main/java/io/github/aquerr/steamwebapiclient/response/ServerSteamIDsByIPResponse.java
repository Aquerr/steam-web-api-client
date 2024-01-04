package io.github.aquerr.steamwebapiclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.aquerr.steamwebapiclient.response.shared.Server;
import lombok.Data;

import java.util.List;

/**
 * Class representing the result of <a href="https://partner.steamgames.com/doc/webapi/IGameServersService#GetServerSteamIDsByIP">https://partner.steamgames.com/doc/webapi/IGameServersService#GetServerSteamIDsByIP</a>
 */
@Data
public class ServerSteamIDsByIPResponse implements SteamWebApiResponse {

    /**
     * The response.
     */
    @JsonProperty("response")
    private ServerIPsBySteamIdResponse.Response response;

    @Data
    public static class Response {

        /**
         * The list of servers.
         */
        @JsonProperty("servers")
        private List<Server> servers;
    }
}
