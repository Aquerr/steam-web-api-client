package io.github.aquerr.steamwebapiclient;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.github.tomakehurst.wiremock.matching.UrlPathPattern;
import io.github.aquerr.steamwebapiclient.request.TradeHistoryRequest;
import io.github.aquerr.steamwebapiclient.response.TradeHistoryResponse;
import io.github.aquerr.steamwebapiclient.util.TestResourceUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.assertj.core.api.Assertions.assertThat;

@WireMockTest(httpPort = 4444)
@ExtendWith(MockitoExtension.class)
class SteamEconServiceWebApiClientTest {

    private static final String API_KEY = "ApiKey";

    private final SteamHttpClient steamHttpClient = new SteamHttpClient("http://localhost:4444", API_KEY);

    private final SteamEconServiceWebApiClient client = new SteamEconServiceWebApiClient(this.steamHttpClient);

    @Test
    void getTradeHistoryShouldReturnTradeHistory() {
        // given
        stubFor(get(new UrlPathPattern(equalTo("/IEconService/GetTradeHistory/v1"), false))
                .willReturn(okJson(TestResourceUtils.loadMockFileContent("mock-json/get_trade_history_response.json"))));

        // when
        TradeHistoryResponse response = client.getTradeHistory(TradeHistoryRequest.builder()
                .maxTrades(2)
                .includeTotal(true)
                .language("english")
                .includeFailed(true)
                .build());

        // then
        assertThat(response.getResponse()).satisfies(resp -> {
            assertThat(resp.getTotalTrades()).isEqualTo(88);
            assertThat(resp.isMore()).isEqualTo(true);

            assertThat(resp.getTrades()).hasSize(2);
            assertThat(resp.getTrades().get(0)).satisfies(trade -> {
                assertThat(trade.getTradeId()).isEqualTo("1894412447350854522");
                assertThat(trade.getOtherUserSteamId()).isEqualTo("76564198325376876");
                assertThat(trade.getStatus()).isEqualTo(TradeHistoryResponse.TradeHistory.Trade.Status.COMPLETE);
                assertThat(trade.getAssetsReceived()).hasSize(1);
            });
            assertThat(resp.getTrades().get(1)).satisfies(trade -> {
               assertThat(trade.getTradeId()).isEqualTo("1891933900753285999");
               assertThat(trade.getOtherUserSteamId()).isEqualTo("76561198324683384");
               assertThat(trade.getStatus()).isEqualTo(TradeHistoryResponse.TradeHistory.Trade.Status.PRE_COMMITED);
                assertThat(trade.getAssetsReceived()).hasSize(1);
            });

            assertThat(resp.getAssetInformation()).hasSize(2);
        });
    }
}