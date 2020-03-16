package com.bluuminn.ncodeproject.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FeedRepository extends JpaRepository<Feed, Long> {

    List<Feed> findAll();

    Optional<Feed> findById(Long id);

    Feed save(Feed feed);

}
