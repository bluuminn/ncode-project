package com.bluuminn.ncodeproject.domain.like;

import com.bluuminn.ncodeproject.domain.feed.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikedRepository extends JpaRepository<Liked, Long> {
    List<Liked> findAll();

    Optional<Liked> findById(Long id);

    Liked save(Liked like);

    List<Liked> findAllByFeedEquals(Feed feed);
}