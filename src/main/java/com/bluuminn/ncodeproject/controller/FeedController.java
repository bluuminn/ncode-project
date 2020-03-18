package com.bluuminn.ncodeproject.controller;

import com.bluuminn.ncodeproject.application.FeedService;
import com.bluuminn.ncodeproject.domain.feed.Feed;
import com.bluuminn.ncodeproject.dto.FeedDto;
import com.github.dozermapper.core.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/feeds")
public class FeedController {

    @Autowired
    Mapper mapper;

    private final FeedService feedService;

    @GetMapping
    public List<FeedDto> list(
            @PageableDefault Pageable pageable
    ) {
        Page<Feed> feeds = feedService.getFeeds(pageable);

        log.debug("총 element 수 : {}, 전체 page 수 : {}, 페이지 당 최대 element 수 : {}, 현재 페이지 index : {}, 현재 페이지의 element 수 : {}",
                feeds.getTotalElements(), feeds.getTotalPages(), feeds.getSize(),
                feeds.getNumber(), feeds.getNumberOfElements());

//        for (Feed feed : feeds) {
//            feed.updateComments();
//        }

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
            @Valid @RequestBody FeedDto feedDto
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
