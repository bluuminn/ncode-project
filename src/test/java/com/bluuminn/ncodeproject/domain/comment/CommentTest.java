package com.bluuminn.ncodeproject.domain.comment;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CommentTest {

    @Test
    public void checkComment() {
        Comment comment = Comment.builder()
                .contents("댓글내용")
                .userId(1234L)
                .build();

        assertThat(comment.getContents()).isEqualTo("댓글내용");
        assertThat(comment.getUserId()).isEqualTo(1234L);
    }

}