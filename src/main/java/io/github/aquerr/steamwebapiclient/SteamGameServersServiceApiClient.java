package io.github.aquerr.steamwebapiclient;

import io.github.aquerr.steamwebapiclient.exception.ClientException;
import io.github.aquerr.steamwebapiclient.request.AccountListRequest;
import io.github.aquerr.steamwebapiclient.request.CreateAccountRequest;
import io.github.aquerr.steamwebapiclient.request.DeleteAccountRequest;
import io.github.aquerr.steamwebapiclient.request.ResetLoginTokenRequest;
import io.github.aquerr.steamwebapiclient.request.SetMemoRequest;
import io.github.aquerr.steamwebapiclient.response.AccountListResponse;
import io.github.aquerr.steamwebapiclient.response.CreateAccountResponse;
import io.github.aquerr.steamwebapiclient.response.DeleteAccountResponse;
import io.github.aquerr.steamwebapiclient.response.ResetLoginTokenResponse;
import io.github.aquerr.steamwebapiclient.response.SetMemoResponse;
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
     *
     * @throws ClientException if:
     * - request could not be sent due to an error
     * - response has not been received
     * - response could not be parsed
     * - any other error occurs
     */
    public AccountListResponse getAccountList(AccountListRequest request) throws ClientException {
        return this.steamHttpClient.get(
                SteamWebApiInterfaceMethod.I_GAME_SERVERS_SERVICE_GET_ACCOUNT_LIST,
                API_VERSION_1,
                request,
                AccountListResponse.class
        );
    }

    /**
     * Creates GSLT (Game Server Login Token) @see <a href="https://steamcommunity.com/dev/managegameservers">Game Server Login Tokens</a>
     *
     * @throws ClientException if:
     * - request could not be sent due to an error
     * - response has not been received
     * - response could not be parsed
     * - any other error occurs
     */
    public CreateAccountResponse createAccount(CreateAccountRequest request) throws ClientException {
        return this.steamHttpClient.post(
                SteamWebApiInterfaceMethod.I_GAME_SERVERS_SERVICE_CREATE_ACCOUNT,
                API_VERSION_1,
                request,
                CreateAccountResponse.class
        );
    }

    /**
     * Deletes GSLT (Game Server Login Token) @see <a href="https://steamcommunity.com/dev/managegameservers">Game Server Login Tokens</a>
     *
     * @return empty response if succeeded.
     *
     * @throws ClientException if:
     * - request could not be sent due to an error
     * - response has not been received
     * - response could not be parsed
     * - any other error occurs
     */
    public DeleteAccountResponse deleteAccount(DeleteAccountRequest request) throws ClientException {
        return this.steamHttpClient.post(
                SteamWebApiInterfaceMethod.I_GAME_SERVERS_SERVICE_DELETE_ACCOUNT,
                API_VERSION_1,
                request,
                DeleteAccountResponse.class
        );
    }

    /**
     * Updates the memo/description of GSLT (Game Server Login Token) @see <a href="https://steamcommunity.com/dev/managegameservers">Game Server Login Tokens</a>
     *
     * Memo does not change the GSLT in any way. It serves only as a note and reminder of what the GSLT is used for.
     *
     * @return empty response if succeeded.
     *
     * @throws ClientException if:
     * - request could not be sent due to an error
     * - response has not been received
     * - response could not be parsed
     * - any other error occurs
     */
    public SetMemoResponse setMemo(SetMemoRequest request) throws ClientException {
        return this.steamHttpClient.post(
                SteamWebApiInterfaceMethod.I_GAME_SERVERS_SERVICE_SET_MEMO,
                API_VERSION_1,
                request,
                SetMemoResponse.class
        );
    }

    /**
     * Resets GSLT (Game Server Login Token) @see <a href="https://steamcommunity.com/dev/managegameservers">Game Server Login Tokens</a>
     *
     * @return the response containing newly generated token for given account steam id.
     *
     * @throws ClientException if:
     * - request could not be sent due to an error
     * - response has not been received
     * - response could not be parsed
     * - any other error occurs
     */
    public ResetLoginTokenResponse resetLoginToken(ResetLoginTokenRequest request) throws ClientException {
        return this.steamHttpClient.post(
                SteamWebApiInterfaceMethod.I_GAME_SERVERS_SERVICE_RESET_LOGIN_TOKEN,
                API_VERSION_1,
                request,
                ResetLoginTokenResponse.class
        );
    }
}
