package com.bluuminn.ncodeproject.controller;

import com.bluuminn.ncodeproject.application.LikedService;
import com.bluuminn.ncodeproject.domain.like.Liked;
import com.bluuminn.ncodeproject.dto.LikedDto;
import com.github.dozermapper.core.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/feeds/{feedId}/likes")
public class LikedController {

    @Autowired
    Mapper mapper;

    private final LikedService likedService;

    @GetMapping
    public List<LikedDto> list(
            @PathVariable Long feedId
    ) {
        List<Liked> likes = likedService.getLikes(feedId);

        return likes.stream()
                .map(liked -> mapper.map(liked, LikedDto.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
            @PathVariable Long feedId,
            @Valid @RequestBody LikedDto likedDto
    ) {
        Liked liked = mapper.map(likedDto, Liked.class);
        likedService.addLike(liked, feedId);
    }

    @DeleteMapping("{id}")
    public void destroy(
            @PathVariable Long feedId,
            @PathVariable Long id
    ) {
        likedService.deleteLike(id, feedId);
    }
}
