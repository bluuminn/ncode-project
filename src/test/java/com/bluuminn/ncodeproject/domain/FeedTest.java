package com.bluuminn.ncodeproject.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FeedTest {

    private Feed feed;

    @BeforeEach
    public void setUp() {
        feed = Feed.builder()
                .mdName("D.FASHION")
                .contents("빌리 아일리시와 H&M의 만남.")
                .countOfComments(3)
                .countOfLikes(4)
                .countOfShared(0)
                .build();
    }

    @Test
    public void checkFeed() {
        assertThat(feed.getMdName()).isEqualTo("D.FASHION");
        assertThat(feed.getCountOfComments()).isEqualTo(3);
    }

}