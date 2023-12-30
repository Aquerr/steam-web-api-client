package io.github.aquerr.steamwebapiclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the result of <a href="https://partner.steamgames.com/doc/webapi/ISteamRemoteStorage#GetPublishedFileDetails">https://partner.steamgames.com/doc/webapi/ISteamRemoteStorage#GetPublishedFileDetails</a>
 */
@Data
public class PublishedFileDetailsResponse implements SteamWebApiResponse {

    /**
     * The response.
     */
    @JsonProperty("response")
    private PublishedFileDetailsResponse.QueryFilesResponse response;

    @Data
    public static class QueryFilesResponse {

        /**
         * The result count. (Almost always, if not, equal to 1)
         */
        @JsonProperty("result")
        private int result;

        /**
         * The number of results that have been found. Basically the size of {@link PublishedFileDetailsResponse.QueryFilesResponse#publishedFileDetails} list.
         */
        @JsonProperty("resultcount")
        private int resultCount;

        /**
         * The list of found published file details.
         */
        @JsonProperty("publishedfiledetails")
        private List<PublishedFileDetailsResponse.QueryFilesResponse.PublishedFileDetails> publishedFileDetails = new ArrayList<>();

        @Data
        public static class PublishedFileDetails {
            /**
             * The ban reason.
             */
            @JsonProperty("ban_reason")
            private String banReason;

            /**
             * The banned flag.
             */
            @JsonProperty("banned")
            private int banned;

            /**
             * The title.
             */
            @JsonProperty("title")
            private String title;

            /**
             * The description.
             */
            @JsonProperty("description")
            private String description;

            /**
             * The consumer app id.
             */
            @JsonProperty("consumer_app_id")
            private int consumerAppId;

            /**
             * The creator app id.
             */
            @JsonProperty("creator_app_id")
            private int creatorAppId;

            /**
             * The creator id
             */
            @JsonProperty("creator")
            private long creator;

            /**
             * The favorite count
             */
            @JsonProperty("favorited")
            private int favorited;

            /**
             * The file size in kilobytes.
             */
            @JsonProperty("file_size")
            private long fileSizeKb;

            /**
             * The file url.
             */
            @JsonProperty("file_url")
            private String fileUrl;

            /**
             * The name of the file.
             */
            @JsonProperty("filename")
            private String fileName;

            /**
             * Unknown.
             */
            @JsonProperty("hcontent_file")
            private String hContentFile;

            /**
             * Unknown.
             */
            @JsonProperty("hcontent_preview")
            private String hContentPreview;

            /**
             * The favorited count in lifetime.
             */
            @JsonProperty("lifetime_favorited")
            private int lifeTimeFavortied;

            /**
             * The subscriptions count in lifetime.
             */
            @JsonProperty("lifetime_subscriptions")
            private int lifetimeSubscriptions;

            /**
             * The preview url.
             */
            @JsonProperty("preview_url")
            private String previewUrl;

            /**
             * The published file id.
             */
            @JsonProperty("publishedfileid")
            private String publishedFileId;

            /**
             * The number of results. (Almost always, if not, equal to 1)
             */
            @JsonProperty("result")
            private int result;

            /**
             * The number of subscriptions.
             */
            @JsonProperty("subscriptions")
            private int subscriptions;

            /**
             * The list of tags for given published file.
             */
            @JsonProperty("tags")
            private List<Tag> tags = new ArrayList<>();

            /**
             * The time when the item got created in seconds since epoch time.
             */
            @JsonProperty("time_created")
            private long createdTimeSeconds;

            /**
             * The time when the item got last updated in seconds since epoch time.
             */
            @JsonProperty("time_updated")
            private long updatedTimeSeconds;

            /**
             * The number of views.
             */
            @JsonProperty("views")
            private int views;

            /**
             * The visibility flag.
             */
            @JsonProperty("visibility")
            private int visibility;

            /**
             * Gets the created date time as {@link ZonedDateTime} with UTC time zone.
             * @return the created date.
             */
            public ZonedDateTime getCreatedDateTime() {
                return ZonedDateTime.of(LocalDateTime.ofEpochSecond(this.createdTimeSeconds, 0, ZoneOffset.UTC), ZoneOffset.UTC);
            }

            /**
             * Gets the last updated date time as {@link ZonedDateTime} with UTC time zone.
             * @return the last updated date.
             */
            public ZonedDateTime getUpdatedDateTime() {
                return ZonedDateTime.of(LocalDateTime.ofEpochSecond(this.updatedTimeSeconds, 0, ZoneOffset.UTC), ZoneOffset.UTC);
            }

            /**
             * Represents the steam workshop tag.
             */
            @Data
            @NoArgsConstructor
            @AllArgsConstructor
            public static class Tag {

                /**
                 * The name of the tag.
                 */
                @JsonProperty("tag")
                private String tag;
            }
        }
    }
}
