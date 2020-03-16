package com.bluuminn.ncodeproject.application;

import com.bluuminn.ncodeproject.domain.Feed;
import com.bluuminn.ncodeproject.domain.FeedRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FeedService {

    private FeedRepository feedRepository;

    public List<Feed> getFeeds() {
        return feedRepository.findAll();
    }

    public void addFeed(Feed feed) {
        feedRepository.save(feed);
    }
}
