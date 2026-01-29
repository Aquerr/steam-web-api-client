package io.github.aquerr.steamwebapiclient.request;

import io.github.aquerr.steamwebapiclient.annotation.SteamRequestQueryParam;
import lombok.Data;

@Data
public class SupportedApiListRequest implements SteamWebApiRestrictedRequest {

    @SteamRequestQueryParam("key")
    private String key;

    @Override
    public String getApiKey() {
        return this.key;
    }

    @Override
    public void setApiKey(String apiKey) {
        this.key = apiKey;
    }
}
