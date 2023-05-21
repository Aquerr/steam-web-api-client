package io.github.aquerr.steamwebapiclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Class representing the result of <a href="https://partner.steamgames.com/doc/webapi/IPlayerService#GetSteamLevel">https://partner.steamgames.com/doc/webapi/IPlayerService#GetSteamLevel</a>
 */
@Data
public class SteamLevelResponse implements SteamWebApiResponse {

    @JsonProperty("response")
    private GetSteamLevelResponse response;

    @Data
    public static class GetSteamLevelResponse {
        /**
         * Steam user profile level.
         */
        @JsonProperty("player_level")
        private int profileLevel;
    }
}
