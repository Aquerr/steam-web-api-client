package io.github.aquerr.steamwebapiclient.request;

import io.github.aquerr.steamwebapiclient.annotation.SteamRequestQueryParam;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * The request representing <a href="https://partner.steamgames.com/doc/webapi/IGameServersService#GetServerIPsBySteamID">https://partner.steamgames.com/doc/webapi/IGameServersService#GetServerIPsBySteamID</a>
 */
@Builder
@Getter
@Setter
@ToString
public class ServerIPsBySteamIdRequest implements SteamWebApiRestrictedRequest {

    /**
     * Steamworks Web API user authentication key.
     * <p>
     * Added automatically by the {@link io.github.aquerr.steamwebapiclient.SteamWebApiClient} if not added manually.
     */
    @SteamRequestQueryParam("key")
    @Builder.Default
    private String key = "";

    /**
     * The steam identifiers of the GSLT (Game Server Login Token).
     */
    @SteamRequestQueryParam("server_steamids")
    private List<String> serverSteamIds;

    @Override
    public void setApiKey(String apiKey) {
        this.key = apiKey;
    }

    @Override
    public String getApiKey() {
        return this.key;
    }
}
