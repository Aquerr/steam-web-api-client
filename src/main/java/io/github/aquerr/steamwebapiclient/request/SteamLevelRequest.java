package io.github.aquerr.steamwebapiclient.request;

import io.github.aquerr.steamwebapiclient.annotation.SteamRequestQueryParam;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The request representing <a href="https://partner.steamgames.com/doc/webapi/IPlayerService#GetSteamLevel">https://partner.steamgames.com/doc/webapi/IPlayerService#GetSteamLevel</a>
 */
@Builder
@Getter
@Setter
@ToString
public class SteamLevelRequest implements SteamWebApiRestrictedRequest {

    /**
     * Steamworks Web API user authentication key.
     *
     * Added automatically by the {@link io.github.aquerr.steamwebapiclient.SteamWebApiClient} if not added manually.
     */
    @SteamRequestQueryParam("key")
    @Builder.Default
    private String key = "";

    /**
     * The player we're asking about.
     */
    @SteamRequestQueryParam("steamid")
    private long steamId;

    @Override
    public void setApiKey(String apiKey) {
        this.key = apiKey;
    }

    @Override
    public String getApiKey() {
        return this.key;
    }
}
