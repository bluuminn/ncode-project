package com.bluuminn.ncodeproject.controller;

import com.bluuminn.ncodeproject.application.CommentService;
import com.bluuminn.ncodeproject.domain.comment.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
@ActiveProfiles("test")
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    private Comment comment;

    @BeforeEach
    public void setUp() {
        comment = Comment.builder()
                .contents("댓글내용")
                .writerId(1234L)
                .build();
    }

    @Test
    public void list() throws Exception {

        given(commentService.getComments(13L))
                .willReturn(Arrays.asList(Comment.builder()
                        .contents("댓글내용")
                        .writerId(1234L)
                        .build()));

        mockMvc.perform(get("/feeds/5/comments")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().encoding("UTF-8"))
                .andExpect(content().string(containsString("")));

        verify(commentService).getComments(13L);
    }


    @Test
    public void create() throws Exception {
        mockMvc.perform(post("/feeds/5/comments")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"contents\":\"댓글내용\", \"writerId\":1234L, \"feedId\":5}")
        ).andExpect(status().isCreated());
        verify(commentService).addComment(any(Comment.class), eq(13L));
    }

    @Test
    public void update() throws Exception {
        mockMvc.perform(put("/feeds/5/comments/13")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"contents\":\"댓글내용수정\"}")
        ).andExpect(status().isOk());

        verify(commentService).updateComment(eq(13L), eq(12L), any(Comment.class));
    }

    @Test
    public void softDelete() throws Exception {
        mockMvc.perform(delete("/feeds/5/comments/5"))
                .andExpect(status().isOk());

        verify(commentService).deleteComment(5L, 13L);
    }

}