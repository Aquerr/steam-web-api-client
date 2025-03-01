package io.github.aquerr.steamwebapiclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the result of <a href="https://partner.steamgames.com/doc/webapi/IPublishedFileService#QueryFiles">https://partner.steamgames.com/doc/webapi/IPublishedFileService#QueryFiles</a>
 */
@Data
public class WorkShopQueryResponse implements SteamWebApiResponse {

    /**
     * The response.
     */
    @JsonProperty("response")
    private QueryFilesResponse response;

    @Data
    public static class QueryFilesResponse {

        /**
         * The pointer to next page.
         */
        @JsonProperty("next_cursor")
        private String nextCursor;

        /**
         * The list of found workshop items.
         */
        @JsonProperty("publishedfiledetails")
        private List<io.github.aquerr.steamwebapiclient.response.shared.PublishedFileDetails> publishedFileDetails = new ArrayList<>();
    }
}
