package io.github.aquerr.steamwebapiclient.request;

import io.github.aquerr.steamwebapiclient.annotation.SteamRequestQueryParam;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * The request representing <a href="https://partner.steamgames.com/doc/webapi/IGameServersService#GetServerSteamIDsByIP">https://partner.steamgames.com/doc/webapi/IGameServersService#GetServerSteamIDsByIP</a>
 */
@Builder
@Getter
@Setter
@ToString
public class ServerSteamIDsByIPRequest implements SteamWebApiRestrictedRequest {

    /**
     * Steamworks Web API user authentication key.
     * <p>
     * Added automatically by the {@link io.github.aquerr.steamwebapiclient.SteamWebApiClient} if not added manually.
     */
    @SteamRequestQueryParam("key")
    @Builder.Default
    private String key = "";

    /**
     * The ips of servers.
     */
    @SteamRequestQueryParam("server_ips")
    private List<String> serverIps;

    @Override
    public void setApiKey(String apiKey) {
        this.key = apiKey;
    }

    @Override
    public String getApiKey() {
        return this.key;
    }
}
