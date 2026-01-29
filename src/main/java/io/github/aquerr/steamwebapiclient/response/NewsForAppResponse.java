package io.github.aquerr.steamwebapiclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the result of <a href="https://partner.steamgames.com/doc/webapi/ISteamNews#GetNewsForApp">https://partner.steamgames.com/doc/webapi/ISteamNews#GetNewsForApp</a>
 */
@Data
public class NewsForAppResponse implements SteamWebApiResponse {

    @JsonProperty("appnews")
    private Response response;

    @Data
    public static class Response {

        /**
         * AppID for which news were retrieved.
         */
        @JsonProperty("appid")
        private int appId;

        /**
         * List of news for app.
         */
        @JsonProperty("newsitems")
        private List<NewsItem> newsItems = new ArrayList<>();

        /**
         * Amount of elements returned.
         */
        @JsonProperty("count")
        private int count;

        @Data
        public static class NewsItem {

            /**
             * Probably news id.
             */
            @JsonProperty("gid")
            private String id;

            /**
             * News title.
             */
            @JsonProperty("title")
            private String title;

            /**
             * News url.
             */
            @JsonProperty("url")
            private String url;

            /**
             * Is news url external.
             */
            @JsonProperty("is_external_url")
            private boolean isExternalUrl;

            /**
             * News author.
             */
            @JsonProperty("author")
            private String author;

            /**
             * News content.
             */
            @JsonProperty("contents")
            private String content;

            /**
             * News feed label.
             */
            @JsonProperty("feedlabel")
            private String feedLabel;

            /**
             * News creation date.
             */
            @JsonProperty("date")
            private long creationDate;

            /**
             * News feed name.
             */
            @JsonProperty("feedname")
            private String feedName;

            /**
             * News feed type.
             */
            @JsonProperty("feed_type")
            private int feedType;

            /**
             * AppID for which news were retrieved.
             */
            @JsonProperty("appid")
            private int appId;

            /**
             * Gets the creation date time as {@link ZonedDateTime} with UTC time zone.
             *
             * @return the creation date.
             */
            public ZonedDateTime getCreationDateTime() {
                return ZonedDateTime.of(LocalDateTime.ofEpochSecond(this.creationDate, 0, ZoneOffset.UTC), ZoneOffset.UTC);
            }
        }
    }
}
