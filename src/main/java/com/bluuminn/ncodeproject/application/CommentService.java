package com.bluuminn.ncodeproject.application;

import com.bluuminn.ncodeproject.domain.comment.Comment;
import com.bluuminn.ncodeproject.domain.comment.CommentRepository;
import com.bluuminn.ncodeproject.domain.feed.Feed;
import com.bluuminn.ncodeproject.domain.feed.FeedRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
@NoArgsConstructor
@AllArgsConstructor
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private FeedRepository feedRepository;

    public CommentService(CommentRepository commentRepository) {
    }

    public List<Comment> getComments(Long feedId) {
        Feed feed = feedRepository.findByDeletedAndId(false, feedId).orElseThrow(EntityNotFoundException::new);
        if (feed.isDeleted()) {
            throw new EntityNotFoundException();
        }
        return commentRepository.findAllByDeletedAndFeedEquals(false, feed);
    }

    public void addComment(Comment comment, Long feedId) {
        Feed feed = feedRepository.findById(feedId).orElseThrow(EntityNotFoundException::new);
        comment.updateFeed(feed);
        feed.addComment();
        commentRepository.save(comment);
    }

    public Comment deleteComment(Long id, Long feedId) {
        Feed feed = feedRepository.findById(feedId).orElseThrow(EntityNotFoundException::new);
        Comment comment = commentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        comment.delete();
        feed.deleteComment();
        return comment;
    }

    public Comment updateComment(Long id, Long feedId, Comment commentInformation) {
        feedRepository.findById(feedId).orElseThrow(EntityNotFoundException::new);
        Comment comment = commentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (!feedId.equals(comment.getFeed().getId())) {
            throw new EntityNotFoundException();
        }
        comment.changeByInfomation(commentInformation);
        return comment;
    }
}
