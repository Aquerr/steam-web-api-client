package io.github.aquerr.steamwebapiclient.request;

import io.github.aquerr.steamwebapiclient.annotation.SteamRequestQueryParam;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The request representing <a href="https://partner.steamgames.com/doc/webapi/IPlayerService#GetOwnedGames">https://partner.steamgames.com/doc/webapi/IPlayerService#GetOwnedGames</a>
 */
@Builder
@Getter
@Setter
@ToString
public class OwnedGamesRequest implements SteamWebApiRestrictedRequest {

    /**
     * Steamworks Web API user authentication key.
     * <p>
     * Added automatically by the client library if not added manually.
     */
    @SteamRequestQueryParam("key")
    @Builder.Default
    private String key = "";

    /**
     * The player we're asking about
     */
    @SteamRequestQueryParam("steamid")
    private long steamId;

    /**
     * True if we want additional details (name, icon) about each game
     */
    @SteamRequestQueryParam("include_appinfo")
    private boolean includeAppInfo;

    /**
     * Free games are excluded by default. If this is set, free games the user has played will be returned.
     */
    @SteamRequestQueryParam("include_played_free_games")
    private boolean includePlayedFreeGames;

    /**
     * 	if set, restricts result set to the passed in apps.
     */
    @SteamRequestQueryParam("appids_filter")
    private int appIdsFilter;

    @Override
    public void setApiKey(String apiKey) {
        this.key = apiKey;
    }

    @Override
    public String getApiKey() {
        return this.key;
    }
}
