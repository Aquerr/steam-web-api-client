package io.github.aquerr.steamwebapiclient;

import io.github.aquerr.steamwebapiclient.request.NewsForAppRequest;
import io.github.aquerr.steamwebapiclient.response.NewsForAppResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static io.github.aquerr.steamwebapiclient.SteamWebApiClient.API_VERSION_2;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SteamNewsWebApiClientTest {

    private static final long APP_ID = 1L;
    private static final int COUNT = 1;
    private static final int MAX_LENGTH = 1;
    private static final String FEEDS = "gaming,news";
    private static final long NEWS_DATE = Date.from(Instant.parse("2022-02-02T22:22:22.000Z")).getTime();
    private static final String NEWS_ITEM_ID = "NEWS_ITEM_ID";
    private static final String NEWS_ITEM_TITLE = "NEWS_ITEM_TITLE";
    private static final String NEWS_ITEM_URL = "NEWS_ITEM_URL";
    private static final String NEWS_ITEM_AUTHOR = "NEWS_ITEM_AUTHOR";
    private static final String NEWS_ITEM_CONTENT = "NEWS_ITEM_CONTENT";
    private static final String NEWS_ITEM_FEED_LABEL = "NEWS_ITEM_FEED_LABEL";
    private static final String NEWS_ITEM_FEED_NAME = "NEWS_ITEM_FEED_NAME";
    private static final int NEWS_ITEM_FEED_TYPE = 1;

    @Mock
    private SteamHttpClient steamHttpClient;

    @InjectMocks
    private SteamNewsWebApiClient client;

    @Test
    void shouldGetNewsForApp() {
        //given
        NewsForAppRequest request = prepareNewsForAppRequest();
        given(client.getNewsForApp(request)).willReturn(prepareNewsForAppResponse());

        //when
        NewsForAppResponse response = client.getNewsForApp(request);

        //then
        verify(steamHttpClient).get(
                SteamWebApiInterfaceMethod.I_STEAM_NEWS_GET_NEWS_FOR_APP,
                API_VERSION_2,
                request,
                NewsForAppResponse.class);
        Assertions.assertNotNull(response);
    }

    private NewsForAppRequest prepareNewsForAppRequest() {
        return NewsForAppRequest.builder()
                .appId(APP_ID)
                .maxLength(MAX_LENGTH)
                .endDate(NEWS_DATE)
                .count(COUNT)
                .feeds(FEEDS)
                .build();
    }

    private NewsForAppResponse prepareNewsForAppResponse() {
        NewsForAppResponse response = new NewsForAppResponse();
        response.setResponse(prepareNewsForAppResponseResponse());
        return response;
    }

    private NewsForAppResponse.Response prepareNewsForAppResponseResponse() {
        NewsForAppResponse.Response response = new NewsForAppResponse.Response();
        response.setAppId(APP_ID);
        response.setNewsItems(List.of(prepareNewsItem()));
        response.setCount(COUNT);
        return response;
    }

    private NewsForAppResponse.Response.NewsItem prepareNewsItem() {
        NewsForAppResponse.Response.NewsItem newsItem = new NewsForAppResponse.Response.NewsItem();
        newsItem.setId(NEWS_ITEM_ID);
        newsItem.setTitle(NEWS_ITEM_TITLE);
        newsItem.setUrl(NEWS_ITEM_URL);
        newsItem.setExternalUrl(Boolean.TRUE);
        newsItem.setAuthor(NEWS_ITEM_AUTHOR);
        newsItem.setContent(NEWS_ITEM_CONTENT);
        newsItem.setFeedLabel(NEWS_ITEM_FEED_LABEL);
        newsItem.setCreationDate(NEWS_DATE);
        newsItem.setFeedName(NEWS_ITEM_FEED_NAME);
        newsItem.setFeedType(NEWS_ITEM_FEED_TYPE);
        newsItem.setAppId(APP_ID);
        return newsItem;
    }

}
