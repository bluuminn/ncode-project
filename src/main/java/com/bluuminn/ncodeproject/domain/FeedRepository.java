package com.bluuminn.ncodeproject.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedRepository extends JpaRepository<Feed, Long> {

    List<Feed> findAll();

    Feed save(Feed feed);

}
