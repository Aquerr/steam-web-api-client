package io.github.aquerr.steamwebapiclient;

import io.github.aquerr.steamwebapiclient.request.AccountListRequest;
import io.github.aquerr.steamwebapiclient.response.AccountListResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import static io.github.aquerr.steamwebapiclient.SteamWebApiClient.API_VERSION_1;

/**
 *  Methods to improve the administration of Steam Game Servers.
 */
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class SteamGameServersServiceApiClient {

    private final SteamHttpClient steamHttpClient;

    /**
     * 	Gets a list of game server accounts with their logon tokens.
     */
    public AccountListResponse getAccountList(AccountListRequest request) {
        return this.steamHttpClient.get(
                SteamWebApiInterfaceMethod.I_GAME_SERVERS_SERVICE_GET_ACCOUNT_LIST,
                API_VERSION_1,
                request,
                AccountListResponse.class
        );
    }

}
