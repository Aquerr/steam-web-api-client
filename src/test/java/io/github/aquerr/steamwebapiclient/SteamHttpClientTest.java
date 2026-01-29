package io.github.aquerr.steamwebapiclient;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.github.tomakehurst.wiremock.matching.EqualToPattern;
import com.github.tomakehurst.wiremock.matching.UrlPathPattern;
import io.github.aquerr.steamwebapiclient.exception.ClientException;
import io.github.aquerr.steamwebapiclient.request.SupportedApiListRequest;
import io.github.aquerr.steamwebapiclient.response.ServerInfoResponse;
import io.github.aquerr.steamwebapiclient.response.SteamWebApiResponse;
import io.github.aquerr.steamwebapiclient.response.SupportedApiListResponse;
import io.github.aquerr.steamwebapiclient.util.TestResourceUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@WireMockTest(httpPort = 4444)
@ExtendWith(MockitoExtension.class)
class SteamHttpClientTest
{
    private static final String API_KEY = "ApiKey";

    @Spy
    private ObjectMapper objectMapper = JsonMapper.builder()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .findAndAddModules()
            .build();

    private final SteamHttpClient steamHttpClient = new SteamHttpClient("http://localhost:4444", API_KEY, SteamWebApiClient.defaultHttpClient(), objectMapper);

    @Test
    void getShouldThrowClientExceptionWhenApiInterfaceMethodIsNull() {
        assertThrows(ClientException.class, () -> this.steamHttpClient.get(null, "version", null, SteamWebApiResponse.class));
    }

    @Test
    void getShouldThrowClientExceptionWhenVersionIsNull() {
        assertThrows(ClientException.class, () -> this.steamHttpClient.get(SteamWebApiInterfaceMethod.I_STEAM_WEB_API_UTIL_GET_SUPPORTED_API_LIST, null, new SupportedApiListRequest(), SteamWebApiResponse.class));
    }

    @Test
    void getShouldNotConvertPassedRequestToQueryParamsWhenRequestIsNull() throws ClientException {
        // given
        stubFor(get(new UrlPathPattern(equalTo("/ISteamWebAPIUtil/GetServerInfo/v1"), false))
                .willReturn(okJson(TestResourceUtils.loadMockFileContent("mock-files/get_server_info.json"))));

        // when
        ServerInfoResponse response = this.steamHttpClient.get(SteamWebApiInterfaceMethod.I_STEAM_WEB_API_UTIL_GET_SERVER_INFO, SteamWebApiClient.API_VERSION_1, null, ServerInfoResponse.class);

        // then
        assertThat(response).isNotNull();
        assertThat(response.getServerTime()).isEqualTo(123456789L);
        assertThat(response.getServerTimeString()).isEqualTo("Sat May 13 09:19:49 2023");
    }

    @Test
    void getShouldConvertPassedRequestToQueryParams() throws ClientException {
        // given
        stubFor(get(new UrlPathPattern(equalTo("/ISteamWebAPIUtil/GetSupportedAPIList/v1"), false))
                .withQueryParam("key", new EqualToPattern(API_KEY))
                .willReturn(okJson(TestResourceUtils.loadMockFileContent("mock-files/get_supported_api_list.json"))));

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
    void getShouldPerformRequestAndReturnMappedResponse() throws ClientException {
        // given
        stubFor(get(new UrlPathPattern(equalTo("/ISteamWebAPIUtil/GetServerInfo/v1"), false))
                .willReturn(okJson(TestResourceUtils.loadMockFileContent("mock-files/get_server_info.json"))));

        // when
        ServerInfoResponse response = this.steamHttpClient.get(SteamWebApiInterfaceMethod.I_STEAM_WEB_API_UTIL_GET_SERVER_INFO, SteamWebApiClient.API_VERSION_1, null, ServerInfoResponse.class);

        // then
        assertThat(response.getServerTime()).isEqualTo(123456789L);
        assertThat(response.getServerTimeString()).isEqualTo("Sat May 13 09:19:49 2023");
    }
}
