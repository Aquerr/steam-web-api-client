package io.github.aquerr.steamwebapiclient;

import io.github.aquerr.steamwebapiclient.exception.ClientException;
import io.github.aquerr.steamwebapiclient.request.BadgesRequest;
import io.github.aquerr.steamwebapiclient.request.CommunityBadgeProgressRequest;
import io.github.aquerr.steamwebapiclient.request.OwnedGamesRequest;
import io.github.aquerr.steamwebapiclient.request.PlayerRecentlyPlayedGamesRequest;
import io.github.aquerr.steamwebapiclient.request.SteamLevelRequest;
import io.github.aquerr.steamwebapiclient.response.BadgesResponse;
import io.github.aquerr.steamwebapiclient.response.CommunityBadgeProgressResponse;
import io.github.aquerr.steamwebapiclient.response.OwnedGamesResponse;
import io.github.aquerr.steamwebapiclient.response.PlayerRecentlyPlayedGamesResponse;
import io.github.aquerr.steamwebapiclient.response.SteamLevelResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import static io.github.aquerr.steamwebapiclient.SteamWebApiClient.API_VERSION_1;

/**
 * Steam web api player client.
 */
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class SteamPlayerWebApiClient {

    private final SteamHttpClient steamHttpClient;

    /**
     * 	Gets information about a player's recently played games.
     *
     * @throws ClientException if:
     * - request could not be sent due to an error
     * - response has not been received
     * - response could not be parsed
     * - any other error occurs
     */
    public PlayerRecentlyPlayedGamesResponse getRecentlyPlayedGames(PlayerRecentlyPlayedGamesRequest request) throws ClientException {
        return this.steamHttpClient.get(
                SteamWebApiInterfaceMethod.I_PLAYER_SERVICE_GET_RECENTLY_PLAYED_GAMES,
                API_VERSION_1,
                request,
                PlayerRecentlyPlayedGamesResponse.class
        );
    }

    /**
     * 	Return a list of games owned by the player.
     *
     * @throws ClientException if:
     * - request could not be sent due to an error
     * - response has not been received
     * - response could not be parsed
     * - any other error occurs
     */
    public OwnedGamesResponse getOwnedGames(OwnedGamesRequest request) throws ClientException {
        return this.steamHttpClient.get(
                SteamWebApiInterfaceMethod.I_PLAYER_SERVICE_GET_OWNED_GAMES,
                API_VERSION_1,
                request,
                OwnedGamesResponse.class
        );
    }

    /**
     * 	Returns the Steam Level of a user.
     *
     * @throws ClientException if:
     * - request could not be sent due to an error
     * - response has not been received
     * - response could not be parsed
     * - any other error occurs
     */
    public SteamLevelResponse getSteamLevel(SteamLevelRequest request) throws ClientException {
        return this.steamHttpClient.get(
                SteamWebApiInterfaceMethod.I_PLAYER_SERVICE_GET_STEAM_LEVEL,
                API_VERSION_1,
                request,
                SteamLevelResponse.class
        );
    }

    /**
     * 	Gets badges that are owned by a specific user.
     *
     * @throws ClientException if:
     * - request could not be sent due to an error
     * - response has not been received
     * - response could not be parsed
     * - any other error occurs
     */
    public BadgesResponse getBadges(BadgesRequest request) throws ClientException {
        return this.steamHttpClient.get(
                SteamWebApiInterfaceMethod.I_PLAYER_SERVICE_GET_BADGES,
                API_VERSION_1,
                request,
                BadgesResponse.class
        );
    }

    /**
     * 	Gets all the quests needed to get the specified badge, and which are completed.
     *
     * @throws ClientException if:
     * - request could not be sent due to an error
     * - response has not been received
     * - response could not be parsed
     * - any other error occurs
     */
    public CommunityBadgeProgressResponse getCommunityBadgeProgress(CommunityBadgeProgressRequest request) throws ClientException {
        return this.steamHttpClient.get(
                SteamWebApiInterfaceMethod.I_PLAYER_SERVICE_GET_COMMUNITY_BADGE_PROGRESS,
                API_VERSION_1,
                request,
                CommunityBadgeProgressResponse.class
        );
    }

}
