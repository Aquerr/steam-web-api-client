package io.github.aquerr.steamwebapiclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Class representing the result of <a href="https://partner.steamgames.com/doc/webapi/IPlayerService#GetRecentlyPlayedGames">https://partner.steamgames.com/doc/webapi/IPlayerService#GetRecentlyPlayedGames</a>
 */
@Data
public class PlayerRecentlyPlayedGamesResponse implements SteamWebApiResponse {

    @JsonProperty("response")
    private GetRecentlyPlayedGamesResponse response;

    @Data
    public static class GetRecentlyPlayedGamesResponse {
        @JsonProperty("total_count")
        private int totalCount;
        @JsonProperty("games")
        private List<GameDetails> gameList;

        @Data
        public static class GameDetails {
            /**
             * Steam game id.
             */
            @JsonProperty("appid")
            private int appId;
            /**
             * Steam game name.
             */
            @JsonProperty("name")
            private String name;
            /**
             * Play time since last two weeks in minutes.
             */
            @JsonProperty("playtime_2weeks")
            private long playtime2WeeksMinutes;
            /**
             * Overall Play time in minutes.
             */
            @JsonProperty("playtime_forever")
            private long playtimeForeverMinutes;
            /**
             * Game img icon url.
             */
            @JsonProperty("img_icon_url")
            private String imgIconUrl;
            /**
             * Play time on Windows in minutes.
             */
            @JsonProperty("playtime_windows_forever")
            private long playtimeWindowsForeverMinutes;
            /**
             * Play time on Mac in minutes.
             */
            @JsonProperty("playtime_mac_forever")
            private long playtimeMacForeverMinutes;
            /**
             * Play time on Linux in minutes.
             */
            @JsonProperty("playtime_linux_forever")
            private long playtimeLinuxForeverMinutes;
        }
    }
}
