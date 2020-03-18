package com.bluuminn.ncodeproject.domain.feed;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeedRepository extends JpaRepository<Feed, Long> {
    Page<Feed> findAllByDeletedOrderByCreateDateDesc(boolean deleted, Pageable pageable);

    Optional<Feed> findById(Long id);

    Feed save(Feed feed);
}
