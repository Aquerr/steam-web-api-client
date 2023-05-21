package io.github.aquerr.steamwebapiclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Class representing the result of <a href="https://partner.steamgames.com/doc/webapi/IPlayerService#GetBadges">https://partner.steamgames.com/doc/webapi/IPlayerService#GetBadges</a>
 */
@Data
public class BadgesResponse implements SteamWebApiResponse {

    @JsonProperty("response")
    private Response response;

    @Data
    public static class Response {
        /**
         * List of user badges.
         */
        @JsonProperty("badges")
        private List<Badge> badges;

        @Data
        public static class Badge {
            /**
             * Badge id.
             */
            @JsonProperty("badgeid")
            private long id;
            /**
             * Badge level.
             */
            @JsonProperty("level")
            private int level;
            /**
             * Time when the badge was completed in seconds.
             */
            @JsonProperty("completion_time")
            private long completionTimeSeconds;
            /**
             * Amount of points collected from badge.
             */
            @JsonProperty("xp")
            private int xp;
            /**
             * Unknown.
             */
            @JsonProperty("scarcity")
            private int scarcity;
            /**
             * Gets the completion date time as {@link ZonedDateTime} with UTC time zone.
             * @return the completion date.
             */
            public ZonedDateTime getCompletionDateTime() {
                return ZonedDateTime.of(LocalDateTime.ofEpochSecond(this.completionTimeSeconds, 0, ZoneOffset.UTC), ZoneOffset.UTC);
            }
        }
    }
}
