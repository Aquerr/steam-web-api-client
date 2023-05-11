package io.github.aquerr.steamwebapiclient.request;

import io.github.aquerr.steamwebapiclient.annotation.SteamRequestQueryParam;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The request representing <a href="https://partner.steamgames.com/doc/webapi/IPublishedFileService#QueryFiles">https://partner.steamgames.com/doc/webapi/IPublishedFileService#QueryFiles</a>
 */
@Builder
@Getter
@Setter
@ToString
public class WorkShopQueryFilesRequest implements SteamWebApiRestrictedRequest {

    @SteamRequestQueryParam(name = "key")
    @Builder.Default
    private String key = "";
    @SteamRequestQueryParam(name = "query_type")
    private int queryType;
    @Builder.Default
    @SteamRequestQueryParam(name = "page")
    private String page = "";
    @Builder.Default
    @SteamRequestQueryParam(name = "cursor")
    private String cursor = "";
    @SteamRequestQueryParam(name = "numperpage")
    private int numPerPage;
    @SteamRequestQueryParam(name = "creator_appid")
    private int creatorAppId;
    @SteamRequestQueryParam(name = "appid")
    private int appId;
    @Builder.Default
    @SteamRequestQueryParam(name = "requiredtags")
    private String requiredTags = "";
    @Builder.Default
    @SteamRequestQueryParam(name = "excludedtags")
    private String excludedTags = "";
    @SteamRequestQueryParam(name = "match_all_tags")
    private boolean matchAllTags;
    @Builder.Default
    @SteamRequestQueryParam(name = "required_flags")
    private String requiredFlags = "";
    @Builder.Default
    @SteamRequestQueryParam(name = "omitted_flags")
    private String omittedFlags = "";
    @Builder.Default
    @SteamRequestQueryParam(name = "search_text")
    private String searchText = "";
    @SteamRequestQueryParam(name = "filetype")
    private int fileType;
    @SteamRequestQueryParam(name = "child_publishedfileid")
    private long childPublishedFileId;
    @SteamRequestQueryParam(name = "days")
    private int days;
    @SteamRequestQueryParam(name = "include_recent_votes_only")
    private boolean includeRecentVotesOnly;
    @SteamRequestQueryParam(name = "cache_max_age_seconds")
    private int cacheMaxAgeSeconds;
    @SteamRequestQueryParam(name = "language")
    private int language;
    @SteamRequestQueryParam(name = "required_kv_tags")
    private String requiredKvTags;
    @SteamRequestQueryParam(name = "totalonly")
    private boolean totalOnly;
    @SteamRequestQueryParam(name = "ids_only")
    private boolean idsOnly;
    @SteamRequestQueryParam(name = "return_vote_data")
    private boolean returnVoteData;
    @SteamRequestQueryParam(name = "return_tags")
    private boolean returnTags;
    @SteamRequestQueryParam(name = "return_kv_tags")
    private boolean returnKvTags;
    @SteamRequestQueryParam(name = "return_previews")
    private boolean returnPreviews;
    @SteamRequestQueryParam(name = "return_children")
    private boolean returnChildren;
    @SteamRequestQueryParam(name = "return_short_description")
    private boolean returnShortDescription;
    @SteamRequestQueryParam(name = "return_for_sale_data")
    private boolean returnForSaleData;
    @SteamRequestQueryParam(name = "return_metadata")
    private boolean returnMetadata;
    @SteamRequestQueryParam(name = "return_playtime_stats")
    private boolean returnPlaytimeStats;

    @Override
    public void setApiKey(String apiKey)
    {
        this.key = apiKey;
    }

    @Override
    public String getApiKey()
    {
        return this.key;
    }


    public static class WorkShopQueryFilesRequestBuilder {

        private int queryType;
        private int fileType;

        public WorkShopQueryFilesRequestBuilder queryType(PublishedFileQueryType publishedFileQueryType) {
            this.queryType = publishedFileQueryType.getId();
            return this;
        }

