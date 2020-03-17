package com.bluuminn.ncodeproject.domain.feed;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FeedRepository extends JpaRepository<Feed, Long> {
    List<Feed> findAll();

    List<Feed> findAllByDeleted(boolean deleted);

    Optional<Feed> findById(Long id);

    Feed save(Feed feed);
}
