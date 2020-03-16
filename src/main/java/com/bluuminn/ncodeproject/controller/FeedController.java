package com.bluuminn.ncodeproject.controller;

import com.bluuminn.ncodeproject.application.FeedService;
import com.bluuminn.ncodeproject.domain.feed.Feed;
import com.bluuminn.ncodeproject.dto.FeedDto;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/feeds")
public class FeedController {

    @Autowired
    Mapper mapper;

    @Autowired
    private FeedService feedService;

    @GetMapping
    public List<FeedDto> list() {

        List<Feed> feeds = feedService.getFeeds();

        return feeds.stream()
                .map(feed -> mapper.map(feed, FeedDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public FeedDto detail(
            @PathVariable Long id
    ) {
        Feed feed = feedService.getFeed(id);
        return mapper.map(feed, FeedDto.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
            @RequestBody FeedDto feedDto
    ) {
        Feed feed = mapper.map(feedDto, Feed.class);
        feedService.addFeed(feed);
    }

    @DeleteMapping("{id}")
    public void softDelete(
            @PathVariable Long id
    ) {
        feedService.deleteFeed(id);
    }
}
