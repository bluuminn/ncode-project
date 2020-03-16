package com.bluuminn.ncodeproject.application;

import com.bluuminn.ncodeproject.domain.Feed;
import com.bluuminn.ncodeproject.domain.FeedRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class FeedServiceTest {

    private FeedService feedService;

    @Mock
    private FeedRepository feedRepository;
    private Feed feed;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        feedService = new FeedService(feedRepository);

        feed = Feed.builder()
                .mdImages("md이미지")
                .mdName("md이름")
                .contents("피드내용")
                .countOfComments(3)
                .countOfLikes(4)
                .countOfShared(0)
                .build();
    }

    @Test
    public void getFeeds() {

        given(feedRepository.findAll()).willReturn(Arrays.asList(feed));

        List<Feed> feeds = feedService.getFeeds();

        assertThat(feeds).hasSize(1);

        assertThat(feeds.get(0).getMdName()).isEqualTo("md이름");

        verify(feedRepository).findAll();
    }

    @Test
    public void addFeed() {
        feedService.addFeed(feed);

        verify(feedRepository).save(feed);
    }
}