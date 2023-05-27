package io.github.aquerr.steamwebapiclient.response;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WorkShopQueryResponseTest
{
    private static final long CREATED_TIME_SECONDS = 1445800572L;
    private static final long UPDATED_TIME_SECONDS = 1640866389L;

    private static final ZonedDateTime CREATED_OFFSET_DATE_TIME = ZonedDateTime.of(LocalDateTime.of(2015, 10, 25, 19, 16, 12), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_OFFSET_DATE_TIME = ZonedDateTime.of(LocalDateTime.of(2021, 12, 30, 12, 13, 9), ZoneOffset.UTC);

    @Test
    void getCreatedDateTimeShouldConvertCreatedTimeFieldToZonedDateTime() {

        WorkShopQueryResponse response = prepareResponse();
        ZonedDateTime createdDateTime = response.getResponse().getPublishedFileDetails().get(0).getCreatedDateTime();

        assertThat(createdDateTime).isEqualTo(CREATED_OFFSET_DATE_TIME);
    }

    @Test
    void getCreatedDateTimeShouldConvertUpdatedTimeFieldToZonedDateTime() {

        WorkShopQueryResponse response = prepareResponse();
        ZonedDateTime updatedDateTime = response.getResponse().getPublishedFileDetails().get(0).getUpdatedDateTime();

        assertThat(updatedDateTime).isEqualTo(UPDATED_OFFSET_DATE_TIME);
    }

    private WorkShopQueryResponse prepareResponse() {
        WorkShopQueryResponse workShopQueryResponse = new WorkShopQueryResponse();
        WorkShopQueryResponse.QueryFilesResponse queryFilesResponse = new WorkShopQueryResponse.QueryFilesResponse();
        workShopQueryResponse.setResponse(queryFilesResponse);
        List<WorkShopQueryResponse.QueryFilesResponse.PublishedFileDetails> publishedFileDetailsList = new ArrayList<>();
        queryFilesResponse.setPublishedFileDetails(publishedFileDetailsList);
        WorkShopQueryResponse.QueryFilesResponse.PublishedFileDetails publishedFileDetails = new WorkShopQueryResponse.QueryFilesResponse.PublishedFileDetails();
        publishedFileDetails.setCreatedTimeSeconds(CREATED_TIME_SECONDS);
        publishedFileDetails.setUpdatedTimeSeconds(UPDATED_TIME_SECONDS);
        publishedFileDetailsList.add(publishedFileDetails);
        return workShopQueryResponse;
    }
}