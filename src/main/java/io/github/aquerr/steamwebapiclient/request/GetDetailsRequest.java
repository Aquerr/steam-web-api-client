package io.github.aquerr.steamwebapiclient.request;

import io.github.aquerr.steamwebapiclient.annotation.QueryParamCollectionBehaviour;
import io.github.aquerr.steamwebapiclient.annotation.SteamRequestQueryParam;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@Setter
@ToString
public class GetDetailsRequest implements SteamWebApiRestrictedRequest {

    /**
     * Steamworks Web API user authentication key.
     * <p>
     * Added automatically by the {@link io.github.aquerr.steamwebapiclient.SteamWebApiClient} if not added manually.
     */
    @SteamRequestQueryParam("key")
    @Builder.Default
    private String apiKey = "";

    /**
     * App that consumes/uses the files.
     */
    @SteamRequestQueryParam("appid")
    private int appId;

    /**
     * The list of file ids to get the details for.
     */
    @SteamRequestQueryParam(value = "publishedfileids", collectionBehaviour = QueryParamCollectionBehaviour.MULTIPLE_PARAMETERS)
    private List<Long> publishedFileIds;

    /**
     * Return tags in the file details.
     */
    @SteamRequestQueryParam("includetags")
    private boolean includeTags;

    /**
     * Return additional previews in file details.
     */
    @SteamRequestQueryParam("includeadditionalpreviews")
    private boolean includeAdditionalPreviews;

    /**
     * Return child item ids (or required items to use this one) in the file details.
     */
    @SteamRequestQueryParam("includechildren")
    private boolean includeChildren;

    /**
     * Return key-value tags in the file details.
     */
    @SteamRequestQueryParam("includekvtags")
    private boolean includeKvTags;

    /**
     * Return vote data in file details.
     */
    @SteamRequestQueryParam("includevotes")
    private boolean includeVotes;

    /**
     * Populate the short_description field instead of file_description.
     */
    @SteamRequestQueryParam("short_description")
    private boolean includeShortDescription;

    /**
     * Return pricing information, if applicable.
     */
    @SteamRequestQueryParam("includeforsaledata")
    private boolean includeForSaleData;

    /**
     * Return metadata.
     */
    @SteamRequestQueryParam("includemetadata")
    private boolean includeMetadata;

    /**
     * Specifies the localized text to return. Defaults to English.
     */
    @SteamRequestQueryParam("language")
    private int language;

    /**
     * Return playtime stats for the specified number of days before today.
     */
    @SteamRequestQueryParam("return_playtime_stats")
    private boolean includePlaytimeStats;

    /**
     * Strips BBCode from descriptions.
     */
    @SteamRequestQueryParam("strip_description_bbcode")
    private boolean stripDescriptionBbcode;

    /**
     * Return reactions in file details.
     */
    @SteamRequestQueryParam("includereactions")
    private boolean includeReactions;

    /**
     * Returns hidden items. Used by admin tools.
     */
    @SteamRequestQueryParam("admin_query")
    private boolean adminQuery;
}
