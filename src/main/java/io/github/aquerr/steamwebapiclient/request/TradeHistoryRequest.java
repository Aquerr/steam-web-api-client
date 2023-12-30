package io.github.aquerr.steamwebapiclient.request;

import io.github.aquerr.steamwebapiclient.annotation.SteamRequestQueryParam;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The request representing <a href="https://partner.steamgames.com/doc/webapi/IEconService#GetTradeHistory">https://partner.steamgames.com/doc/webapi/IEconService#GetTradeHistory</a>
 */
@Builder
@Getter
@Setter
@ToString
public class TradeHistoryRequest implements SteamWebApiRestrictedRequest {

    /**
     * Steamworks Web API user authentication key.
     * <p>
     * Added automatically by the {@link io.github.aquerr.steamwebapiclient.SteamWebApiClient} if not added manually.
     */
    @SteamRequestQueryParam("key")
    @Builder.Default
    private String key = "";

    /**
     * Represents the number of requested trades.
     *
     * If <code>getDescriptions</code> is set to false then maxTrades limit = 500.
     * Otherwise, if <code>getDescriptions</code> is true, then limit = 100.
     */
    @SteamRequestQueryParam("max_trades")
    private int maxTrades;

    /**
     * If set, the item display data for the items included in the returned trades will also be returned.
     */
    @SteamRequestQueryParam("getDescriptions")
    private boolean getDescriptions;

    /**
     * The language (e.g. english, polish, french etc.) to use when loading item display data.
     */
    @SteamRequestQueryParam("language")
    private String language;

    /**
     * The time of the last trade shown on the previous page of results, or the time of the first trade if navigating back.
     */
    @SteamRequestQueryParam("start_after_time")
    private int startAfterTime;

    /**
     * The trade id shown on the previous page of results, or the ID of the first trade if navigating back.
     */
    @SteamRequestQueryParam("start_after_tradeid")
    private long startAfterTradeId;

    /**
     * Return the previous max_trades trades before the start time and ID.
     */
    @SteamRequestQueryParam("navigating_back")
    private boolean navigatingBack;

    /**
     * If set, trades in status:
     *
     * {@link io.github.aquerr.steamwebapiclient.response.TradeHistoryResponse.TradeHistory.Trade.Status#FAILED}
     * {@link io.github.aquerr.steamwebapiclient.response.TradeHistoryResponse.TradeHistory.Trade.Status#ROLLBACK_FAILED}
     * {@link io.github.aquerr.steamwebapiclient.response.TradeHistoryResponse.TradeHistory.Trade.Status#ROLLBACK_ABANDONED}
     * {@link io.github.aquerr.steamwebapiclient.response.TradeHistoryResponse.TradeHistory.Trade.Status#ESCROW_ROLLBACK}
     *
     * will be included in the response.
     */
    @SteamRequestQueryParam("include_failed")
    private boolean includeFailed;

    /**
     * If set, the total number of trades the account has participated in will be included in the response.
     */
    @SteamRequestQueryParam("include_total")
    private boolean includeTotal;

    @Override
    public void setApiKey(String apiKey) {
        this.key = apiKey;
    }

    @Override
    public String getApiKey() {
        return this.key;
    }
}
