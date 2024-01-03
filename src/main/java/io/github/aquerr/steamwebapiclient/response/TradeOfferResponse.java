package io.github.aquerr.steamwebapiclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.aquerr.steamwebapiclient.response.shared.TradeOffer;
import lombok.Data;

/**
 * Class representing the result of <a href="https://partner.steamgames.com/doc/webapi/IEconService#GetTradeOffer">https://partner.steamgames.com/doc/webapi/IEconService#GetTradeOffer</a>
 */
@Data
public class TradeOfferResponse implements SteamWebApiResponse {

    @JsonProperty("response")
    private Response response;

    @Data
    public static class Response {

        @JsonProperty("offer")
        private TradeOffer offer;
    }
}
