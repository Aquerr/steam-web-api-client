package io.github.aquerr.steamwebapiclient;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.github.tomakehurst.wiremock.matching.UrlPathPattern;
import io.github.aquerr.steamwebapiclient.exception.ClientException;
import io.github.aquerr.steamwebapiclient.request.CollectionDetailsRequest;
import io.github.aquerr.steamwebapiclient.request.PublishedFileDetailsRequest;
import io.github.aquerr.steamwebapiclient.response.CollectionDetailsResponse;
import io.github.aquerr.steamwebapiclient.response.PublishedFileDetailsResponse;
import io.github.aquerr.steamwebapiclient.util.TestResourceUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.assertj.core.api.Assertions.assertThat;

@WireMockTest(httpPort = 4444)
@ExtendWith(MockitoExtension.class)
class SteamRemoteStorageClientTest {

    private static final long PUBLISHED_FILE_ID = 123456789L;
    private static final long PUBLISHED_FILE_ID_2 = 9532492348L;
    private static final long PUBLISHED_FILE_ID_3 = 181627512L;

    private static final String API_KEY = "ApiKey";

    private final SteamHttpClient steamHttpClient = new SteamHttpClient("http://localhost:4444", API_KEY);

    private SteamRemoteStorageClient steamRemoteStorageClient;

    @BeforeEach
    void setUp() {
        this.steamRemoteStorageClient = new SteamRemoteStorageClient(this.steamHttpClient);
    }

    @Test
    void getPublishedFileDetailsReturnPublishedFileDetailsResponse() throws ClientException {

        // given
        stubFor(post(new UrlPathPattern(equalTo("/ISteamRemoteStorage/GetPublishedFileDetails/v1"), false))
                .withHeader("Content-Type", equalTo("application/x-www-form-urlencoded"))
                .withRequestBody(equalTo(TestResourceUtils.loadMockFileContent("mock-files/post_get_published_file_details_request.txt")))
                .willReturn(okJson(TestResourceUtils.loadMockFileContent("mock-files/post_get_published_file_details_response.json"))));

        // when
        PublishedFileDetailsResponse response = steamRemoteStorageClient.getPublishedFileDetails(new PublishedFileDetailsRequest(List.of(3122668835L, 210267782L)));

        // then
        assertThat(response).satisfies(resp -> {
            assertThat(resp.getResponse().getResult()).isEqualTo(1);
            assertThat(resp.getResponse().getResultCount()).isEqualTo(2);

            assertThat(resp.getResponse().getPublishedFileDetails().get(0)).satisfies(firstDetails -> {
                assertThat(firstDetails.getPublishedFileId()).isEqualTo(String.valueOf(3122668835L));
                assertThat(firstDetails.getCreator()).isEqualTo(76561199405322406L);
            });

            assertThat(resp.getResponse().getPublishedFileDetails().get(1)).satisfies(secondDetails -> {
                assertThat(secondDetails.getPublishedFileId()).isEqualTo(String.valueOf(210267782L));
                assertThat(secondDetails.getTags()).contains(
                        new PublishedFileDetailsResponse.QueryFilesResponse.PublishedFileDetails.Tag("Addon"),
                        new PublishedFileDetailsResponse.QueryFilesResponse.PublishedFileDetails.Tag("Weapon"),
                        new PublishedFileDetailsResponse.QueryFilesResponse.PublishedFileDetails.Tag("Fun")
                );
            });
        });
    }

    @Test
    void getCollectionDetailsReturnCollectionDetailsResponse() throws ClientException {
        // given
        stubFor(post(new UrlPathPattern(equalTo("/ISteamRemoteStorage/GetCollectionDetails/v1"), false))
                .withHeader("Content-Type", equalTo("application/x-www-form-urlencoded"))
                .withRequestBody(equalTo(TestResourceUtils.loadMockFileContent("mock-files/post_get_collection_details_request.txt")))
                .willReturn(okJson(TestResourceUtils.loadMockFileContent("mock-files/post_get_collection_details_response.json"))));

        // when
        CollectionDetailsResponse response = steamRemoteStorageClient.getCollectionDetails(new CollectionDetailsRequest(List.of(PUBLISHED_FILE_ID, PUBLISHED_FILE_ID_2, PUBLISHED_FILE_ID_3)));

        // then
        assertThat(response).satisfies(resp -> {
            assertThat(resp.getResponse().getResult()).isEqualTo(1);
            assertThat(resp.getResponse().getResultCount()).isEqualTo(2);
            assertThat(resp.getResponse().getCollectionDetails().get(0).getCollectionId()).isEqualTo(PUBLISHED_FILE_ID);

            assertThat(resp.getResponse().getCollectionDetails().get(0)).satisfies(firstDetails -> {
                assertThat(firstDetails.getResultAsEnum()).isEqualTo(CollectionDetailsResponse.Response.CollectionDetails.ResultType.FOUND);
            });

            assertThat(resp.getResponse().getCollectionDetails().get(1)).satisfies(secondDetails -> {
                assertThat(secondDetails.getResultAsEnum()).isEqualTo(CollectionDetailsResponse.Response.CollectionDetails.ResultType.FOUND);
            });

            assertThat(resp.getResponse().getCollectionDetails().get(2)).satisfies(thirdDetails -> {
                assertThat(thirdDetails.getItems()).isEmpty();
                assertThat(thirdDetails.getResultAsEnum()).isEqualTo(CollectionDetailsResponse.Response.CollectionDetails.ResultType.NOT_FOUND);
            });
        });
    }
}