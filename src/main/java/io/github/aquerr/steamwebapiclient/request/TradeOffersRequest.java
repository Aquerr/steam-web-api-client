package io.github.aquerr.steamwebapiclient.request;

import io.github.aquerr.steamwebapiclient.annotation.SteamRequestQueryParam;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The request representing <a href="https://partner.steamgames.com/doc/webapi/IEconService#GetTradeOffers">https://partner.steamgames.com/doc/webapi/IEconService#GetTradeOffers</a>
 */
@Builder
@Getter
@Setter
@ToString
public class TradeOffersRequest implements SteamWebApiRestrictedRequest {

    /**
     * Steamworks Web API user authentication key.
     * <p>
     * Added automatically by the {@link io.github.aquerr.steamwebapiclient.SteamWebApiClient} if not added manually.
     */
    @SteamRequestQueryParam("key")
    @Builder.Default
    private String key = "";

    /**
     * Request the list of sent offers.
     */
    @SteamRequestQueryParam("get_sent_offers")
    private boolean getSentOffers;

    /**
     * Request the list of received offers.
     */
    @SteamRequestQueryParam("get_received_offers")
    private boolean getReceivedOffers;

    /**
     * If set, the item display data for the items included in the returned trade offers will also be returned.
     */
    @SteamRequestQueryParam("get_descriptions")
    private boolean getDescriptions;

    /**
     * The language (e.g. english, polish, french etc.) to use when loading item display data.
     */
    @SteamRequestQueryParam("language")
    private String language;

    /**
     * Indicates we should only return offers which are still active, or offers that have changed in state since the time_historical_cutoff.
     */
    @SteamRequestQueryParam("active_only")
    private boolean activeOnly;

    /**
     * Indicates we should only return offers which are not active.
     */
    @SteamRequestQueryParam("historical_only")
    private boolean historicalOnly;

    /**
     * When active_only is set, offers updated since this time will also be returned.
     */
    @SteamRequestQueryParam("time_historical_cutoff")
    private int timeHistoricalCutOff;

    @Override
    public String getApiKey() {
        return this.key;
    }

    @Override
    public void setApiKey(String apiKey) {
        this.key = apiKey;
    }
}
