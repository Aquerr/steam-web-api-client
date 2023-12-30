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

    /**
     * Steamworks Web API user authentication key.
     *
     * Added automatically by the {@link io.github.aquerr.steamwebapiclient.SteamWebApiClient} if not added manually.
     */
    @SteamRequestQueryParam("key")
    @Builder.Default
    private String key = "";

    /**
     * Query type (or sorting type) to use while searching. See {@link PublishedFileQueryType}
     */
    @SteamRequestQueryParam("query_type")
    private int queryType;

    /**
     * Current page. Currently, there is an upper limit of 1000.
     */
    @Builder.Default
    @SteamRequestQueryParam("page")
    private String page = "";

    /**
     * Cursor to paginate through the results (set to '*' for the first request).
     * Prefer this over using the page parameter, as it will allow you to do deep pagination.
     * When used, the page parameter will be ignored. Use the "next_cursor" value returned in the response to set up the next query to get the next set of results.
     */
    @Builder.Default
    @SteamRequestQueryParam("cursor")
    private String cursor = "";

    /**
     * (Optional) The number of results, per page to return.
     */
    @SteamRequestQueryParam("numperpage")
    private int numPerPage;

    /**
     * App that created the files.
     */
    @SteamRequestQueryParam("creator_appid")
    private int creatorAppId;

    /**
     * App that consumes the files.
     */
    @SteamRequestQueryParam("appid")
    private int appId;

    /**
     * Tags to match on.
     */
    @Builder.Default
    @SteamRequestQueryParam("requiredtags")
    private String requiredTags = "";

    /**
     * (Optional) Tags that must NOT be present on a published file to satisfy the query.
     */
    @Builder.Default
    @SteamRequestQueryParam("excludedtags")
    private String excludedTags = "";

    /**
     * If true, then items must have all the tags specified, otherwise they must have at least one of the tags.
     */
    @SteamRequestQueryParam("match_all_tags")
    private boolean matchAllTags;

    /**
     * Required flags that must be set on any returned items.
     */
    @Builder.Default
    @SteamRequestQueryParam("required_flags")
    private String requiredFlags = "";

    /**
     * Flags that must not be set on any returned items.
     */
    @Builder.Default
    @SteamRequestQueryParam("omitted_flags")
    private String omittedFlags = "";

    /**
     * Text to match in the item's title or description.
     */
    @Builder.Default
    @SteamRequestQueryParam("search_text")
    private String searchText = "";

    /**
     * File type to search for. See {@link PublishedFileInfoMatchingFileType}
     */
    @SteamRequestQueryParam("filetype")
    private int fileType;

    /**
     * Find all items that reference the given item.
     */
    @SteamRequestQueryParam("child_publishedfileid")
    private long childPublishedFileId;

    /**
     * If queryType is {@code PublishedFileQueryType#RANKED_BY_TREND}, then this is the number of days to get votes for [1,7].
     */
    @SteamRequestQueryParam("days")
    private int days;

    /**
     * If queryType is {@code PublishedFileQueryType#RANKED_BY_TREND}, then limit result set just to items that have votes within the day range given
     */
    @SteamRequestQueryParam("include_recent_votes_only")
    private boolean includeRecentVotesOnly;

    /**
     * Allow stale data to be returned for the specified number of seconds.
     */
    @SteamRequestQueryParam("cache_max_age_seconds")
    private int cacheMaxAgeSeconds;

    /**
     * Language to search in and also what gets returned. Defaults to English.
     */
    @SteamRequestQueryParam("language")
    private int language;

    /**
     * Required key-value tags to match on.
     */
    @SteamRequestQueryParam("required_kv_tags")
    private String requiredKvTags;

    /**
     * (Optional) If true, only return the total number of files that satisfy this query.
     */
    @SteamRequestQueryParam("totalonly")
    private boolean totalOnly;

    /**
     * (Optional) If true, only return the published file ids of files that satisfy this query.
     */
    @SteamRequestQueryParam("ids_only")
    private boolean idsOnly;

    /**
     * Return vote data.
     */
    @SteamRequestQueryParam("return_vote_data")
    private boolean returnVoteData;

    /**
     * Return tags in the file details.
     */
    @SteamRequestQueryParam("return_tags")
    private boolean returnTags;

    /**
     * Return key-value tags in the file details.
     */
    @SteamRequestQueryParam("return_kv_tags")
    private boolean returnKvTags;

    /**
     * Return preview image and video details in the file details.
     */
    @SteamRequestQueryParam("return_previews")
    private boolean returnPreviews;

    /**
     * Return child item ids in the file details.
     */
    @SteamRequestQueryParam("return_children")
    private boolean returnChildren;

    /**
     * Populate the short_description field instead of file_description.
     */
    @SteamRequestQueryParam("return_short_description")
    private boolean returnShortDescription;

    /**
     * Return pricing information, if applicable.
     */
    @SteamRequestQueryParam("return_for_sale_data")
    private boolean returnForSaleData;

    /**
     * Return metadata.
     */
    @SteamRequestQueryParam("return_metadata")
    private boolean returnMetadata;

    /**
     * Return playtime stats for the specified number of days before today.
     */
    @SteamRequestQueryParam("return_playtime_stats")
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

    /**
     * Same as {@link <a href="https://partner.steamgames.com/doc/webapi/IPublishedFileService#EPublishedFileInfoMatchingFileType">https://partner.steamgames.com/doc/webapi/IPublishedFileService#EPublishedFileInfoMatchingFileType</a>}
     */
    public enum PublishedFileInfoMatchingFileType {

        /**
         * Items
         */
        ITEMS(0 ),

        /**
         * A collection of Workshop items.
         */
        COLLECTIONS(1),

        /**
         * Artwork.
         */
        ART(2),

        /**
         * Videos.
         */
        VIDEOS(3),

        /**
         * Screenshots.
         */
        SCREENSHOTS(4),

        /**
         * Items that can be put inside a collection.
         */
        COLLECTION_ELIGIBLE(5),

        /**
         * Unused.
         */
        GAMES(6),

        /**
         * Unused.
         */
        SOFTWARE(7),

        /**
         * Unused.
         */
        CONCEPTS(8),

        /**
         * Unused.
         */
        GREENLIGHT_ITEMS(9),

        /**
         * Guides.
         */
        ALL_GUIDES(10),

        /**
         * Steam web guide.
         */
        WEB_GUIDES(11),

        /**
         * Application integrated guide.
         */
        INTEGRATED_GUIDES(12),

        /**
         * Unknown.
         */
        USABLE_IN_GAME(13),

        /**
         * Workshop merchandise meant to be voted on for the purpose of being sold.
         */
        MERCH(14),

        /**
         * Steam Controller bindings.
         */
        CONTROLLER_BINDINGS(15),

        /**
         * Used internally.
         */
        STEAMWORKS_ACCESS_INVITES(16),

        /**
         * Workshop items that can be sold in-game.
         */
        ITEMS_MTX(17),

        /**
         * Workshop items that can be used right away by the user.
         */
        ITEMS_READY_TO_USE(18),

        /**
         * Unknown.
         */
        WORKSHOP_SHOWCASE(19),

        /**
         * Managed completely by the game, not the user, and not shown on the web.
         */
        GAME_MANAGED_ITEMS(20)
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
