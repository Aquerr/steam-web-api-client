package io.github.aquerr.steamwebapiclient;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.github.tomakehurst.wiremock.matching.UrlPathPattern;
import io.github.aquerr.steamwebapiclient.exception.ClientException;
import io.github.aquerr.steamwebapiclient.request.AccountListRequest;
import io.github.aquerr.steamwebapiclient.request.CreateAccountRequest;
import io.github.aquerr.steamwebapiclient.response.AccountListResponse;
import io.github.aquerr.steamwebapiclient.response.CreateAccountResponse;
import io.github.aquerr.steamwebapiclient.util.TestResourceUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.assertj.core.api.Assertions.assertThat;

@WireMockTest(httpPort = 4444)
@ExtendWith(MockitoExtension.class)
class SteamGameServersServiceApiClientTest {

    private static final String KEY = "KEY";

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
                        "input_json", equalTo("{\"appid\":744,\"memo\":\"Test GSLT\"}")
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

    private AccountListRequest prepareAccountListRequest() {
        return AccountListRequest.builder()
                .key(KEY)
                .build();
    }
}
