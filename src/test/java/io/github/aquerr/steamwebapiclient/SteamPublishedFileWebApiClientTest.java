package io.github.aquerr.steamwebapiclient;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.github.tomakehurst.wiremock.matching.UrlPathPattern;
import io.github.aquerr.steamwebapiclient.exception.ClientException;
import io.github.aquerr.steamwebapiclient.request.GetDetailsRequest;
import io.github.aquerr.steamwebapiclient.request.WorkShopQueryFilesRequest;
import io.github.aquerr.steamwebapiclient.response.FileDetailsResponse;
import io.github.aquerr.steamwebapiclient.response.WorkShopQueryResponse;
import io.github.aquerr.steamwebapiclient.response.shared.PublishedFileDetails;
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

        SteamHttpClient steamHttpClient = new SteamHttpClient("http://localhost:8080", apiKey, SteamWebApiClient.defaultHttpClient(), SteamWebApiClient.defaultObjectMapper());
        SteamPublishedFileWebApiClient steamPublishedFileWebApiClient = new SteamPublishedFileWebApiClient(steamHttpClient);

        // when
        WorkShopQueryResponse workShopQueryResponse = steamPublishedFileWebApiClient.queryFiles(request);

        // then
        assertThat(workShopQueryResponse).isNotNull();
        assertThat(workShopQueryResponse.getResponse().getPublishedFileDetails().size()).isOne();
        assertWith(workShopQueryResponse.getResponse().getPublishedFileDetails().get(0), (publishedFileDetails -> {
            assertThat(publishedFileDetails.getPublishedFileId()).isEqualTo(String.valueOf(PUBLISHED_FILE_ID));
            assertThat(publishedFileDetails.getTitle()).isEqualTo("Enhanced Movement");
            assertThat(publishedFileDetails.getChildren()).containsExactly(prepareChildItem("450814997", 1, 0));
            assertThat(publishedFileDetails.getVoteData()).isEqualTo(prepareVoteData(0.822674393653869629, 233, 11));
        }));
    }

    @Test
    void shouldGetPublishedFileDetailsReturnFileDetailsResponse() throws ClientException {
        // given
        String apiKey = "ApiKey";
        GetDetailsRequest request = GetDetailsRequest.builder()
                .apiKey(apiKey)
                .appId(APP_ID)
                .publishedFileIds(List.of(PUBLISHED_FILE_ID, PUBLISHED_FILE_ID_2))
                .includeChildren(true)
                .includeTags(true)
                .includeVotes(true)
                .includeReactions(true)
                .includeAdditionalPreviews(true)
                .includeReactions(true)
                .includeMetadata(true)
                .includeKvTags(true)
                .includePlaytimeStats(true)
                .build();

        stubFor(get(new UrlPathPattern(equalTo("/IPublishedFileService/GetDetails/v1"), false))
                .withQueryParams(TestHttpUtils.toQueryParams(request))
                .willReturn(okJson(TestResourceUtils.loadMockFileContent("mock-files/get_details_response.json"))));

        SteamHttpClient steamHttpClient = new SteamHttpClient("http://localhost:8080", apiKey, SteamWebApiClient.defaultHttpClient(), SteamWebApiClient.defaultObjectMapper());
        SteamPublishedFileWebApiClient steamPublishedFileWebApiClient = new SteamPublishedFileWebApiClient(steamHttpClient);

        // when
        FileDetailsResponse response = steamPublishedFileWebApiClient.getDetails(request);

        // then
        assertThat(response).isNotNull();
        assertThat(response.getResponse().getPublishedFileDetails()).hasSize(2);
        assertWith(response.getResponse().getPublishedFileDetails().get(0), (publishedFileDetails -> {
            assertThat(publishedFileDetails.getPublishedFileId()).isEqualTo(String.valueOf(PUBLISHED_FILE_ID));
            assertThat(publishedFileDetails.getTitle()).isEqualTo("Mount Laguna");
            assertThat(publishedFileDetails.getChildren()).containsExactly(prepareChildItem("3336740643", 1, 0));
            assertThat(publishedFileDetails.getVoteData()).isEqualTo(prepareVoteData(0.732984304428100586, 90, 1));
        }));
        assertWith(response.getResponse().getPublishedFileDetails().get(1), (publishedFileDetails -> {
            assertThat(publishedFileDetails.getPublishedFileId()).isEqualTo(String.valueOf(PUBLISHED_FILE_ID_2));
            assertThat(publishedFileDetails.getTitle()).isEqualTo("CBA_A3");
            assertThat(publishedFileDetails.getKeyValueTags().get(0).getKey()).isEqualTo("bis_shortHash");
            assertThat(publishedFileDetails.getReactions().get(0).getReactionId()).isEqualTo(7);
        }));
    }

    private PublishedFileDetails.VoteData prepareVoteData(double score, int voteUp, int voteDown)
    {
        PublishedFileDetails.VoteData voteData = new PublishedFileDetails.VoteData();
        voteData.setScore(score);
        voteData.setVotesUp(voteUp);
        voteData.setVotesDown(voteDown);
        return voteData;
    }

    private PublishedFileDetails.ChildItem prepareChildItem(String publishedFileId, int sortOrder, int fileType)
    {
        PublishedFileDetails.ChildItem childItem = new PublishedFileDetails.ChildItem();
        childItem.setPublishedFileId(String.valueOf(publishedFileId));
        childItem.setOrder(sortOrder);
        childItem.setFileType(fileType);
        return childItem;
    }

    private WorkShopQueryResponse prepareWorkShopQueryResponse(List<Long> publishedFileIds) {
        WorkShopQueryResponse workShopQueryResponse = new WorkShopQueryResponse();
        WorkShopQueryResponse.QueryFilesResponse queryFilesResponse = new WorkShopQueryResponse.QueryFilesResponse();
        workShopQueryResponse.setResponse(queryFilesResponse);
        List<PublishedFileDetails> publishedFileDetailsList = new ArrayList<>();
        queryFilesResponse.setPublishedFileDetails(publishedFileDetailsList);

        publishedFileIds.forEach(id -> {
            PublishedFileDetails publishedFileDetails = new PublishedFileDetails();
            publishedFileDetails.setPublishedFileId(String.valueOf(id));
            publishedFileDetailsList.add(publishedFileDetails);
        });
        return workShopQueryResponse;
    }
}