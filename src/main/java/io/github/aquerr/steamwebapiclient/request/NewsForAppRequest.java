package io.github.aquerr.steamwebapiclient.request;

import io.github.aquerr.steamwebapiclient.annotation.SteamRequestQueryParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 * The request representing <a href="https://partner.steamgames.com/doc/webapi/IPlayerService#GetCommunityBadgeProgress">https://partner.steamgames.com/doc/webapi/IPlayerService#GetCommunityBadgeProgress</a>
 */
@Builder
@Getter
@Setter
@ToString
public class NewsForAppRequest implements SteamWebApiRestrictedRequest {

    /**
     * AppID to retrieve news for. Field required.
     */
    @NonNull
    @SteamRequestQueryParam("appid")
    private Long appId;

    /**
     * Maximum length for the content to return, if this is 0 the full content is returned, if it's less then a blurb is generated to fit.
     */
    @SteamRequestQueryParam("maxlength")
    private Integer maxLength;

    /**
     * 	Retrieve posts earlier than this date (unix epoch timestamp).
     */
    @SteamRequestQueryParam("enddate")
    private Long endDate;

    /**
     * 	Amount of posts to retrieve (default 20).
     */
    @SteamRequestQueryParam("count")
    private Integer count;

    /**
     *  Comma-seperated list of feed names to return news for.
     */
    @SteamRequestQueryParam("feeds")
    private String feeds;

    @Override
    public void setApiKey(String apiKey) {}

    @Override
    public String getApiKey() {
        return null;
    }
}
