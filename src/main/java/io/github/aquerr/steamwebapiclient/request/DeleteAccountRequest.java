package io.github.aquerr.steamwebapiclient.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.aquerr.steamwebapiclient.annotation.SteamRequestQueryParam;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The request representing <a href="https://partner.steamgames.com/doc/webapi/IGameServersService#DeleteAccount">https://partner.steamgames.com/doc/webapi/IGameServersService#DeleteAccount</a>
 */
@Builder
@Getter
@Setter
@ToString
public class DeleteAccountRequest implements SteamWebApiRestrictedRequest {

    /**
     * Steamworks Web API user authentication key.
     * <p>
     * Added automatically by the {@link io.github.aquerr.steamwebapiclient.SteamWebApiClient} if not added manually.
     */
    @SteamRequestQueryParam("key")
    @Builder.Default
    @JsonIgnore
    private String key = "";

    /**
     * The steam identifier of the GSLT (Game Server Login Token).
     */
    @JsonProperty("steamid")
    private String steamId;

    @JsonIgnore
    @Override
    public void setApiKey(String apiKey) {
        this.key = apiKey;
    }

    @JsonIgnore
    @Override
    public String getApiKey() {
        return this.key;
    }
}
