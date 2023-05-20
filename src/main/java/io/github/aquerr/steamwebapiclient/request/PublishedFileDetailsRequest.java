package io.github.aquerr.steamwebapiclient.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * The request representing <a href="https://partner.steamgames.com/doc/webapi/ISteamRemoteStorage#GetPublishedFileDetails">https://partner.steamgames.com/doc/webapi/ISteamRemoteStorage#GetPublishedFileDetails</a>
 */
@Data
@AllArgsConstructor
public class PublishedFileDetailsRequest implements SteamWebApiRequest {

    /**
     * The list of file ids to get the details for.
     */
    private List<Long> publishedFileIds;

    /**
     * Gets item count (or more exactly the size of publishedFileIds).
     *
     * @return the size of publishedFileIds list.
     */
    public int getItemCount()
    {
        return publishedFileIds.size();
    }
}
