package io.github.aquerr.steamwebapiclient.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.aquerr.steamwebapiclient.SteamWebApiClient;
import io.github.aquerr.steamwebapiclient.SteamWebApiInterfaceMethod;
import io.github.aquerr.steamwebapiclient.request.SupportedApiListRequest;
import io.github.aquerr.steamwebapiclient.response.ServerInfoResponse;
import io.github.aquerr.steamwebapiclient.response.SteamWebApiResponse;
import io.github.aquerr.steamwebapiclient.response.SupportedApiListResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Spy;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

class SteamHttpClientTest
{
    private static final String BASE_URL = "https://api.steampowered.com";

    private static final String API_KEY = "ApiKey";

    @Spy
    private ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

    @Mock
    private HttpClient httpClient;

    private SteamHttpClient steamHttpClient;

    @Captor
    private ArgumentCaptor<HttpRequest> httpRequestArgumentCaptor;

    @BeforeEach
    void setUp() {
        openMocks(this);
        this.steamHttpClient = new SteamHttpClient(BASE_URL, API_KEY, httpClient, objectMapper);
    }

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
    void getShouldNotConvertPassedRequestToQueryParamsWhenRequestIsNull() throws IOException, InterruptedException
    {
        HttpResponse httpResponse = mock(HttpResponse.class);

        given(httpClient.send(any(), any())).willReturn(httpResponse);
        given(httpResponse.statusCode()).willReturn(200);
        given(httpResponse.body()).willReturn(TestResourceUtils.loadMockJson("mock-json/get_server_info.json"));

        this.steamHttpClient.get(SteamWebApiInterfaceMethod.I_STEAM_WEB_API_UTIL_GET_SERVER_INFO, SteamWebApiClient.API_VERSION_1, null, ServerInfoResponse.class);

        verify(httpClient).send(httpRequestArgumentCaptor.capture(), any(HttpResponse.BodyHandler.class));
        assertThat(httpRequestArgumentCaptor.getValue().uri()).isEqualTo(URI.create(BASE_URL
                + "/" + SteamWebApiInterfaceMethod.I_STEAM_WEB_API_UTIL_GET_SERVER_INFO.getInterfaceName()
                + "/" + SteamWebApiInterfaceMethod.I_STEAM_WEB_API_UTIL_GET_SERVER_INFO.getMethodName()
                + "/" + SteamWebApiClient.API_VERSION_1));
    }

    @Test
    void getShouldConvertPassedRequestToQueryParams() throws IOException, InterruptedException
    {
        HttpResponse httpResponse = mock(HttpResponse.class);

        given(httpClient.send(any(), any())).willReturn(httpResponse);
        given(httpResponse.statusCode()).willReturn(200);
        given(httpResponse.body()).willReturn(TestResourceUtils.loadMockJson("mock-json/get_supported_api_list.json"));

        SupportedApiListResponse supportedApiListResponse = this.steamHttpClient.get(SteamWebApiInterfaceMethod.I_STEAM_WEB_API_UTIL_GET_SUPPORTED_API_LIST, SteamWebApiClient.API_VERSION_1, new SupportedApiListRequest(), SupportedApiListResponse.class);

        verify(httpClient).send(httpRequestArgumentCaptor.capture(), any(HttpResponse.BodyHandler.class));
        assertThat(httpRequestArgumentCaptor.getValue().uri()).isEqualTo(URI.create(BASE_URL
                + "/" + SteamWebApiInterfaceMethod.I_STEAM_WEB_API_UTIL_GET_SUPPORTED_API_LIST.getInterfaceName()
                + "/" + SteamWebApiInterfaceMethod.I_STEAM_WEB_API_UTIL_GET_SUPPORTED_API_LIST.getMethodName()
                + "/" + SteamWebApiClient.API_VERSION_1
                + "?key=" + API_KEY));

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
    void getShouldPerformRequestAndReturnMappedResponse() throws IOException, InterruptedException
    {
        long serverTime = 123456789L;
        String serverTimeString = "Sat May 13 09:19:49 2023";
        HttpResponse httpResponse = mock(HttpResponse.class);

        given(httpClient.send(any(), any())).willReturn(httpResponse);
        given(httpResponse.statusCode()).willReturn(200);
        given(httpResponse.body()).willReturn(TestResourceUtils.loadMockJson("mock-json/get_server_info.json"));

        ServerInfoResponse serverInfoResponse = this.steamHttpClient.get(SteamWebApiInterfaceMethod.I_STEAM_WEB_API_UTIL_GET_SERVER_INFO, SteamWebApiClient.API_VERSION_1, null, ServerInfoResponse.class);

        assertThat(serverInfoResponse.getServerTime()).isEqualTo(serverTime);
        assertThat(serverInfoResponse.getServerTimeString()).isEqualTo(serverTimeString);
    }
}
