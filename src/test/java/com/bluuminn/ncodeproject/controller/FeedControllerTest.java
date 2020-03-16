package com.bluuminn.ncodeproject.controller;

import com.bluuminn.ncodeproject.application.FeedService;
import com.bluuminn.ncodeproject.domain.Feed;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FeedController.class)
class FeedControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FeedService feedService;

    @Test
    public void list() throws Exception {

        given(feedService.getFeeds()).willReturn(Arrays.asList(Feed.builder()
                .mdName("엔코드")
                .contents("프로젝트")
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

}