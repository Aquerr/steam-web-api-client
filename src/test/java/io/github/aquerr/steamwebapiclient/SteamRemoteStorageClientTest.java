package io.github.aquerr.steamwebapiclient;

import io.github.aquerr.steamwebapiclient.request.PublishedFileDetailsRequest;
import io.github.aquerr.steamwebapiclient.response.PublishedFileDetailsResponse;
import io.github.aquerr.steamwebapiclient.util.SteamHttpClient;
import io.github.aquerr.steamwebapiclient.util.UrlEncodedForm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class SteamRemoteStorageClientTest {

    private static final long PUBLISHED_FILE_ID = 123456789L;
    private static final long PUBLISHED_FILE_ID_2 = 9532492348L;

    @Mock
    private SteamHttpClient steamHttpClient;

    @InjectMocks
    private SteamRemoteStorageClient steamRemoteStorageClient;

    @Test
    void getPublishedFileDetailsReturnPublishedFileDetailsResponse() {

        // given
        Map<String, String> parameters = new HashMap<>();
        parameters.put("itemcount", String.valueOf(2));
        parameters.put("publishedfileids[0]", String.valueOf(PUBLISHED_FILE_ID));
        parameters.put("publishedfileids[1]", String.valueOf(PUBLISHED_FILE_ID_2));
        UrlEncodedForm urlEncodedForm = UrlEncodedForm.of(parameters);

        PublishedFileDetailsResponse expectedResponse = preparePublishedFileDetailsResponse(List.of(PUBLISHED_FILE_ID, PUBLISHED_FILE_ID_2));

        given(steamHttpClient.post(SteamWebApiInterfaceMethod.I_STEAM_REMOTE_STORAGE_GET_PUBLISHED_FILE_DETAILS,
                SteamWebApiClient.API_VERSION_1,
                urlEncodedForm,
                PublishedFileDetailsResponse.class)).willReturn(expectedResponse);

        // when
        PublishedFileDetailsResponse response = steamRemoteStorageClient.getPublishedFileDetails(new PublishedFileDetailsRequest(List.of(PUBLISHED_FILE_ID, PUBLISHED_FILE_ID_2)));

        // then
        assertThat(response).isEqualTo(expectedResponse);
    }

    private PublishedFileDetailsResponse preparePublishedFileDetailsResponse(List<Long> publishedFileIds) {
        PublishedFileDetailsResponse publishedFileDetailsResponse = new PublishedFileDetailsResponse();
        PublishedFileDetailsResponse.QueryFilesResponse queryFilesResponse = new PublishedFileDetailsResponse.QueryFilesResponse();
        publishedFileDetailsResponse.setResponse(queryFilesResponse);
        List<PublishedFileDetailsResponse.QueryFilesResponse.PublishedFileDetails> publishedFileDetailsList = new ArrayList<>();
        queryFilesResponse.setPublishedFileDetails(publishedFileDetailsList);

        publishedFileIds.forEach(id -> {
            PublishedFileDetailsResponse.QueryFilesResponse.PublishedFileDetails publishedFileDetails = new PublishedFileDetailsResponse.QueryFilesResponse.PublishedFileDetails();
            publishedFileDetails.setPublishedFileId(String.valueOf(id));
            publishedFileDetailsList.add(publishedFileDetails);
        });
        return publishedFileDetailsResponse;
    }
}