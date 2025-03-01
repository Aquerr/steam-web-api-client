package io.github.aquerr.steamwebapiclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the result of <a href="https://partner.steamgames.com/doc/webapi/ISteamRemoteStorage#GetPublishedFileDetails">https://partner.steamgames.com/doc/webapi/ISteamRemoteStorage#GetPublishedFileDetails</a>
 */
@Data
public class PublishedFileDetailsResponse implements SteamWebApiResponse {

    /**
     * The response.
     */
    @JsonProperty("response")
    private PublishedFileDetailsResponse.QueryFilesResponse response;

    @Data
    public static class QueryFilesResponse {

        /**
         * The result count. (Almost always, if not, equal to 1)
         */
        @JsonProperty("result")
        private int result;

        /**
         * The number of results that have been found. Basically the size of {@link PublishedFileDetailsResponse.QueryFilesResponse#publishedFileDetails} list.
         */
        @JsonProperty("resultcount")
        private int resultCount;

        /**
         * The list of found published file details.
         */
        @JsonProperty("publishedfiledetails")
        private List<io.github.aquerr.steamwebapiclient.response.shared.PublishedFileDetails> publishedFileDetails = new ArrayList<>();
    }
}
