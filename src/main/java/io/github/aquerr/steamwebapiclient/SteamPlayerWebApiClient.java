package io.github.aquerr.steamwebapiclient;

import io.github.aquerr.steamwebapiclient.request.OwnedGamesRequest;
import io.github.aquerr.steamwebapiclient.request.PlayerRecentlyPlayedGamesRequest;
import io.github.aquerr.steamwebapiclient.response.OwnedGamesResponse;
import io.github.aquerr.steamwebapiclient.response.PlayerRecentlyPlayedGamesResponse;
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

}
