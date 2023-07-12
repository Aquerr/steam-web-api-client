package io.github.aquerr.steamwebapiclient.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.github.tomakehurst.wiremock.matching.EqualToPattern;
import com.github.tomakehurst.wiremock.matching.UrlPathPattern;
import io.github.aquerr.steamwebapiclient.SteamWebApiClient;
import io.github.aquerr.steamwebapiclient.SteamWebApiInterfaceMethod;
import io.github.aquerr.steamwebapiclient.request.SupportedApiListRequest;
import io.github.aquerr.steamwebapiclient.response.ServerInfoResponse;
import io.github.aquerr.steamwebapiclient.response.SteamWebApiResponse;
import io.github.aquerr.steamwebapiclient.response.SupportedApiListResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@WireMockTest(httpPort = 8080)
@ExtendWith(MockitoExtension.class)
class SteamHttpClientTest
{
    private static final String API_KEY = "ApiKey";

    @Spy
    private ObjectMapper objectMapper = new ObjectMapper()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .findAndRegisterModules();

    private final SteamHttpClient steamHttpClient = new SteamHttpClient("http://localhost:8080", API_KEY, objectMapper);

    @Test
    void getShouldThrowIllegalArgumentExceptionWhenApiInterfaceMethodIsNull() {
        assertThrows(IllegalArgumentException.class, () -> this.steamHttpClient.get(null, "version", null, SteamWebApiResponse.class));
    }

    @Test
    void getShouldThrowIllegalArgumentExceptionWhenVersionIsNull() {
        assertThrows(IllegalArgumentException.class, () -> this.steamHttpClient.get(SteamWebApiInterfaceMethod.I_STEAM_WEB_API_UTIL_GET_SUPPORTED_API_LIST, null, null, SteamWebApiResponse.class));
    }

    @Test
    void getShouldNotThrowAnyExceptionWhenVersionIsNull() {
        assertThrows(IllegalArgumentException.class, () -> this.steamHttpClient.get(SteamWebApiInterfaceMethod.I_STEAM_WEB_API_UTIL_GET_SUPPORTED_API_LIST, null, null, SteamWebApiResponse.class));
    }

    @Test
    void getShouldNotConvertPassedRequestToQueryParamsWhenRequestIsNull()
    {
        // given
        stubFor(get(new UrlPathPattern(equalTo("/ISteamWebAPIUtil/GetServerInfo/v1"), false))
                .willReturn(okJson(TestResourceUtils.loadMockJson("mock-json/get_server_info.json"))));

        // when
        ServerInfoResponse response = this.steamHttpClient.get(SteamWebApiInterfaceMethod.I_STEAM_WEB_API_UTIL_GET_SERVER_INFO, SteamWebApiClient.API_VERSION_1, null, ServerInfoResponse.class);

        // then
        assertThat(response).isNotNull();
        assertThat(response.getServerTime()).isEqualTo(123456789L);
        assertThat(response.getServerTimeString()).isEqualTo("Sat May 13 09:19:49 2023");
    }

    @Test
    void getShouldConvertPassedRequestToQueryParams()
    {
        // given
        stubFor(get(new UrlPathPattern(equalTo("/ISteamWebAPIUtil/GetSupportedAPIList/v1"), false))
                .withQueryParam("key", new EqualToPattern(API_KEY))
                .willReturn(okJson(TestResourceUtils.loadMockJson("mock-json/get_supported_api_list.json"))));

        // when
        SupportedApiListResponse supportedApiListResponse = this.steamHttpClient.get(SteamWebApiInterfaceMethod.I_STEAM_WEB_API_UTIL_GET_SUPPORTED_API_LIST, SteamWebApiClient.API_VERSION_1, new SupportedApiListRequest(), SupportedApiListResponse.class);

        // then
        SupportedApiListResponse.SupportedApiList.SupportedInterface expectedSupportedInterface = new SupportedApiListResponse.SupportedApiList.SupportedInterface();
        expectedSupportedInterface.setName("my_interface");
        SupportedApiListResponse.SupportedApiList.SupportedInterface.Method expectedSupportedMethod = new SupportedApiListResponse.SupportedApiList.SupportedInterface.Method();
        expectedSupportedMethod.setName("my_method");
        expectedSupportedMethod.setVersion(1);
        expectedSupportedMethod.setHttpMethod("GET");
        expectedSupportedInterface.setMethods(List.of(expectedSupportedMethod));

        assertThat(supportedApiListResponse).isNotNull();
        assertThat(supportedApiListResponse.getSupportedApiList().getInterfaces()).containsExactly(expectedSupportedInterface);
    }

    @Test
    void getShouldPerformRequestAndReturnMappedResponse()
    {
        // given
        stubFor(get(new UrlPathPattern(equalTo("/ISteamWebAPIUtil/GetServerInfo/v1"), false))
                .willReturn(okJson(TestResourceUtils.loadMockJson("mock-json/get_server_info.json"))));

        // when
        ServerInfoResponse response = this.steamHttpClient.get(SteamWebApiInterfaceMethod.I_STEAM_WEB_API_UTIL_GET_SERVER_INFO, SteamWebApiClient.API_VERSION_1, null, ServerInfoResponse.class);

        // then
        assertThat(response.getServerTime()).isEqualTo(123456789L);
        assertThat(response.getServerTimeString()).isEqualTo("Sat May 13 09:19:49 2023");
    }
}
