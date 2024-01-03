package io.github.aquerr.steamwebapiclient;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.github.tomakehurst.wiremock.matching.UrlPathPattern;
import io.github.aquerr.steamwebapiclient.exception.ClientException;
import io.github.aquerr.steamwebapiclient.request.TradeHistoryRequest;
import io.github.aquerr.steamwebapiclient.request.TradeOfferRequest;
import io.github.aquerr.steamwebapiclient.request.TradeOffersRequest;
import io.github.aquerr.steamwebapiclient.request.TradeOffersSummaryRequest;
import io.github.aquerr.steamwebapiclient.response.TradeHistoryResponse;
import io.github.aquerr.steamwebapiclient.response.TradeOfferResponse;
import io.github.aquerr.steamwebapiclient.response.TradeOffersResponse;
import io.github.aquerr.steamwebapiclient.response.TradeOffersSummaryResponse;
import io.github.aquerr.steamwebapiclient.response.shared.TradeOffer;
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
    void getTradeHistoryShouldReturnTradeHistory() throws ClientException {
        // given
        stubFor(get(new UrlPathPattern(equalTo("/IEconService/GetTradeHistory/v1"), false))
                .willReturn(okJson(TestResourceUtils.loadMockFileContent("mock-files/get_trade_history_response.json"))));

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

    @Test
    void getTradeOfferShouldReturnTradeOffer() {
        // given
        stubFor(get(new UrlPathPattern(equalTo("/IEconService/GetTradeOffer/v1"), false))
                .willReturn(okJson(TestResourceUtils.loadMockFileContent("mock-files/get_trade_offer_response.json"))));

        // when
        TradeOfferResponse response = client.getTradeOffer(TradeOfferRequest.builder()
                .tradeOfferId(6656314810L)
                .language("english")
                .build());

        // then
        assertThat(response.getResponse()).satisfies(resp -> {
            TradeOffer tradeOffer = response.getResponse().getOffer();
            assertThat(tradeOffer).isNotNull();
            assertThat(tradeOffer.getId()).isEqualTo("6656314810");
            assertThat(tradeOffer.getOtherUserAccountId()).isEqualTo(1049118211);
            assertThat(tradeOffer.getMessage()).isEmpty();
            assertThat(tradeOffer.getExpirationTimeSeconds()).isEqualTo(1705429814);
            assertThat(tradeOffer.getState()).isEqualTo(TradeOffer.State.ACTIVE);

            assertThat(tradeOffer.getGivenItems()).hasSize(1);
            assertThat(tradeOffer.getGivenItems().get(0)).satisfies(item -> {
                assertThat(item.getAppId()).isEqualTo(730);
                assertThat(item.getContextId()).isEqualTo("2");
                assertThat(item.getAssetId()).isEqualTo("35001471190");
                assertThat(item.getClassId()).isEqualTo("3220810370");
                assertThat(item.getInstanceId()).isEqualTo("188530130");
                assertThat(item.getAmount()).isEqualTo("1");
                assertThat(item.isMissing()).isEqualTo(false);
                assertThat(item.getEstUSD()).isEqualTo("25");
            });

            assertThat(tradeOffer.getReceivedItems()).hasSize(1);
            assertThat(tradeOffer.getReceivedItems().get(0)).satisfies(item -> {
                assertThat(item.getAppId()).isEqualTo(730);
                assertThat(item.getContextId()).isEqualTo("2");
                assertThat(item.getAssetId()).isEqualTo("35001471193");
                assertThat(item.getClassId()).isEqualTo("3220810373");
                assertThat(item.getInstanceId()).isEqualTo("188530133");
                assertThat(item.getAmount()).isEqualTo("1");
                assertThat(item.isMissing()).isEqualTo(false);
                assertThat(item.getEstUSD()).isEqualTo("25");
            });

            assertThat(tradeOffer.isOurOffer()).isEqualTo(true);
            assertThat(tradeOffer.getCreationTimeSeconds()).isEqualTo(1704220214);
            assertThat(tradeOffer.getUpdateTimeSeconds()).isEqualTo(1704220226);
            assertThat(tradeOffer.isFromRealTimeTrade()).isEqualTo(false);
            assertThat(tradeOffer.getEscrowEndDateSeconds()).isEqualTo(0);
            assertThat(tradeOffer.getConfirmationMethod()).isEqualTo(TradeOffer.ConfirmationMethod.MOBILE_APP);
            assertThat(tradeOffer.getEResult()).isEqualTo(1);
        });
    }

    @Test
    void getTradeOffersShouldReturnTradeOffers() {
        // given
        stubFor(get(new UrlPathPattern(equalTo("/IEconService/GetTradeOffers/v1"), false))
                .willReturn(okJson(TestResourceUtils.loadMockFileContent("mock-files/get_trade_offers_response.json"))));

        // when
        TradeOffersResponse response = client.getTradeOffers(TradeOffersRequest.builder()
                .getReceivedOffers(true)
                .getSentOffers(true)
                .getDescriptions(true)
                .language("english")
                .build());

        // then
        assertThat(response.getResponse()).satisfies(resp -> {
            TradeOffer receivedOffer = response.getResponse().getReceivedOffers().get(0);
            assertThat(receivedOffer.getId()).isEqualTo("6656314810");
            assertThat(receivedOffer.getOtherUserAccountId()).isEqualTo(1049118211);
            assertThat(receivedOffer.getMessage()).isEmpty();
            assertThat(receivedOffer.getExpirationTimeSeconds()).isEqualTo(1705429814);
            assertThat(receivedOffer.getState()).isEqualTo(TradeOffer.State.ACTIVE);

            assertThat(receivedOffer.getGivenItems()).hasSize(1);
            assertThat(receivedOffer.getGivenItems().get(0)).satisfies(item -> {
                assertThat(item.getAppId()).isEqualTo(730);
                assertThat(item.getContextId()).isEqualTo("2");
                assertThat(item.getAssetId()).isEqualTo("35001471190");
                assertThat(item.getClassId()).isEqualTo("3220810370");
                assertThat(item.getInstanceId()).isEqualTo("188530130");
                assertThat(item.getAmount()).isEqualTo("1");
                assertThat(item.isMissing()).isEqualTo(false);
                assertThat(item.getEstUSD()).isEqualTo("25");
            });

            assertThat(receivedOffer.getReceivedItems()).hasSize(1);
            assertThat(receivedOffer.getReceivedItems().get(0)).satisfies(item -> {
                assertThat(item.getAppId()).isEqualTo(730);
                assertThat(item.getContextId()).isEqualTo("2");
                assertThat(item.getAssetId()).isEqualTo("35001471193");
                assertThat(item.getClassId()).isEqualTo("3220810373");
                assertThat(item.getInstanceId()).isEqualTo("188530133");
                assertThat(item.getAmount()).isEqualTo("1");
                assertThat(item.isMissing()).isEqualTo(false);
                assertThat(item.getEstUSD()).isEqualTo("25");
            });

            assertThat(receivedOffer.isOurOffer()).isEqualTo(true);
            assertThat(receivedOffer.getCreationTimeSeconds()).isEqualTo(1704220214);
            assertThat(receivedOffer.getUpdateTimeSeconds()).isEqualTo(1704220226);
            assertThat(receivedOffer.isFromRealTimeTrade()).isEqualTo(false);
            assertThat(receivedOffer.getEscrowEndDateSeconds()).isEqualTo(0);
            assertThat(receivedOffer.getConfirmationMethod()).isEqualTo(TradeOffer.ConfirmationMethod.MOBILE_APP);
            assertThat(receivedOffer.getEResult()).isEqualTo(1);


            TradeOffer sentOffer = response.getResponse().getSentOffers().get(0);
            assertThat(sentOffer.getId()).isEqualTo("6656314811");
            assertThat(sentOffer.getOtherUserAccountId()).isEqualTo(1049118211);
            assertThat(sentOffer.getMessage()).isEmpty();
            assertThat(sentOffer.getExpirationTimeSeconds()).isEqualTo(1705429814);
            assertThat(sentOffer.getState()).isEqualTo(TradeOffer.State.ACTIVE);

            assertThat(sentOffer.getGivenItems()).hasSize(1);
            assertThat(sentOffer.getGivenItems().get(0)).satisfies(item -> {
                assertThat(item.getAppId()).isEqualTo(730);
                assertThat(item.getContextId()).isEqualTo("2");
                assertThat(item.getAssetId()).isEqualTo("35001471191");
                assertThat(item.getClassId()).isEqualTo("3220810371");
                assertThat(item.getInstanceId()).isEqualTo("188530131");
                assertThat(item.getAmount()).isEqualTo("1");
                assertThat(item.isMissing()).isEqualTo(false);
                assertThat(item.getEstUSD()).isEqualTo("25");
            });

            assertThat(sentOffer.getReceivedItems()).hasSize(1);
            assertThat(sentOffer.getReceivedItems().get(0)).satisfies(item -> {
                assertThat(item.getAppId()).isEqualTo(730);
                assertThat(item.getContextId()).isEqualTo("2");
                assertThat(item.getAssetId()).isEqualTo("35001471193");
                assertThat(item.getClassId()).isEqualTo("3220810373");
                assertThat(item.getInstanceId()).isEqualTo("188530133");
                assertThat(item.getAmount()).isEqualTo("1");
                assertThat(item.isMissing()).isEqualTo(false);
                assertThat(item.getEstUSD()).isEqualTo("25");
            });

            assertThat(sentOffer.isOurOffer()).isEqualTo(true);
            assertThat(sentOffer.getCreationTimeSeconds()).isEqualTo(1704220214);
            assertThat(sentOffer.getUpdateTimeSeconds()).isEqualTo(1704220226);
            assertThat(sentOffer.isFromRealTimeTrade()).isEqualTo(false);
            assertThat(sentOffer.getEscrowEndDateSeconds()).isEqualTo(0);
            assertThat(sentOffer.getConfirmationMethod()).isEqualTo(TradeOffer.ConfirmationMethod.MOBILE_APP);
            assertThat(sentOffer.getEResult()).isEqualTo(1);

            assertThat(resp.getAssetInformation()).hasSize(2);
        });
    }

    @Test
    void getTradeOffersSummaryShouldReturnTradeOffersSummary() {
        // given
        stubFor(get(new UrlPathPattern(equalTo("/IEconService/GetTradeOffersSummary/v1"), false))
                .willReturn(okJson(TestResourceUtils.loadMockFileContent("mock-files/get_trade_offers_summary_response.json"))));

        // when
        TradeOffersSummaryResponse response = client.getTradeOffersSummary(TradeOffersSummaryRequest.builder()
                .timeLastVisitSeconds(100000)
                .build());

        // then
        assertThat(response.getResponse()).satisfies(resp -> {
            assertThat(resp.getPendingReceivedCount()).isEqualTo(0);
            assertThat(resp.getNewReceivedCount()).isEqualTo(0);
            assertThat(resp.getUpdatedReceivedCount()).isEqualTo(0);
            assertThat(resp.getHistoricalReceivedCount()).isEqualTo(2);
            assertThat(resp.getPendingSentCount()).isEqualTo(1);
            assertThat(resp.getNewlyAcceptedSentCount()).isEqualTo(0);
            assertThat(resp.getUpdatedSentCount()).isEqualTo(0);
            assertThat(resp.getHistoricalSentCount()).isEqualTo(2);
            assertThat(resp.getEscrowReceivedCount()).isEqualTo(0);
            assertThat(resp.getEscrowSentCount()).isEqualTo(0);
        });
    }
}