package io.github.aquerr.steamwebapiclient;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.github.tomakehurst.wiremock.matching.UrlPathPattern;
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
import io.github.aquerr.steamwebapiclient.util.TestResourceUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.assertj.core.api.Assertions.assertThat;

@WireMockTest(httpPort = 4444)
@ExtendWith(MockitoExtension.class)
class SteamGameServersServiceApiClientTest {

    private static final String API_KEY = "ApiKey";

    private final SteamHttpClient steamHttpClient = new SteamHttpClient("http://localhost:4444", API_KEY);

    private final SteamGameServersServiceApiClient client = new SteamGameServersServiceApiClient(this.steamHttpClient);

    @Test
    void shouldGetAccountList() throws ClientException {
        //given
        stubFor(get(new UrlPathPattern(equalTo("/IGameServersService/GetAccountList/v1"), false))
                .withQueryParams(Map.of(
                        "key", equalTo(API_KEY)))
                .willReturn(okJson(TestResourceUtils.loadMockFileContent("mock-files/get_account_list_response.json"))));

        //when
        AccountListResponse response = client.getAccountList(AccountListRequest.builder().build());

        //then
        assertThat(response.getResponse()).satisfies(resp -> {
           assertThat(resp.getLoginTokens()).hasSize(1);
           assertThat(resp.getLoginTokens().get(0).getSteamId()).isEqualTo("25564392950647528");
           assertThat(resp.getLoginTokens().get(0).getAppId()).isEqualTo(730);
           assertThat(resp.getLoginTokens().get(0).getValue()).isEqualTo("2464D219E321A5C291FCE5CCE9D27A38");
           assertThat(resp.getLoginTokens().get(0).getMemo()).isEqualTo("Test GSLT");
           assertThat(resp.getLoginTokens().get(0).isDeleted()).isFalse();
           assertThat(resp.getLoginTokens().get(0).isExpired()).isTrue();
           assertThat(resp.getLoginTokens().get(0).getLastLogon()).isEqualTo(0);

           assertThat(resp.isBanned()).isFalse();
           assertThat(resp.getExpires()).isEqualTo(0);
           assertThat(resp.getActor()).isEqualTo("26561697917265748");
           assertThat(resp.getLastActionTime()).isEqualTo(0);
        });
    }

    @Test
    void shouldCreateAccount() throws ClientException {
        // given
        stubFor(post(new UrlPathPattern(equalTo("/IGameServersService/CreateAccount/v1"), false))
                .withQueryParams(Map.of(
                        "key", equalTo(API_KEY),
                        "input_json", equalToJson("{\"appid\":744,\"memo\":\"Test GSLT\"}")
                ))
                .willReturn(okJson(TestResourceUtils.loadMockFileContent("mock-files/post_create_account_response.json"))));

        // when
        CreateAccountResponse response = client.createAccount(CreateAccountRequest.builder()
                .key(API_KEY)
                .appId(744)
                .memo("Test GSLT")
            .build());

        // then
        assertThat(response.getResponse()).satisfies(resp -> {
           assertThat(resp.getSteamId()).isEqualTo("83568352931645129");
           assertThat(resp.getLoginToken()).isEqualTo("EFE925B6BB087X2681DC75ACE34CCG1L");
        });
    }

    @Test
    void shouldDeleteAccount() throws ClientException {
        // given
        stubFor(post(new UrlPathPattern(equalTo("/IGameServersService/DeleteAccount/v1"), false))
                .withQueryParams(Map.of(
                        "key", equalTo(API_KEY),
                        "input_json", equalToJson("{\"steamid\":\"35562374938658193\"}")
                ))
                .willReturn(okJson(TestResourceUtils.loadMockFileContent("mock-files/post_delete_account_response.json"))));

        // when
        DeleteAccountResponse response = client.deleteAccount(DeleteAccountRequest.builder()
                .steamId("35562374938658193")
                .build());

        // then
        assertThat(response.getResponse()).isNotNull();
    }

    @Test
    void shouldSetMemo() throws ClientException {
        // given
        stubFor(post(new UrlPathPattern(equalTo("/IGameServersService/SetMemo/v1"), false))
                .withQueryParams(Map.of(
                        "key", equalTo(API_KEY),
                        "input_json", equalToJson("{\"steamid\":\"35562374938658193\", \"memo\": \"New Memo\"}")
                ))
                .willReturn(okJson(TestResourceUtils.loadMockFileContent("mock-files/post_set_memo_response.json"))));

        // when
        SetMemoResponse response = client.setMemo(SetMemoRequest.builder()
                .steamId("35562374938658193")
                .memo("New Memo")
                .build());

        // then
        assertThat(response.getResponse()).isNotNull();
    }

    @Test
    void shouldResetLoginToken() throws ClientException {
        // given
        stubFor(post(new UrlPathPattern(equalTo("/IGameServersService/ResetLoginToken/v1"), false))
                .withQueryParams(Map.of(
                        "key", equalTo(API_KEY),
                        "input_json", equalToJson("{\"steamid\":\"35562374938658193\"}")
                ))
                .willReturn(okJson(TestResourceUtils.loadMockFileContent("mock-files/post_reset_login_token_response.json"))));

        // when
        ResetLoginTokenResponse response = client.resetLoginToken(ResetLoginTokenRequest.builder()
                .steamId("35562374938658193")
                .build());

        // then
        assertThat(response.getResponse()).isNotNull();
        assertThat(response.getResponse().getLoginToken()).isEqualTo("DXC2CE2WA8CB0BA5F3B29A5F1E622D21");
    }
}
