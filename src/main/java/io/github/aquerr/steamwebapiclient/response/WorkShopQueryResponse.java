package io.github.aquerr.steamwebapiclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Class representing the result of <a href="https://partner.steamgames.com/doc/webapi/IPublishedFileService#QueryFiles">https://partner.steamgames.com/doc/webapi/IPublishedFileService#QueryFiles</a>
 */
@Data
public class WorkShopQueryResponse implements SteamWebApiResponse {

    @JsonProperty("next_cursor")
    private String nextCursor;
    @JsonProperty("publishedfiledetails")
    private List<PublishedFileDetails> publishedFileDetails;

    @Data
    public static class PublishedFileDetails {
        @JsonProperty("app_name")
        private String appName;
        @JsonProperty("ban_reason")
        private String banReason;
        @JsonProperty("ban_text_check_result")
        private int banTextCheckResult;
        @JsonProperty("banner")
        private String banner;
        @JsonProperty("can_be_deleted")
        private boolean canBeDeleted;
        @JsonProperty("can_subscribe")
        private boolean canSubscribe;
        @JsonProperty("consumer_appid")
        private int consumerAppId;
        @JsonProperty("consumer_shortcutid")
        private String consumerShortCutId;
        @JsonProperty("creator")
        private String creator;
        @JsonProperty("creator_appid")
        private int creatorAppId;
        @JsonProperty("favorited")
        private int favorited;
        @JsonProperty("file_description")
        private String fileDescription;
        @JsonProperty("file_size")
        private String fileSize;
        @JsonProperty("file_type")
        private int fileType;
        @JsonProperty("filename")
        private String filename;
        @JsonProperty("flags")
        private int flags;
        @JsonProperty("followers")
        private int followers;
        @JsonProperty("hcontent_file")
        private String hcontentFile;
        @JsonProperty("hcontent_preview")
        private String hcontentPreview;
        @JsonProperty("language")
        private int language;
        @JsonProperty("lifetime_favorited")
        private int lifetimeFavorited;
        @JsonProperty("lifetime_followers")
        private int lifetimeFollowers;
        @JsonProperty("lifetime_playtime")
        private String lifetimePlaytime;
        @JsonProperty("lifetime_playtime_sessions")
        private String lifetimePlaytimeSessions;
        @JsonProperty("lifetime_subscriptions")
        private int lifetimeSubscriptions;
        @JsonProperty("maybe_inappropriate_sex")
        private boolean maybeInappropriateSex;
        @JsonProperty("maybe_inappropriate_violence")
        private boolean maybeInappropriateViolence;
        @JsonProperty("num_children")
        private int numChildren;
        @JsonProperty("num_comments_public")
        private int numCommentsPublic;
        @JsonProperty("num_reports")
        private int numReports;
        @JsonProperty("preview_file_size")
        private String previewFileSize;
        @JsonProperty("preview_url")
        private String previewUrl;
        @JsonProperty("publishedfileid")
        private String publishedFileId;
        @JsonProperty("result")
        private int result;
        @JsonProperty("revision")
        private int revision;
        @JsonProperty("revision_change_number")
        private String revisionChangeNumber;
        @JsonProperty("show_subscribe_all")
        private boolean showSubscribeAll;
        @JsonProperty("subscriptions")
        private int subscriptions;
        @JsonProperty("tags")
        private List<Tag> tags;
        @JsonProperty("time_created")
        private int timeCreated;
        @JsonProperty("time_updated")
        private int timeUpdated;
        @JsonProperty("title")
        private String title;
        @JsonProperty("url")
        private String url;
        @JsonProperty("views")
        private int views;
        @JsonProperty("visibility")
        private int visibility;
        @JsonProperty("workshop_accepted")
        private boolean workshopAccepted;
        @JsonProperty("workshop_file")
        private boolean workshopFile;

        public static class Tag {
            @JsonProperty("display_name")
            private String displayName;
            @JsonProperty("tag")
            private String tag;
        }
    }
}
