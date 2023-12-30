package io.github.aquerr.steamwebapiclient;

import io.github.aquerr.steamwebapiclient.request.TradeHistoryRequest;
import io.github.aquerr.steamwebapiclient.response.TradeHistoryResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import static io.github.aquerr.steamwebapiclient.SteamWebApiClient.API_VERSION_1;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class SteamEconServiceWebApiClient {

    private final SteamHttpClient steamHttpClient;

    /**
     * Gets a history of trades that you have been involved in as identified by the account associated with the WebAPI key.
     *
     * You cannot call this API for accounts other than your own.
     */
    public TradeHistoryResponse getTradeHistory(TradeHistoryRequest request) {
        return this.steamHttpClient.get(
                SteamWebApiInterfaceMethod.I_ECON_SERVICE_GET_TRADE_HISTORY,
                API_VERSION_1,
                request,
                TradeHistoryResponse.class
        );
    }
}
