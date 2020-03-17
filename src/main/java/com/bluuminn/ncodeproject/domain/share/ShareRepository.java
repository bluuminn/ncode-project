package com.bluuminn.ncodeproject.domain.share;

import com.bluuminn.ncodeproject.domain.feed.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShareRepository extends JpaRepository<Share, Long> {
    List<Share> findAll();

    Optional<Share> findById(Long id);

    Share save(Share share);

    List<Share> findAllByFeedEquals(Feed feed);
}
