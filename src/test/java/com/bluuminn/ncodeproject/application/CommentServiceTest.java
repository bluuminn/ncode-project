package com.bluuminn.ncodeproject.application;

import com.bluuminn.ncodeproject.domain.comment.Comment;
import com.bluuminn.ncodeproject.domain.comment.CommentRepository;
import com.bluuminn.ncodeproject.domain.feed.Feed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class CommentServiceTest {
    private CommentService commentService;

    @Mock
    private CommentRepository commentRepository;

    private Comment comment;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        commentService = new CommentService(commentRepository);

        comment = Comment.builder()
                .contents("댓글내용")
                .userId(1234L)
                .build();
    }

    @Test
    public void getComments() throws Exception {

        Feed feed = Feed.builder()
                .id(5L)
                .build();

        given(commentRepository.findAllByDeletedAndFeedEquals(false, feed)).willReturn(Arrays.asList(comment));

        List<Comment> comments = commentService.getComments(13L);

        assertThat(comments).hasSize(1);
        assertThat(comments.get(0).getContents()).isEqualTo("댓글내용");

        verify(commentRepository).findAllByDeletedAndFeedEquals(false, feed);
    }

    @Test
    public void addComment() {
        commentService.addComment(comment, 13L);

        verify(commentRepository).save(comment);
    }

    @Test
    public void updateComment() {

        given(commentRepository.findById(13L)).willReturn(Optional.of(Comment.builder().contents("댓글내용").build()));

        Comment comment = Comment.builder()
                .contents("수정한댓글")
                .userId(1234L)
                .build();

        Comment updatedComment = commentService.updateComment(13L, 13L, comment);

        assertThat(updatedComment.getContents()).isEqualTo("수정한댓글");

        verify(commentRepository).findById(13L);
    }

    @Test
    public void deleteComment() {

        given(commentRepository.findById(13L)).willReturn(Optional.of(Comment.builder().contents("댓글내용").build()));

        Comment comment = commentService.deleteComment(1L, 13L);

        verify(commentRepository).findById(13L);

        assertThat(comment.isDeleted()).isTrue();
    }
}
