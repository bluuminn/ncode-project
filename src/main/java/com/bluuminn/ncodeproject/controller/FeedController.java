package com.bluuminn.ncodeproject.controller;

import com.bluuminn.ncodeproject.domain.Feed;
import com.bluuminn.ncodeproject.dto.FeedDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feed")
public class FeedController {

    @GetMapping
    public FeedDto list() {
        FeedDto feedDto = FeedDto.builder()
                .mdName("엔코드")
                .contents("프로젝트")
                .countOfComments(3)
                .countOfLikes(4)
                .countOfShared(0)
                .build();

        return feedDto;
    }

    @PostMapping
    public void create() {

    }
}
