package io.github.aquerr.steamwebapiclient;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.github.tomakehurst.wiremock.matching.UrlPathPattern;
import io.github.aquerr.steamwebapiclient.exception.ClientException;
import io.github.aquerr.steamwebapiclient.request.WorkShopQueryFilesRequest;
import io.github.aquerr.steamwebapiclient.response.WorkShopQueryResponse;
import io.github.aquerr.steamwebapiclient.util.TestHttpUtils;
import io.github.aquerr.steamwebapiclient.util.TestResourceUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertWith;
import static org.mockito.BDDMockito.given;

@WireMockTest(httpPort = 8080)
@ExtendWith(MockitoExtension.class)
class SteamPublishedFileWebApiClientTest
{
    private static final int APP_ID = 123456;
    private static final long PUBLISHED_FILE_ID = 333310405L;
    private static final long PUBLISHED_FILE_ID_2 = 9532492348L;

    @Mock
    private SteamHttpClient steamHttpClient;

    @InjectMocks
    private SteamPublishedFileWebApiClient steamPublishedFileWebApiClient;

    @Test
    void getPublishedFileDetailsReturnPublishedFileDetailsResponse() throws ClientException {

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
    void shouldGetPublishedFileDetailsReturnResponseWhenSearchTextContainsWhitespace() throws ClientException {
        // given
        String apiKey = "ApiKey";
        WorkShopQueryFilesRequest request = WorkShopQueryFilesRequest.builder()
                .appId(APP_ID)
                .key(apiKey)
                .searchText("Enhanced Movement")
                .build();
        stubFor(get(new UrlPathPattern(equalTo("/IPublishedFileService/QueryFiles/v1"), false))
                .withQueryParams(TestHttpUtils.toQueryParams(request))
                .willReturn(okJson(TestResourceUtils.loadMockFileContent("mock-files/get_workshop_query_response.json"))));

        SteamHttpClient steamHttpClient = new SteamHttpClient("http://localhost:8080", apiKey);
        SteamPublishedFileWebApiClient steamPublishedFileWebApiClient = new SteamPublishedFileWebApiClient(steamHttpClient);

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