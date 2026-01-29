package io.github.aquerr.steamwebapiclient.request;

import io.github.aquerr.steamwebapiclient.annotation.SteamRequestQueryParam;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The request representing <a href="https://partner.steamgames.com/doc/webapi/IGameServersService#GetAccountList">https://partner.steamgames.com/doc/webapi/IGameServersService#GetAccountList</a>
 */
@Builder
@Getter
@Setter
@ToString
public class AccountListRequest implements SteamWebApiRestrictedRequest {

    /**
     * Steamworks Web API user authentication key.
     * <p>
     * Added automatically by the {@link io.github.aquerr.steamwebapiclient.SteamWebApiClient} if not added manually.
     */
    @SteamRequestQueryParam("key")
    @Builder.Default
    private String key = "";

    @Override
    public String getApiKey() {
        return this.key;
    }

    @Override
    public void setApiKey(String apiKey) {
        this.key = apiKey;
    }
}
