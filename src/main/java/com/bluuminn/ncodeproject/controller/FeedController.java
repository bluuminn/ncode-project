package com.bluuminn.ncodeproject.controller;

import com.bluuminn.ncodeproject.application.FeedService;
import com.bluuminn.ncodeproject.domain.Feed;
import com.bluuminn.ncodeproject.dto.FeedDto;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

//    @PostMapping
//    public void create() {
//
//    }
}
