package io.github.aquerr.steamwebapiclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Class representing the result of <a href="https://partner.steamgames.com/doc/webapi/IEconService#GetTradeOffersSummary">https://partner.steamgames.com/doc/webapi/IEconService#GetTradeOffersSummary</a>
 */
@Data
public class TradeOffersSummaryResponse implements SteamWebApiResponse {

    @JsonProperty("response")
    private Response response;

    @Data
    public static class Response {

        /**
         * Pending received trade offers count.
         */
        @JsonProperty("pending_received_count")
        private int pendingReceivedCount;

        /**
         * New received trade offers count.
         */
        @JsonProperty("new_received_count")
        private int newReceivedCount;

        /**
         * Updated received trade offers count.
         */
        @JsonProperty("updated_received_count")
        private int updatedReceivedCount;

        /**
         * Historical received trade offers count.
         */
        @JsonProperty("historical_received_count")
        private int historicalReceivedCount;

        /**
         * Pending sent trade offers count.
         */
        @JsonProperty("pending_sent_count")
        private int pendingSentCount;

        /**
         * Newly accepted sent trade offers count.
         */
        @JsonProperty("newly_accepted_sent_count")
        private int newlyAcceptedSentCount;

        /**
         * Updated sent trade offers count.
         */
        @JsonProperty("updated_sent_count")
        private int updatedSentCount;

        /**
         * Historical sent trade offers count.
         */
        @JsonProperty("historical_sent_count")
        private int historicalSentCount;

        /**
         * Escrow received trade offers count.
         */
        @JsonProperty("escrow_received_count")
        private int escrowReceivedCount;

        /**
         * Escrow sent trade offers count.
         */
        @JsonProperty("escrow_sent_count")
        private int escrowSentCount;
    }
}
