package io.github.aquerr.steamwebapiclient;

import io.github.aquerr.steamwebapiclient.request.PlayerRecentlyPlayedGamesRequest;
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

    public PlayerRecentlyPlayedGamesResponse getRecentlyPlayedGames(PlayerRecentlyPlayedGamesRequest request) {
        return this.steamHttpClient.get(
                SteamWebApiInterface.I_PLAYER_SERVICE,
                SteamWebApiInterface.Method.GET_RECENTLY_PLAYED_GAMES,
                API_VERSION_1,
                request,
                PlayerRecentlyPlayedGamesResponse.class
        );
    }

}
