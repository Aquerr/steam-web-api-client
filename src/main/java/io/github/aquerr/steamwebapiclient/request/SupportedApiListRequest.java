package io.github.aquerr.steamwebapiclient.request;

import io.github.aquerr.steamwebapiclient.annotation.SteamRequestQueryParam;
import lombok.Data;

@Data
public class SupportedApiListRequest implements SteamWebApiRestrictedRequest {

    @SteamRequestQueryParam(name = "key")
    private String key;

    @Override
    public void setApiKey(String apiKey) {
        this.key = apiKey;
    }

    @Override
    public String getApiKey() {
        return this.key;
    }
}
