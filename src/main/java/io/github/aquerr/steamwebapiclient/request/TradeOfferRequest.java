package io.github.aquerr.steamwebapiclient.request;

import io.github.aquerr.steamwebapiclient.annotation.SteamRequestQueryParam;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The request representing <a href="https://partner.steamgames.com/doc/webapi/IEconService#GetTradeOffer">https://partner.steamgames.com/doc/webapi/IEconService#GetTradeOffer</a>
 */
@Builder
@Getter
@Setter
@ToString
public class TradeOfferRequest implements SteamWebApiRestrictedRequest {

    /**
     * Steamworks Web API user authentication key.
     * <p>
     * Added automatically by the {@link io.github.aquerr.steamwebapiclient.SteamWebApiClient} if not added manually.
     */
    @SteamRequestQueryParam("key")
    @Builder.Default
    private String key = "";

    /**
     * Trade offer id.
     */
    @SteamRequestQueryParam("tradeofferid")
    private long tradeOfferId;

    /**
     * The language (e.g. english, polish, french etc.) to use when loading item display data.
     */
    @SteamRequestQueryParam("language")
    private String language;

    @Override
    public void setApiKey(String apiKey) {
        this.key = apiKey;
    }

    @Override
    public String getApiKey() {
        return this.key;
    }
}
