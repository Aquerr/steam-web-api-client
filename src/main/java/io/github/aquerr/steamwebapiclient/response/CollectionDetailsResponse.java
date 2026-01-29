package io.github.aquerr.steamwebapiclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class representing the result of <a href="https://partner.steamgames.com/doc/webapi/ISteamRemoteStorage#GetCollectionDetails">https://partner.steamgames.com/doc/webapi/ISteamRemoteStorage#GetCollectionDetails</a>
 */
@Data
public class CollectionDetailsResponse implements SteamWebApiResponse {

    /**
     * The response.
     */
    @JsonProperty("response")
    private Response response;

    @Data
    public static class Response {

        /**
         * The result count. (Almost always equal to 1)
         */
        @JsonProperty("result")
        private int result;

        /**
         * The number of successfully retrieved results for collection ids.
         * <p>
         * E.g. If 2 collections were asked for details and both of them were found
         * and returned their details then this field will be equal to 2.
         * <p>
         * If only one was found and returned values then this field will be equal to 1.
         */
        @JsonProperty("resultcount")
        private int resultCount;

        @JsonProperty("collectiondetails")
        private List<CollectionDetails> collectionDetails = new ArrayList<>();

        @Data
        public static class CollectionDetails {

            /**
             * Collection id.
             */
            @JsonProperty("publishedfileid")
            private long collectionId;

            /**
             * The type of result.
             * <p>
             * To get enum result use {@link CollectionDetails#getResultAsEnum()}
             *
             * @see ResultType for more info.
             */
            @JsonProperty("result")
            private int result;

            /**
             * The list of found collection items (also called "children").
             */
            @JsonProperty("children")
            private List<CollectionDetails.Item> items = new ArrayList<>();

            /**
             * The type of result.
             *
             * @see ResultType for more info.
             */
            public ResultType getResultAsEnum() {
                return ResultType.findByCode(result);
            }

            public enum ResultType {
                FOUND(1),
                NOT_FOUND(9),
                UNKNOWN(-1);

                private final int code;

                ResultType(int type) {
                    this.code = type;
                }

                public static ResultType findByCode(int resultCode) {
                    return Arrays.stream(ResultType.values())
                            .filter(resultEnum -> resultCode == resultEnum.getCode())
                            .findFirst()
                            .orElse(UNKNOWN);
                }

                public int getCode() {
                    return code;
                }
            }

            @Data
            public static class Item {

                /**
                 * The id of published file.
                 */
                @JsonProperty("publishedfileid")
                private long publishedFileId;

                /**
                 * The order of item within the collection.
                 */
                @JsonProperty("sortorder")
                private int order;

                /**
                 * The type of item/file.
                 */
                @JsonProperty("filetype")
                private int fileType;
            }
        }
    }
}
