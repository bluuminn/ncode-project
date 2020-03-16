package com.bluuminn.ncodeproject.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FeedRepository {

    private List<Feed> feeds = new ArrayList<>();

    public List<Feed> findAll() {
        return feeds;
    }

    public void save(Feed feed) {
        feeds.add(feed);
    }
}
