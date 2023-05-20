package io.github.aquerr.steamwebapiclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Class representing the result of <a href="https://partner.steamgames.com/doc/webapi/IPlayerService#GetOwnedGames">https://partner.steamgames.com/doc/webapi/IPlayerService#GetOwnedGames</a>
 */
@Data
public class OwnedGamesResponse implements SteamWebApiResponse {

    @JsonProperty("response")
    private GetOwnedGamesResponse response;

    @Data
    public static class GetOwnedGamesResponse {
        @JsonProperty("game_count")
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
            /**
             * Seconds since last played.
             */
            @JsonProperty("rtime_last_played")
            private long sinceLastPlayedTimeSeconds;
            /**
             * 	Unknown.
             */
            @JsonProperty("content_descriptorids")
            private List<Integer> contentDescriptorIds;
            /**
             * Gets the last played date time as {@link ZonedDateTime} with UTC time zone.
             * @return the last played date.
             */
            public ZonedDateTime getLastPlayedDateTime() {
                return ZonedDateTime.of(LocalDateTime.ofEpochSecond(this.sinceLastPlayedTimeSeconds, 0, ZoneOffset.UTC), ZoneOffset.UTC);
            }
        }
    }
}