        public WorkShopQueryFilesRequestBuilder fileType(PublishedFileInfoMatchingFileType publishedFileInfoMatchingFileType) {
            this.fileType = publishedFileInfoMatchingFileType.getId();
            return this;
        }
    }

    public enum PublishedFileQueryType {

        RANKED_BY_VOTE(0),
        RANKED_BY_PUBLICATION_DATE(1),
        ACCEPTED_FOR_GAME_RANKED_BY_ACCEPTANCE_DATE(2),
        RANKED_BY_TREND(3),
        FAVORITED_BY_FRIENDS_RANKED_BY_PUBLICATION_DATE(4),
        CREATED_BY_FRIENDS_RANKED_BY_PUBLICATION_DATE(5),
        RANKED_BY_NUM_TIMES_REPORTED(6),
        CREATED_BY_FOLLOWED_USERS_RANKED_BY_PUBLICATION_DATE(7),
        NOT_YET_RATED(8),
        RANKED_BY_TOTAL_UNIQUE_SUBSCRIPTIONS(9),
        RANKED_BY_TOTAL_VOTES_ASC(10),
        RANKED_BY_VOTES_UP(11),
        RANKED_BY_TEXT_SEARCH(12),
        RANKED_BY_PLAYTIME_TREND(13),
        RANKED_BY_TOTAL_PLAYTIME(14),
        RANKED_BY_AVERAGE_PLAYTIME_TREND(15),
        RANKED_BY_LIFETIME_AVERAGE_PLAYTIME_TREND(16),
        RANKED_BY_PLAYTIME_SESSIONS_TREND(17),
        RANKED_BY_LIFETIME_PLAYTIME_SESSIONS(18),
        RANKED_BY_INAPPROPRIATE_CONTENT_RATING(19),
        RANKED_BY_BAN_CONTENT_CHECK(20),
        RANKED_BY_LAST_UPDATED_DATE(21)
        ;

        private final int id;

        PublishedFileQueryType(int id)
        {
            this.id = id;
        }

        public int getId()
        {
            return id;
        }
    }

    public enum PublishedFileInfoMatchingFileType {

        RANKED_BY_VOTE(0),
        RANKED_BY_PUBLICATION_DATE(1),
        ACCEPTED_FOR_GAME_RANKED_BY_ACCEPTANCE_DATE(2),
        RANKED_BY_TREND(3),
        FAVORITED_BY_FRIENDS_RANKED_BY_PUBLICATION_DATE(4),
        CREATED_BY_FRIENDS_RANKED_BY_PUBLICATION_DATE(5),
        RANKED_BY_NUM_TIMES_REPORTED(6),
        CREATED_BY_FOLLOWED_USERS_RANKED_BY_PUBLICATION_DATE(7),
        NOT_YET_RATED(8),
        RANKED_BY_TOTAL_UNIQUE_SUBSCRIPTIONS(9),
        RANKED_BY_TOTAL_VOTES_ASC(10),
        RANKED_BY_VOTES_UP(11),
        RANKED_BY_TEXT_SEARCH(12),
        RANKED_BY_PLAYTIME_TREND(13),
        RANKED_BY_TOTAL_PLAYTIME(14),
        RANKED_BY_AVERAGE_PLAYTIME_TREND(15),
        RANKED_BY_LIFETIME_AVERAGE_PLAYTIME_TREND(16),
        RANKED_BY_PLAYTIME_SESSIONS_TREND(17),
        RANKED_BY_LIFETIME_PLAYTIME_SESSIONS(18),
        RANKED_BY_INAPPROPRIATE_CONTENT_RATING(19),
        RANKED_BY_BAN_CONTENT_CHECK(20),
        RANKED_BY_LAST_UPDATED_DATE(21)
        ;

        private final int id;

        PublishedFileInfoMatchingFileType(int id)
        {
            this.id = id;
        }

        public int getId()
        {
            return id;
        }
    }
}
