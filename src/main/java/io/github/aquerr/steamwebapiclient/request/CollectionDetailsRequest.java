package io.github.aquerr.steamwebapiclient.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * The request representing <a href="https://partner.steamgames.com/doc/webapi/ISteamRemoteStorage#GetCollectionDetails">https://partner.steamgames.com/doc/webapi/ISteamRemoteStorage#GetCollectionDetails</a>
 */
@Data
@AllArgsConstructor
public class CollectionDetailsRequest implements SteamWebApiRequest {

    /**
     * The list of collection ids to get the details for.
     */
    private List<Long> collectionIds;

    /**
     * Gets collection count (or more exactly the size of collectionIds).
     *
     * @return the size of collectionIds list.
     */
    public int getCollectionCount()
    {
        return collectionIds.size();
    }
}
