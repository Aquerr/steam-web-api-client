package io.github.aquerr.steamwebapiclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the result of <a href="https://partner.steamgames.com/doc/webapi/IEconService#GetTradeOffer">https://partner.steamgames.com/doc/webapi/IEconService#GetTradeOffer</a>
 */
@Data
public class TradeOfferResponse implements SteamWebApiResponse {

    @JsonProperty("response")
    private TradeOfferWrapper response;

    @Data
    public static class TradeOfferWrapper {


        @JsonProperty("offer")
        private TradeOffer offer;

        @Data
        public static class TradeOffer {

            /**
             * Unique identifier for the trade offer.
             */
            @JsonProperty("tradeofferid")
            private String id;

            /**
             * The account id of other user.
             */
            @JsonProperty("accountid_other")
            private Long otherUserAccountId;

            /**
             * Message included by the creator of the trade offer.
             */
            @JsonProperty("message")
            private String message;

            /**
             * Trade offer expiration time in seconds.
             */
            @JsonProperty("expiration_time")
            private long expirationTimeSeconds;

            /**
             * Trade offer state.
             */
            @JsonProperty("trade_offer_state")
            private State state;

            /**
             * Trade items to be received.
             */
            @JsonProperty("items_to_receive")
            private List<Item> receivedItems = new ArrayList<>();

            /**
             * Trade items to be given.
             */
            @JsonProperty("items_to_give")
            private List<Item> givenItems = new ArrayList<>();

            /**
             * Is offer created by You.
             */
            @JsonProperty("is_our_offer")
            private boolean isOurOffer;

            /**
             * Trade offer creation time in seconds.
             */
            @JsonProperty("time_created")
            private long creationTimeSeconds;

            /**
             * Trade offer update time in seconds.
             */
            @JsonProperty("time_updated")
            private long updateTimeSeconds;

            /**
             * Is an offer automatically created from a real-time trade.
             */
            @JsonProperty("from_real_time_trade")
            private boolean fromRealTimeTrade;

            /**
             * Time in seconds when the trade hold period is supposed to be over for this trade offer.
             */
            @JsonProperty("escrow_end_date")
            private long escrowEndDateSeconds;

            /**
             * Confirmation method that applies to the user asking about the offer..
             */
            @JsonProperty("confirmation_method")
            private ConfirmationMethod confirmationMethod;

            /**
             * E-Result.
             */
            @JsonProperty("eresult")
            private int eResult;

            /**
             * Different states for a trade offer.
             */
            public enum State {
                /**
                 * Invalid.
                 */
                INVALID(1),
                /**
                 * This trade offer has been sent, neither party has acted on it yet.
                 */
                ACTIVE(2),
                /**
                 * This trade offer has been sent, neither party has acted on it yet.
                 */
                ACCEPTED(3),
                /**
                 * The recipient made a counter offer.
                 */
                COUNTERED(4),
                /**
                 * The trade offer was not accepted before the expiration date.
                 */
                EXPIRED(5),
                /**
                 * The sender cancelled the offer.
                 */
                CANCELED(6),
                /**
                 * The recipient declined the offer.
                 */
                DECLINED(7),
                /**
                 * Some of the items in the offer are no longer available (indicated by the missing flag in the output).
                 */
                INVALID_ITEMS(8),
                /**
                 * The offer hasn't been sent yet and is awaiting email/mobile confirmation. The offer is only visible to the sender.
                 */
                CREATED_NEEDS_CONFIRMATION(9),
                /**
                 * Either party canceled the offer via email/mobile. The offer is visible to both parties, even if the sender canceled it before it was sent.
                 */
                CANCELED_BY_SECOND_FACTOR(10),
                /**
                 * The trade has been placed on hold.
                 * The items involved in the trade have all been removed from both parties' inventories and will be automatically delivered in the future.
                 */
                IN_ESCROW(11);

                @JsonValue
                private final int code;

                State(int code) {
                    this.code = code;
                }

                public int getCode()
                {
                    return code;
                }
            }

            /**
             * These are the different methods in which a trade offer can be confirmed.
             */
            public enum ConfirmationMethod {
                /**
                 * Invalid.
                 */
                INVALID(1),
                /**
                 * An email was sent with details on how to confirm the trade offer.
                 */
                EMAIL(2),
                /**
                 * The trade offer may be confirmed via the mobile app.
                 */
                MOBILE_APP(3);

                @JsonValue
                private final int code;

                ConfirmationMethod(int code) {
                    this.code = code;
                }

                public int getCode()
                {
                    return code;
                }
            }

            @Data
            public static class Item {
                /**
                 * App id.
                 */
                @JsonProperty("appid")
                private int appId;

                /**
                 * Context id.
                 */
                @JsonProperty("contextid")
                private String contextId;

                /**
                 * Asset id.
                 */
                @JsonProperty("assetid")
                private String assetId;

                /**
                 * Class id.
                 */
                @JsonProperty("classid")
                private String classId;

                /**
                 * Instance id.
                 */
                @JsonProperty("instanceid")
                private String instanceId;

                /**
                 * The amount offered in the trade, for stackable items and currency.
                 */
                @JsonProperty("amount")
                private String amount;

                /**
                 * Is item is no longer present in the user's inventory.
                 */
                @JsonProperty("missing")
                private boolean missing;

                /**
                 * Represent Steam's determination of the item's value, in whole USD pennies. How this is determined is unknown.
                 */
                @JsonProperty("est_usd")
                private String estUSD;
            }

        }
    }
}
