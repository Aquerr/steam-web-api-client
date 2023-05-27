package io.github.aquerr.steamwebapiclient;

import io.github.aquerr.steamwebapiclient.request.WorkShopQueryFilesRequest;
import io.github.aquerr.steamwebapiclient.response.WorkShopQueryResponse;
import io.github.aquerr.steamwebapiclient.util.SteamHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.openMocks;

class SteamPublishedFileWebApiClientTest
{
    private static final int APP_ID = 123456;
    private static final long PUBLISHED_FILE_ID = 123456789L;
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