package com.bluuminn.ncodeproject.controller;

import com.bluuminn.ncodeproject.application.FeedService;
import com.bluuminn.ncodeproject.domain.Feed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FeedController.class)
class FeedControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FeedService feedService;

    private Feed feed;

    @BeforeEach
    public void setUp() {
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
    public void list() throws Exception {

        given(feedService.getFeeds()).willReturn(Arrays.asList(Feed.builder()
                .mdImages("md이미지")
                .mdName("md이름")
                .contents("피드내용")
                .countOfComments(3)
                .countOfLikes(4)
                .countOfShared(0)
                .build()));

        mockMvc.perform(get("/feeds")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().encoding("UTF-8"))
                .andExpect(content().string(containsString("")));
    }


    @Test
    public void detail() throws Exception {

        given(feedService.getFeed(13L)).willReturn(feed);

        mockMvc.perform(get("/feeds/13")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("피드내용")));

        verify(feedService).getFeed(13L);
    }

    @Test
    public void create() throws Exception {
        mockMvc.perform(post("/feeds")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"mdImages\":\"md이미지\", \"mdName\":\"md이름\", \"contents\":\"피드1내용\", \"countOfComments\":3}")
        ).andExpect(status().isCreated());

        verify(feedService).addFeed(any(Feed.class));
    }
}