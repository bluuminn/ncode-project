package com.bluuminn.ncodeproject.domain.comment;

import com.bluuminn.ncodeproject.domain.feed.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAll();

    Optional<Comment> findById(Long id);

    Comment save(Comment comment);

    List<Comment> findAllByDeletedAndFeedEquals(boolean deleted, Feed feed);

}
