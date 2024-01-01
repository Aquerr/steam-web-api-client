package io.github.aquerr.steamwebapiclient.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.aquerr.steamwebapiclient.annotation.SteamRequestQueryParam;
import lombok.Builder;
import lombok.Data;

/**
 * The request representing <a href="https://partner.steamgames.com/doc/webapi/IGameServersService#CreateAccount">https://partner.steamgames.com/doc/webapi/IGameServersService#CreateAccount</a>
 */
@Data
@Builder
public class CreateAccountRequest implements SteamWebApiRestrictedRequest {

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
     * The id of the app that the GSLT (Game Server Login Token) should be created for.
     */
    @JsonProperty("appid")
    private int appId;

    /**
     * The memo/description for the GSLT (Game Server Login Token).
     */
    @JsonProperty("memo")
    private String memo;

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
