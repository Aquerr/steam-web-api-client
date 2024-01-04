package io.github.aquerr.steamwebapiclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Class representing the result of <a href="https://partner.steamgames.com/doc/webapi/ISteamWebAPIUtil#GetServerInfo">https://partner.steamgames.com/doc/webapi/ISteamWebAPIUtil#GetServerInfo</a>
 */
@Data
public class ServerInfoResponse implements SteamWebApiResponse {
    @JsonProperty("servertime")
    private Long serverTime;
    @JsonProperty("servertimestring")
    private String serverTimeString;
}
