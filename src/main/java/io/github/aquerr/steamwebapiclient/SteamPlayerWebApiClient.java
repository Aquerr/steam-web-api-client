package io.github.aquerr.steamwebapiclient;

import io.github.aquerr.steamwebapiclient.request.BadgesRequest;
import io.github.aquerr.steamwebapiclient.request.OwnedGamesRequest;
import io.github.aquerr.steamwebapiclient.request.PlayerRecentlyPlayedGamesRequest;
import io.github.aquerr.steamwebapiclient.request.SteamLevelRequest;
import io.github.aquerr.steamwebapiclient.response.BadgesResponse;
import io.github.aquerr.steamwebapiclient.response.OwnedGamesResponse;
import io.github.aquerr.steamwebapiclient.response.PlayerRecentlyPlayedGamesResponse;
import io.github.aquerr.steamwebapiclient.response.SteamLevelResponse;
import io.github.aquerr.steamwebapiclient.util.SteamHttpClient;
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
     */
    public PlayerRecentlyPlayedGamesResponse getRecentlyPlayedGames(PlayerRecentlyPlayedGamesRequest request) {
        return this.steamHttpClient.get(
                SteamWebApiInterface.I_PLAYER_SERVICE,
                SteamWebApiInterface.Method.GET_RECENTLY_PLAYED_GAMES,
                API_VERSION_1,
                request,
                PlayerRecentlyPlayedGamesResponse.class
        );
    }

    /**
     * 	Return a list of games owned by the player.
     */
    public OwnedGamesResponse getOwnedGames(OwnedGamesRequest request) {
        return this.steamHttpClient.get(
                SteamWebApiInterface.I_PLAYER_SERVICE,
                SteamWebApiInterface.Method.GET_OWNED_GAMES,
                API_VERSION_1,
                request,
                OwnedGamesResponse.class
        );
    }

    /**
     * 	Returns the Steam Level of a user.
     */
    public SteamLevelResponse getSteamLevel(SteamLevelRequest request) {
        return this.steamHttpClient.get(
                SteamWebApiInterface.I_PLAYER_SERVICE,
                SteamWebApiInterface.Method.GET_STEAM_LEVEL,
                API_VERSION_1,
                request,
                SteamLevelResponse.class
        );
    }

    /**
     * 	Gets badges that are owned by a specific user.
     */
    public BadgesResponse getBadges(BadgesRequest request) {
        return this.steamHttpClient.get(
                SteamWebApiInterface.I_PLAYER_SERVICE,
                SteamWebApiInterface.Method.GET_BADGES,
                API_VERSION_1,
                request,
                BadgesResponse.class
        );
    }

}
