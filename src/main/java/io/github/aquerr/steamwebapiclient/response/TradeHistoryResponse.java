package io.github.aquerr.steamwebapiclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.aquerr.steamwebapiclient.response.shared.AssetInformation;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the result of <a href="https://partner.steamgames.com/doc/webapi/IEconService#GetTradeHistory">https://partner.steamgames.com/doc/webapi/IEconService#GetTradeHistory</a>
 */
@Data
public class TradeHistoryResponse implements SteamWebApiResponse {

    @JsonProperty("response")
    private TradeHistory response;

    @Data
    public static class TradeHistory {

        /**
         * Total number of trades performed by the account.
         */
        @JsonProperty("total_trades")
        private int totalTrades;

        /**
         * Determines if more trades are available. (In other words, if response does not contain all trades)
         */
        @JsonProperty("more")
        private boolean more;

        /**
         * List of trades with basic info.
         */
        @JsonProperty("trades")
        private List<Trade> trades = new ArrayList<>();

        /**
         * List containing information about items in trades.
         */
        //TODO: We should try to populate assets in trades with these.
        @JsonProperty("descriptions")
        private List<AssetInformation> assetInformation = new ArrayList<>();

        @Data
        public static class Trade {

            /**
             * The trade id.
             */
            @JsonProperty("tradeid")
            private String tradeId;

            /**
             * The steam id of other user/account.
             */
            @JsonProperty("steamid_other")
            private String otherUserSteamId;

            /**
             * The time of the trade.
             */
            @JsonProperty("time_init")
            private int timeInit;

            @JsonProperty("status")
            private Status status;

            @JsonProperty("assets_received")
            private List<Asset> assetsReceived = new ArrayList<>();

            public enum Status {

                /**
                 * Trade has just been accepted/confirmed, but no work has been done yet
                 */
                INIT(0),

                /**
                 * Steam is about to start committing the trade
                 */
                PRE_COMMITED(1),

                /**
                 * The items have been exchanged
                 */
                COMMITED(2),

                /**
                 * All work is finished
                 */
                COMPLETE(3),

                /**
                 * Something went wrong after Init, but before Committed, and the trade has been rolled back
                 */
                FAILED(4),

                /**
                 * A support person rolled back the trade for one side
                 */
                PARTIAL_SUPPORT_ROLLBACK(5),

                /**
                 * A support person rolled back the trade for both sides
                 */
                FULL_SUPPORT_ROLLBACK(6),

                /**
                 * A support person rolled back the trade for some set of items
                 */
                SUPPORT_ROLLBACK_SELECTIVE(7),

                /**
                 * We tried to roll back the trade when it failed, but haven't managed to do that for all items yet
                 */
                ROLLBACK_FAILED(8),

                /**
                 * We tried to roll back the trade, but some failure didn't go away and we gave up
                 */
                ROLLBACK_ABANDONED(9),

                /**
                 * Trade is in escrow
                 */
                IN_ESCROW(10),

                /**
                 * A trade in escrow was rolled back
                 */
                ESCROW_ROLLBACK(11);

                @JsonValue
                private final int code;

                Status(int code) {
                    this.code = code;
                }

                public int getCode()
                {
                    return code;
                }
            }

            @Data
            public static class Asset {

                /**
                 * The id of the app.
                 */
                @JsonProperty("appid")
                private int appId;

                /**
                 * The context id.
                 */
                @JsonProperty("contextid")
                private int contextId;

                /**
                 * The id of the asset.
                 */
                @JsonProperty("assetid")
                private String assetId;

                /**
                 * The amount.
                 */
                @JsonProperty("amount")
                private int amount;

                /**
                 * The class id of the asset.
                 */
                @JsonProperty("classid")
                private String classId;

                /**
                 * The instance if of the asset.
                 */
                @JsonProperty("instanceid")
                private String instanceId;

                /**
                 * The new asset id.
                 */
                @JsonProperty("new_assetid")
                private String newAssetId;

                /**
                 * The new context id.
                 */
                @JsonProperty("new_contextid")
                private int newContextId;
            }
        }
    }
}
