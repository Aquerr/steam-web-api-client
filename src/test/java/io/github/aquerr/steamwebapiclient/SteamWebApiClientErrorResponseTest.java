package io.github.aquerr.steamwebapiclient;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.github.tomakehurst.wiremock.matching.UrlPathPattern;
import io.github.aquerr.steamwebapiclient.exception.ClientException;
import io.github.aquerr.steamwebapiclient.exception.HttpClientException;
import io.github.aquerr.steamwebapiclient.request.TradeOffersRequest;
import io.github.aquerr.steamwebapiclient.util.TestResourceUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.catchThrowable;

@WireMockTest(httpPort = 4444)
@ExtendWith(MockitoExtension.class)
class SteamWebApiClientErrorResponseTest
{
    private final SteamHttpClient steamHttpClient = new SteamHttpClient("http://localhost:4444", null, SteamWebApiClient.defaultHttpClient(), SteamWebApiClient.defaultObjectMapper());

    private final SteamEconServiceWebApiClient client = new SteamEconServiceWebApiClient(this.steamHttpClient);

    @Test
    void getTradeOffersShouldThrowClientException() throws ClientException
    {
        // given
        stubFor(get(new UrlPathPattern(equalTo("/IEconService/GetTradeOffers/v1"), false))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.FORBIDDEN.getCode())
                        .withBody(TestResourceUtils.loadMockFileContent("mock-files/get_trade_offers_forbidden_error.txt"))));

        // when
        Throwable throwable = catchThrowable(() -> client.getTradeOffers(TradeOffersRequest.builder()
                .getReceivedOffers(true)
                .getSentOffers(true)
                .getDescriptions(true)
                .language("english")
                .build()));

        assertThat(throwable).isInstanceOf(ClientException.class);
        assertThat(throwable).isInstanceOf(HttpClientException.class);
        assertThat(throwable.getMessage()).isEqualTo("403 FORBIDDEN Response body: <html><head>\t<title>Forbidden</title></head><body>\t<h1>Forbidden</h1>Access is denied. Retrying will not help. Please verify your\t<pre>key=</pre> parameter.</body></html>");
    }
}
