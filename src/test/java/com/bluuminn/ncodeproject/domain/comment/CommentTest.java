package com.bluuminn.ncodeproject.domain.comment;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CommentTest {

    @Test
    public void checkComment() {
        Comment comment = Comment.builder()
                .contents("댓글내용")
                .writerId(1234L)
                .build();

        assertThat(comment.getContents()).isEqualTo("댓글내용");
        assertThat(comment.getWriterId()).isEqualTo(1234L);
    }

}