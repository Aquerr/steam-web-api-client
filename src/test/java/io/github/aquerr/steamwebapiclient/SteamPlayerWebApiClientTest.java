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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static io.github.aquerr.steamwebapiclient.SteamWebApiClient.API_VERSION_1;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
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
    private static final int PROFILE_LEVEL = 0;
    private static final long BADGE_ID = 1L;
    private static final int BADGE_LEVEL = 0;
    private static final long BADGE_COMPLETION_TIME = 1L;
    private static final int BADGE_XP = 0;
    private static final int BADGE_SCARCITY = 0;
    private static final long QUEST_ID = 1L;

    @Mock
    private SteamHttpClient steamHttpClient;

    @InjectMocks
    private SteamPlayerWebApiClient client;

    @Test
    void shouldGetRecentlyPlayedGames() throws ClientException {
        //given
        PlayerRecentlyPlayedGamesRequest request = preparePlayerGetRecentlyPlayedGamesRequest();
        given(client.getRecentlyPlayedGames(request)).willReturn(preparePlayerGetRecentlyPlayedGamesResponse());

        //when
        PlayerRecentlyPlayedGamesResponse response = client.getRecentlyPlayedGames(request);

        //then
        verify(steamHttpClient).get(
                SteamWebApiInterfaceMethod.I_PLAYER_SERVICE_GET_RECENTLY_PLAYED_GAMES,
                API_VERSION_1,
                request,
                PlayerRecentlyPlayedGamesResponse.class);
        Assertions.assertNotNull(response);
    }

    @Test
    void shouldGetOwnedGames() throws ClientException {
        //given
        OwnedGamesRequest request = prepareOwnedGamesRequest();
        given(client.getOwnedGames(request)).willReturn(prepareOwnedGamesResponse());

        //when
        OwnedGamesResponse response = client.getOwnedGames(request);

        //then
        verify(steamHttpClient).get(
                SteamWebApiInterfaceMethod.I_PLAYER_SERVICE_GET_OWNED_GAMES,
                API_VERSION_1,
                request,
                OwnedGamesResponse.class);
        Assertions.assertNotNull(response);
    }

    @Test
    void shouldGetSteamLevel() throws ClientException {
        //given
        SteamLevelRequest request = prepareSteamLevelRequest();
        given(client.getSteamLevel(request)).willReturn(prepareSteamLevelResponse());

        //when
        SteamLevelResponse response = client.getSteamLevel(request);

        //then
        verify(steamHttpClient).get(
                SteamWebApiInterfaceMethod.I_PLAYER_SERVICE_GET_STEAM_LEVEL,
                API_VERSION_1,
                request,
                SteamLevelResponse.class);
        Assertions.assertNotNull(response);
    }

    @Test
    void shouldGetBadges() throws ClientException {
        //given
        BadgesRequest request = prepareBadgesRequest();
        given(client.getBadges(request)).willReturn(prepareBadgesResponse());

        //when
        BadgesResponse response = client.getBadges(request);

        //then
        verify(steamHttpClient).get(
                SteamWebApiInterfaceMethod.I_PLAYER_SERVICE_GET_BADGES,
                API_VERSION_1,
                request,
                BadgesResponse.class);
        Assertions.assertNotNull(response);
    }

    @Test
    void shouldGetCommunityBadgeProgress() throws ClientException {
        //given
        CommunityBadgeProgressRequest request = prepareCommunityBadgeProgressRequest();
        given(client.getCommunityBadgeProgress(request)).willReturn(prepareCommunityBadgeProgressResponse());

        //when
        CommunityBadgeProgressResponse response = client.getCommunityBadgeProgress(request);

        //then
        verify(steamHttpClient).get(
                SteamWebApiInterfaceMethod.I_PLAYER_SERVICE_GET_COMMUNITY_BADGE_PROGRESS,
                API_VERSION_1,
                request,
                CommunityBadgeProgressResponse.class);
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
        response.setGameList(List.of(prepareRecentlyPlayedGamesGameDetails()));
        return response;
    }

    private PlayerRecentlyPlayedGamesResponse.GetRecentlyPlayedGamesResponse.GameDetails prepareRecentlyPlayedGamesGameDetails() {
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

    private OwnedGamesRequest prepareOwnedGamesRequest() {
        return OwnedGamesRequest.builder()
                .key(KEY)
                .steamId(STEAM_ID)
                .includeAppInfo(Boolean.TRUE)
                .includePlayedFreeGames(Boolean.TRUE)
                .build();
    }

    private OwnedGamesResponse prepareOwnedGamesResponse() {
        OwnedGamesResponse response = new OwnedGamesResponse();
        response.setResponse(prepareGetOwnedGamesResponse());
        return response;
    }

    private OwnedGamesResponse.GetOwnedGamesResponse prepareGetOwnedGamesResponse() {
        OwnedGamesResponse.GetOwnedGamesResponse response = new OwnedGamesResponse.GetOwnedGamesResponse();
        response.setTotalCount(COUNT);
        response.setGameList(List.of(prepareRecentlyOwnedGamesGameDetails()));
        return response;
    }

    private OwnedGamesResponse.GetOwnedGamesResponse.GameDetails prepareRecentlyOwnedGamesGameDetails() {
        OwnedGamesResponse.GetOwnedGamesResponse.GameDetails gameDetails = new OwnedGamesResponse.GetOwnedGamesResponse.GameDetails();
        gameDetails.setAppId(GAME_DETAIL_APP_ID);
        gameDetails.setName(GAME_DETAIL_NAME);
        gameDetails.setPlaytimeForeverMinutes(GAME_DETAIL_PLAYTIME_FOREVER);
        gameDetails.setImgIconUrl(GAME_DETAIL_IMG_ICON_URL);
        gameDetails.setPlaytimeWindowsForeverMinutes(GAME_DETAIL_PLAYTIME_WINDOWS_FOREVER);
        gameDetails.setPlaytimeMacForeverMinutes(GAME_DETAIL_PLAYTIME_MAC_FOREVER);
        gameDetails.setPlaytimeLinuxForeverMinutes(GAME_DETAIL_LINUX_FOREVER);
        gameDetails.setSinceLastPlayedTimeSeconds(0);
        gameDetails.setContentDescriptorIds(new ArrayList<>());
        return gameDetails;
    }

    private SteamLevelRequest prepareSteamLevelRequest() {
        return SteamLevelRequest.builder()
                .key(KEY)
                .steamId(STEAM_ID)
                .build();
    }

    private SteamLevelResponse prepareSteamLevelResponse() {
        SteamLevelResponse steamLevelResponse = new SteamLevelResponse();
        steamLevelResponse.setResponse(prepareGetSteamLevelResponse());
        return steamLevelResponse;
    }

    private SteamLevelResponse.GetSteamLevelResponse prepareGetSteamLevelResponse() {
        SteamLevelResponse.GetSteamLevelResponse response = new SteamLevelResponse.GetSteamLevelResponse();
        response.setProfileLevel(PROFILE_LEVEL);
        return response;
    }

    private BadgesRequest prepareBadgesRequest() {
        return BadgesRequest.builder()
                .key(KEY)
                .steamId(STEAM_ID)
                .build();
    }

    private BadgesResponse prepareBadgesResponse() {
        BadgesResponse response = new BadgesResponse();
        response.setResponse(prepareBadgesResponseResponse());
        return response;
    }

    private BadgesResponse.Response prepareBadgesResponseResponse() {
        BadgesResponse.Response response = new BadgesResponse.Response();
        response.setBadges(List.of(prepareBadge()));
        return  response;
    }

    private BadgesResponse.Response.Badge prepareBadge() {
        BadgesResponse.Response.Badge badge = new BadgesResponse.Response.Badge();
        badge.setId(BADGE_ID);
        badge.setLevel(BADGE_LEVEL);
        badge.setCompletionTimeSeconds(BADGE_COMPLETION_TIME);
        badge.setXp(BADGE_XP);
        badge.setScarcity(BADGE_SCARCITY);
        return badge;
    }

    private CommunityBadgeProgressRequest prepareCommunityBadgeProgressRequest() {
        return CommunityBadgeProgressRequest.builder()
                .steamId(STEAM_ID)
                .badgeId(BADGE_ID)
                .build();
    }

    private CommunityBadgeProgressResponse prepareCommunityBadgeProgressResponse() {
        CommunityBadgeProgressResponse response = new CommunityBadgeProgressResponse();
        response.setResponse(prepareCommunityBadgeProgressResponseResponse());
        return response;
    }

    private CommunityBadgeProgressResponse.Response prepareCommunityBadgeProgressResponseResponse() {
        CommunityBadgeProgressResponse.Response response = new CommunityBadgeProgressResponse.Response();
        response.setQuests(List.of(prepareQuest()));
        return response;
    }

    private CommunityBadgeProgressResponse.Response.Quest prepareQuest() {
        CommunityBadgeProgressResponse.Response.Quest quest = new CommunityBadgeProgressResponse.Response.Quest();
        quest.setQuestId(QUEST_ID);
        quest.setCompleted(Boolean.TRUE);
        return quest;
    }


}
