package com.bluuminn.ncodeproject.controller;

import com.bluuminn.ncodeproject.application.CommentService;
import com.bluuminn.ncodeproject.domain.comment.Comment;
import com.bluuminn.ncodeproject.dto.CommentDto;
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
@RequestMapping("/feeds/{feedId}/comments")
public class CommentController {

    @Autowired
    Mapper mapper;

    private final CommentService commentService;

    @GetMapping
    public List<CommentDto> list(
            @PathVariable Long feedId
    ) {
        List<Comment> comments = commentService.getComments(feedId);

        return comments.stream()
                .map(comment -> mapper.map(comment, CommentDto.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
            @PathVariable Long feedId,
            @Valid @RequestBody CommentDto commentDto
    ) {
        Comment comment = mapper.map(commentDto, Comment.class);
        commentService.addComment(comment, feedId);
    }

    @PutMapping("{id}")
    public void update(
            @PathVariable Long id,
            @PathVariable Long feedId,
            @RequestBody CommentDto commentDto
    ) {
        Comment comment = mapper.map(commentDto, Comment.class);
        commentService.updateComment(id, feedId, comment);
    }

    @DeleteMapping("{id}")
    public void softDelete(
            @PathVariable Long feedId,
            @PathVariable Long id
    ) {
        commentService.deleteComment(id, feedId);
    }
}
