package io.github.aquerr.steamwebapiclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ServerInfoResponse implements SteamWebApiResponse {
    @JsonProperty("servertime")
    private Long serverTime;
    @JsonProperty("servertimestring")
    private String serverTimeString;
}
