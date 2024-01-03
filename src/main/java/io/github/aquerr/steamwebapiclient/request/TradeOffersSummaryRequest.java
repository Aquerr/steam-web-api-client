package io.github.aquerr.steamwebapiclient.request;

import io.github.aquerr.steamwebapiclient.annotation.SteamRequestQueryParam;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The request representing <a href="https://partner.steamgames.com/doc/webapi/IEconService#GetTradeOffersSummary">https://partner.steamgames.com/doc/webapi/IEconService#GetTradeOffersSummary</a>
 */
@Builder
@Getter
@Setter
@ToString
public class TradeOffersSummaryRequest implements SteamWebApiRestrictedRequest {

    /**
     * Steamworks Web API user authentication key.
     * <p>
     * Added automatically by the {@link io.github.aquerr.steamwebapiclient.SteamWebApiClient} if not added manually.
     */
    @SteamRequestQueryParam("key")
    @Builder.Default
    private String key = "";

    /**
     * The time the user last visited. If not passed, will use the time the user last visited the trade offer page.
     */
    @SteamRequestQueryParam("time_last_visit")
    private long timeLastVisitSeconds;

    @Override
    public void setApiKey(String apiKey) {
        this.key = apiKey;
    }

    @Override
    public String getApiKey() {
        return this.key;
    }
}
