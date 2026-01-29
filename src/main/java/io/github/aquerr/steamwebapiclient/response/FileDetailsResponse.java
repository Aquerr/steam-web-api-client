package io.github.aquerr.steamwebapiclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.aquerr.steamwebapiclient.response.shared.PublishedFileDetails;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FileDetailsResponse implements SteamWebApiResponse {
    /**
     * The response.
     */
    @JsonProperty("response")
    private QueryFilesResponse response;

    @Data
    public static class QueryFilesResponse implements SteamWebApiResponse {
        /**
         * The list of found published file details.
         */
        @JsonProperty("publishedfiledetails")
        private List<PublishedFileDetails> publishedFileDetails = new ArrayList<>();
    }
}
