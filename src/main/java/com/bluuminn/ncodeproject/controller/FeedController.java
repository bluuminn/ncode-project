package com.bluuminn.ncodeproject.controller;

import com.bluuminn.ncodeproject.dto.FeedDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedController {

    @GetMapping("/feed")
    public FeedDto feed() {
        return FeedDto.builder()
                .mdName("D.FASHION")
                .contents("빌리 아일리시와 H&M의 만남.")
                .countOfComments(3)
                .countOfLikes(4)
                .countOfShared(0)
                .build();
    }
}
