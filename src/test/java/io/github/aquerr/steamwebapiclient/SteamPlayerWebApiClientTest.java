package io.github.aquerr.steamwebapiclient;


import io.github.aquerr.steamwebapiclient.request.PlayerRecentlyPlayedGamesRequest;
import io.github.aquerr.steamwebapiclient.response.PlayerRecentlyPlayedGamesResponse;
import io.github.aquerr.steamwebapiclient.util.SteamHttpClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static io.github.aquerr.steamwebapiclient.SteamWebApiClient.API_VERSION_1;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

class SteamPlayerWebApiClientTest {

    private static final String KEY = "KEY";
    private static final long STEAM_ID = 1L;
    private static final int COUNT = 1;
    private static final int GAME_DETAIL_APP_ID = 1;
    private static final String GAME_DETAIL_NAME = "GAME_DETAIL_NAME";
    private static final int GAME_DETAIL_PLAYTIME_2_WEEKS = 0;
    private static final int GAME_DETAIL_PLAYTIME_FOREVER = 0;
    private static final String GAME_DETAIL_IMG_ICON_URL = "GAME_DETAIL_IMG_ICON_URL";
    private static final int GAME_DETAIL_PLAYTIME_WINDOWS_FOREVER = 0;
    private static final int GAME_DETAIL_PLAYTIME_MAC_FOREVER = 0;
    private static final int GAME_DETAIL_LINUX_FOREVER = 0;

    @Mock
    private SteamHttpClient steamHttpClient;

    @InjectMocks
    private SteamPlayerWebApiClient client;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void shouldGetRecentlyPlayedGames() {
        //given
        PlayerRecentlyPlayedGamesRequest request = preparePlayerGetRecentlyPlayedGamesRequest();
        given(client.getRecentlyPlayedGames(request)).willReturn(preparePlayerGetRecentlyPlayedGamesResponse());

        //when
        PlayerRecentlyPlayedGamesResponse response = client.getRecentlyPlayedGames(request);

        //then
        verify(steamHttpClient).get(
                SteamWebApiInterface.I_PLAYER_SERVICE,
                SteamWebApiInterface.Method.GET_RECENTLY_PLAYED_GAMES,
                API_VERSION_1,
                request,
                PlayerRecentlyPlayedGamesResponse.class);
        Assertions.assertNotNull(response);
    }

    private PlayerRecentlyPlayedGamesRequest preparePlayerGetRecentlyPlayedGamesRequest() {
        return PlayerRecentlyPlayedGamesRequest.builder()
                .key(KEY)
                .steamId(STEAM_ID)
                .count(COUNT)
                .build();
    }

    private PlayerRecentlyPlayedGamesResponse preparePlayerGetRecentlyPlayedGamesResponse() {
        PlayerRecentlyPlayedGamesResponse response = new PlayerRecentlyPlayedGamesResponse();
        response.setResponse(prepareGetRecentlyPlayedGamesResponse());
        return response;
    }

    private PlayerRecentlyPlayedGamesResponse.GetRecentlyPlayedGamesResponse prepareGetRecentlyPlayedGamesResponse() {
        PlayerRecentlyPlayedGamesResponse.GetRecentlyPlayedGamesResponse response = new PlayerRecentlyPlayedGamesResponse.GetRecentlyPlayedGamesResponse();
        response.setTotalCount(COUNT);
        response.setGameList(List.of(prepareGameDetails()));
        return response;
    }

    private PlayerRecentlyPlayedGamesResponse.GetRecentlyPlayedGamesResponse.GameDetails prepareGameDetails() {
        PlayerRecentlyPlayedGamesResponse.GetRecentlyPlayedGamesResponse.GameDetails gameDetails = new PlayerRecentlyPlayedGamesResponse.GetRecentlyPlayedGamesResponse.GameDetails();
        gameDetails.setAppId(GAME_DETAIL_APP_ID);
        gameDetails.setName(GAME_DETAIL_NAME);
        gameDetails.setPlaytime2WeeksMinutes(GAME_DETAIL_PLAYTIME_2_WEEKS);
        gameDetails.setPlaytimeForeverMinutes(GAME_DETAIL_PLAYTIME_FOREVER);
        gameDetails.setImgIconUrl(GAME_DETAIL_IMG_ICON_URL);
        gameDetails.setPlaytimeWindowsForeverMinutes(GAME_DETAIL_PLAYTIME_WINDOWS_FOREVER);
        gameDetails.setPlaytimeMacForeverMinutes(GAME_DETAIL_PLAYTIME_MAC_FOREVER);
        gameDetails.setPlaytimeLinuxForeverMinutes(GAME_DETAIL_LINUX_FOREVER);
        return gameDetails;
    }

}
