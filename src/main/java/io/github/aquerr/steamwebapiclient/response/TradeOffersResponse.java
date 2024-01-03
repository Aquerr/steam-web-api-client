package io.github.aquerr.steamwebapiclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.aquerr.steamwebapiclient.response.shared.AssetInformation;
import io.github.aquerr.steamwebapiclient.response.shared.TradeOffer;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the result of <a href="https://partner.steamgames.com/doc/webapi/IEconService#GetTradeOffers">https://partner.steamgames.com/doc/webapi/IEconService#GetTradeOffers</a>
 */
@Data
public class TradeOffersResponse implements SteamWebApiResponse {

    @JsonProperty("response")
    private Response response;

    @Data
    public static class Response {

        /**
         * List containing information about sent trades.
         */
        @JsonProperty("trade_offers_sent")
        private List<TradeOffer> sentOffers;

        /**
         * List containing information about received trades.
         */
        @JsonProperty("trade_offers_received")
        private List<TradeOffer> receivedOffers;

        /**
         * List containing information about items in trades.
         */
        //TODO: We should try to populate assets in trades with these.
        @JsonProperty("descriptions")
        private List<AssetInformation> assetInformation = new ArrayList<>();

        /**
         * Unknown.
         */
        @JsonProperty("next_cursor")
        private int nextCursor;

    }
}
