package io.github.aquerr.steamwebapiclient;

import io.github.aquerr.steamwebapiclient.request.AccountListRequest;
import io.github.aquerr.steamwebapiclient.response.AccountListResponse;
import io.github.aquerr.steamwebapiclient.util.SteamHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static io.github.aquerr.steamwebapiclient.SteamWebApiClient.API_VERSION_1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

class SteamGameServersServiceApiClientTest {

    private static final String KEY = "KEY";

    @Mock
    private SteamHttpClient steamHttpClient;

    @InjectMocks
    private SteamGameServersServiceApiClient client;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void shouldGetAccountList() {
        //given
        AccountListRequest request = prepareAccountListRequest();
        given(client.getAccountList(request)).willReturn(new AccountListResponse());

        //when
        AccountListResponse response = client.getAccountList(request);

        //then
        verify(steamHttpClient).get(
                SteamWebApiInterfaceMethod.I_GAME_SERVERS_SERVICE_GET_ACCOUNT_LIST,
                API_VERSION_1,
                request,
                AccountListResponse.class
        );
        assertThat(response).isNotNull();
    }

    private AccountListRequest prepareAccountListRequest() {
        return AccountListRequest.builder()
                .key(KEY)
                .build();
    }

}
