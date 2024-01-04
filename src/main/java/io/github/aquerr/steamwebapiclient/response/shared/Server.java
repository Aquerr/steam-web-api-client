package io.github.aquerr.steamwebapiclient.response.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Server {

    /**
     * The steam identifier of the server.
     */
    @JsonProperty("steamid")
    private String steamId;

    /**
     * The server ip address.
     */
    @JsonProperty("addr")
    private String ipAddress;
}
