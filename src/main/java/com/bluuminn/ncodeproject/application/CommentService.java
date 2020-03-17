package com.bluuminn.ncodeproject.application;

import com.bluuminn.ncodeproject.domain.comment.Comment;
import com.bluuminn.ncodeproject.domain.comment.CommentRepository;
import com.bluuminn.ncodeproject.domain.feed.Feed;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getComments(Feed feed) {
        return commentRepository.findAllByDeletedAndFeedEquals(false, feed);
    }

    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }

    public Comment deleteComment(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        comment.delete();
        return comment;
    }

    public Comment updateComment(Long id, Comment commentInfomation) {
        Comment comment = commentRepository.findById(id).get();

        comment.changeByInfomation(commentInfomation);

        return comment;
    }
}
