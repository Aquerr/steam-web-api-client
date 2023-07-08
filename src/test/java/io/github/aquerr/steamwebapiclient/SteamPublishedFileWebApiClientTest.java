package io.github.aquerr.steamwebapiclient;

import io.github.aquerr.steamwebapiclient.request.WorkShopQueryFilesRequest;
import io.github.aquerr.steamwebapiclient.response.WorkShopQueryResponse;
import io.github.aquerr.steamwebapiclient.util.SteamHttpClient;
import io.github.aquerr.steamwebapiclient.util.TestResourceUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.MockitoAnnotations.openMocks;

class SteamPublishedFileWebApiClientTest
{
    private static final int APP_ID = 123456;
    private static final long PUBLISHED_FILE_ID = 333310405L;
    private static final long PUBLISHED_FILE_ID_2 = 9532492348L;

    @Mock
    private SteamHttpClient steamHttpClient;

    @InjectMocks
    private SteamPublishedFileWebApiClient steamPublishedFileWebApiClient;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void getPublishedFileDetailsReturnPublishedFileDetailsResponse() {

        // given
        WorkShopQueryResponse expectedResponse = prepareWorkShopQueryResponse(List.of(PUBLISHED_FILE_ID, PUBLISHED_FILE_ID_2));
        WorkShopQueryFilesRequest request = WorkShopQueryFilesRequest.builder()
                .appId(APP_ID)
                .build();

        given(steamHttpClient.get(SteamWebApiInterfaceMethod.I_PUBLISHED_FILE_SERVICE_QUERY_FILES,
                SteamWebApiClient.API_VERSION_1,
                request,
                WorkShopQueryResponse.class)).willReturn(expectedResponse);

        // when
        WorkShopQueryResponse response = steamPublishedFileWebApiClient.queryFiles(request);

        // then
        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    void shouldGetPublishedFileDetailsReturnResponseWhenSearchTextContainsWhitespace() throws IOException, InterruptedException
    {
        // given
        WorkShopQueryFilesRequest request = WorkShopQueryFilesRequest.builder()
                .appId(APP_ID)
                .searchText("Enhanced Movement")
                .build();

        String baseUrl = "https://api.steampowered.com";
        String apiKey = "ApiKey";
        HttpClient httpClient = mock(HttpClient.class);
        SteamHttpClient steamHttpClient = new SteamHttpClient(baseUrl, apiKey, httpClient);
        SteamPublishedFileWebApiClient steamPublishedFileWebApiClient = new SteamPublishedFileWebApiClient(steamHttpClient);
        HttpResponse response = mock(HttpResponse.class);
        given(response.statusCode()).willReturn(200);
        given(response.body()).willReturn(TestResourceUtils.loadMockJson("mock-json/get_workshop_query_response.json"));
        given(httpClient.send(any(), any())).willReturn(response);

        // when
        WorkShopQueryResponse workShopQueryResponse = steamPublishedFileWebApiClient.queryFiles(request);

        // then
        assertThat(workShopQueryResponse).isNotNull();
        assertThat(workShopQueryResponse.getResponse().getPublishedFileDetails().size()).isOne();
        assertWith(workShopQueryResponse.getResponse().getPublishedFileDetails().get(0), (publishedFileDetails -> {
            assertThat(publishedFileDetails.getPublishedFileId()).isEqualTo(String.valueOf(PUBLISHED_FILE_ID));
            assertThat(publishedFileDetails.getTitle()).isEqualTo("Enhanced Movement");
        }));
    }

    private WorkShopQueryResponse prepareWorkShopQueryResponse(List<Long> publishedFileIds) {
        WorkShopQueryResponse workShopQueryResponse = new WorkShopQueryResponse();
        WorkShopQueryResponse.QueryFilesResponse queryFilesResponse = new WorkShopQueryResponse.QueryFilesResponse();
        workShopQueryResponse.setResponse(queryFilesResponse);
        List<WorkShopQueryResponse.QueryFilesResponse.PublishedFileDetails> publishedFileDetailsList = new ArrayList<>();
        queryFilesResponse.setPublishedFileDetails(publishedFileDetailsList);

        publishedFileIds.forEach(id -> {
            WorkShopQueryResponse.QueryFilesResponse.PublishedFileDetails publishedFileDetails = new WorkShopQueryResponse.QueryFilesResponse.PublishedFileDetails();
            publishedFileDetails.setPublishedFileId(String.valueOf(id));
            publishedFileDetailsList.add(publishedFileDetails);
        });
        return workShopQueryResponse;
    }
}