package io.github.aquerr.steamwebapiclient;

import io.github.aquerr.steamwebapiclient.exception.ClientException;
import io.github.aquerr.steamwebapiclient.request.TradeHistoryRequest;
import io.github.aquerr.steamwebapiclient.request.TradeOfferRequest;
import io.github.aquerr.steamwebapiclient.response.TradeHistoryResponse;
import io.github.aquerr.steamwebapiclient.response.TradeOfferResponse;
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
     *
     * @throws ClientException if:
     * - request could not be sent due to an error
     * - response has not been received
     * - response could not be parsed
     * - any other error occurs
     */
    public TradeHistoryResponse getTradeHistory(TradeHistoryRequest request) throws ClientException {
        return this.steamHttpClient.get(
                SteamWebApiInterfaceMethod.I_ECON_SERVICE_GET_TRADE_HISTORY,
                API_VERSION_1,
                request,
                TradeHistoryResponse.class
        );
    }

    /**
     * Gets a specific trade offer with the WebAPI key.
     *
     * @throws ClientException if:
     * - request could not be sent due to an error
     * - response has not been received
     * - response could not be parsed
     * - any other error occurs
     */
    public TradeOfferResponse getTradeOffer(TradeOfferRequest request) throws ClientException {
        return this.steamHttpClient.get(
                SteamWebApiInterfaceMethod.I_ECON_SERVICE_GET_TRADE_OFFER,
                API_VERSION_1,
                request,
                TradeOfferResponse.class
        );
    }
}
