package com.bluuminn.ncodeproject.controller;

import com.bluuminn.ncodeproject.application.FeedService;
import com.bluuminn.ncodeproject.domain.Feed;
import com.bluuminn.ncodeproject.dto.FeedDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/feeds")
public class FeedController {

    @Autowired
    private FeedService feedService;

    @GetMapping
    public List<FeedDto> list() {

        List<Feed> feeds = feedService.getFeeds();

        List<FeedDto> feedDtos = new ArrayList<>();

        for (Feed feed : feeds) {
            FeedDto feedDto = FeedDto.builder()
                    .mdName(feed.getMdName())
                    .contents(feed.getContents())
                    .countOfComments(feed.getCountOfComments())
                    .countOfLikes(feed.getCountOfLikes())
                    .countOfShared(feed.getCountOfShared())
                    .build();

            feedDtos.add(feedDto);
        }

        return feedDtos;
    }

    @PostMapping
    public void create() {

    }
}
