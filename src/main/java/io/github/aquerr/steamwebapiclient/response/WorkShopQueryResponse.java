package io.github.aquerr.steamwebapiclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the result of <a href="https://partner.steamgames.com/doc/webapi/IPublishedFileService#QueryFiles">https://partner.steamgames.com/doc/webapi/IPublishedFileService#QueryFiles</a>
 */
@Data
public class WorkShopQueryResponse implements SteamWebApiResponse {

    /**
     * The response.
     */
    @JsonProperty("response")
    private QueryFilesResponse response;

    @Data
    public static class QueryFilesResponse {

        /**
         * The pointer to next page.
         */
        @JsonProperty("next_cursor")
        private String nextCursor;

        /**
         * The list of found workshop items.
         */
        @JsonProperty("publishedfiledetails")
        private List<PublishedFileDetails> publishedFileDetails = new ArrayList<>();

        @Data
        public static class PublishedFileDetails {

            /**
             * The name of the app.
             */
            @JsonProperty("app_name")
            private String appName;

            /**
             * Ban reason, if banned.
             */
            @JsonProperty("ban_reason")
            private String banReason;

            /**
             * The ban check result.
             */
            @JsonProperty("ban_text_check_result")
            private int banTextCheckResult;

            /**
             * The banner.
             */
            @JsonProperty("banner")
            private String banner;

            /**
             * Determines if the item can be deleted.
             */
            @JsonProperty("can_be_deleted")
            private boolean canBeDeleted;

            /**
             * Determines if the item can be subscribed.
             */
            @JsonProperty("can_subscribe")
            private boolean canSubscribe;

            /**
             * The consumer app id.
             */
            @JsonProperty("consumer_appid")
            private int consumerAppId;

            /**
             * The consumer shortcut id.
             */
            @JsonProperty("consumer_shortcutid")
            private String consumerShortCutId;

            /**
             * The creator of the item.
             */
            @JsonProperty("creator")
            private String creator;

            /**
             * The creator app id.
             */
            @JsonProperty("creator_appid")
            private int creatorAppId;

            /**
             * The favorite count.
             */
            @JsonProperty("favorited")
            private int favorited;

            /**
             * The (long) file description.
             */
            @JsonProperty("file_description")
            private String fileDescription;

            /**
             *
             */
            @JsonProperty("short_description")
            private String shortDescription;

            /**
             * The file size in kilobytes.
             */
            @JsonProperty("file_size")
            private String fileSize;

            /**
             * The file type.
             *
             * <a href="https://partner.steamgames.com/doc/api/ISteamRemoteStorage#EWorkshopFileType">https://partner.steamgames.com/doc/api/ISteamRemoteStorage#EWorkshopFileType</a>
             */
            //TODO: Add enum for fileType and add getFileTypeAsEnum method in this class.
            @JsonProperty("file_type")
            private int fileType;

            /**
             * The name of the file.
             */
            @JsonProperty("filename")
            private String filename;

            /**
             * The item flags.
             */
            @JsonProperty("flags")
            private int flags;

            /**
             * The followers count.
             */
            @JsonProperty("followers")
            private int followers;

            /**
             * Unknown.
             */
            @JsonProperty("hcontent_file")
            private String hcontentFile;

            /**
             * Unknown.
             */
            @JsonProperty("hcontent_preview")
            private String hcontentPreview;

            /**
             * The language.
             */
            @JsonProperty("language")
            private int language;

            /**
             * The lifetime favorited count.
             */
            @JsonProperty("lifetime_favorited")
            private int lifetimeFavorited;

            /**
             * The lifetime followers count.
             */
            @JsonProperty("lifetime_followers")
            private int lifetimeFollowers;

            /**
             * The lifetime playtime in minutes.
             */
            @JsonProperty("lifetime_playtime")
            private String lifetimePlaytimeMinutes;

            /**
             * The lifetime playtime sessions in minutes.
             */
            @JsonProperty("lifetime_playtime_sessions")
            private String lifetimePlaytimeSessionsMinutes;

            /**
             * The lifetime subscriptions count.
             */
            @JsonProperty("lifetime_subscriptions")
            private int lifetimeSubscriptions;

            /**
             * Unknown.
             */
            @JsonProperty("maybe_inappropriate_sex")
            private boolean maybeInappropriateSex;

            /**
             * Unknown.
             */
            @JsonProperty("maybe_inappropriate_violence")
            private boolean maybeInappropriateViolence;

            /**
             * The number of children this item has.
             */
            @JsonProperty("num_children")
            private int numChildren;

            /**
             * The public comments count.
             */
            @JsonProperty("num_comments_public")
            private int numCommentsPublic;

            /**
             * The reports count.
             */
            @JsonProperty("num_reports")
            private int numReports;

            /**
             * The preview file size.
             */
            @JsonProperty("preview_file_size")
            private String previewFileSize;

            /**
             * The preview url.
             */
            @JsonProperty("preview_url")
            private String previewUrl;

            /**
             * The published file id (this item id).
             */
            @JsonProperty("publishedfileid")
            private String publishedFileId;

            /**
             * The result count. (Almost always, if not, equal to 1)
             */
            @JsonProperty("result")
            private int result;

            /**
             * The revision number.
             */
            @JsonProperty("revision")
            private int revision;

            /**
             * The revision change number.
             */
            @JsonProperty("revision_change_number")
            private String revisionChangeNumber;

            /**
             * Determines if this item shows subscribe all button.
             */
            @JsonProperty("show_subscribe_all")
            private boolean showSubscribeAll;

            /**
             * The subscriptions count.
             */
            @JsonProperty("subscriptions")
            private int subscriptions;

            /**
             * The list of tags for given published file.
             */
            @JsonProperty("tags")
            private List<Tag> tags;

            /**
             * The list of children (or required items to use this one).
             */
            @JsonProperty("children")
            private List<ChildItem> children;

            /**
             * The vote/rating data
             */
            @JsonProperty("vote_data")
            private VoteData voteData;

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
             * The title.
             */
            @JsonProperty("title")
            private String title;

            /**
             * The url of the published file.
             */
            @JsonProperty("url")
            private String url;

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
             * Determines if the item is accepted in workshop.
             */
            @JsonProperty("workshop_accepted")
            private boolean workshopAccepted;

            /**
             * Determines if the item is a workshop file.
             */
            @JsonProperty("workshop_file")
            private boolean workshopFile;

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

            @Data
            public static class Tag {

                /**
                 * The display name of the tag.
                 */
                @JsonProperty("display_name")
                private String displayName;

                /**
                 * The name of the tag.
                 */
                @JsonProperty("tag")
                private String tag;
            }

            @Data
            public static class ChildItem
            {
                /**
                 * The id of published file.
                 */
                @JsonProperty("publishedfileid")
                private String publishedFileId;

                /**
                 * The order of item within the collection.
                 */
                @JsonProperty("sortorder")
                private int order;

                /**
                 * The type of item/file.
                 */
                @JsonProperty("file_type")
                private int fileType;
            }

            @Data
            public static class VoteData
            {
                /**
                 * The score.
                 */
                @JsonProperty("score")
                private double score;

                /**
                 * The number of likes
                 */
                @JsonProperty("votes_up")
                private int votesUp;

                /**
                 * The number of dislikes
                 */
                @JsonProperty("votes_down")
                private int votesDown;
            }
        }
    }
}
