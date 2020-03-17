package com.bluuminn.ncodeproject.controller;

import com.bluuminn.ncodeproject.application.CommentService;
import com.bluuminn.ncodeproject.application.FeedService;
import com.bluuminn.ncodeproject.domain.comment.Comment;
import com.bluuminn.ncodeproject.dto.CommentDto;
import com.github.dozermapper.core.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/feeds/{feedId}/comments")
public class CommentController {

    @Autowired
    Mapper mapper;

    private final CommentService commentService;

    @Autowired
    private FeedService feedService;

    @GetMapping
    public List<CommentDto> list(
            @PathVariable Long feedId
    ) {
        List<Comment> comments = commentService.getComments(feedService.getFeed(feedId));

        return comments.stream()
                .map(comment -> mapper.map(comment, CommentDto.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
            @RequestBody CommentDto commentDto, @PathVariable Long feedId
    ) {
        System.out.println("feedId: " + feedId);
        commentDto.setFeed(feedService.getFeed(feedId));
        System.out.println("commentDto feedId: " + commentDto.getFeed().getId());
        Comment comment = mapper.map(commentDto, Comment.class);
        commentService.addComment(comment);
    }

    @PutMapping("{id}")
    public void update(
            @PathVariable Long id,
            @RequestBody CommentDto commentDto
    ) {
        Comment comment = mapper.map(commentDto, Comment.class);
        commentService.updateComment(id, comment);
    }

    @DeleteMapping("{id}")
    public void softDelete(
            @PathVariable Long id
    ) {
        commentService.deleteComment(id);
    }
}
