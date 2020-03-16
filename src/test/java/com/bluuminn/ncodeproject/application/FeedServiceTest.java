package com.bluuminn.ncodeproject.application;

import com.bluuminn.ncodeproject.domain.Feed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FeedServiceTest {

    private FeedService feedService;

    @BeforeEach
    public void setUp() {
        feedService = new FeedService();
    }

    @Test
    public void getFeeds() {
        List<Feed> feeds = feedService.getFeeds();

        assertThat(feeds).hasSize(1);

        assertThat(feeds.get(0).getMdName()).isEqualTo("엔코드");
    }

}