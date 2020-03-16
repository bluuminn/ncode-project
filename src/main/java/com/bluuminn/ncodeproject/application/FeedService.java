package com.bluuminn.ncodeproject.application;

import com.bluuminn.ncodeproject.domain.Feed;
import com.bluuminn.ncodeproject.domain.FeedRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class FeedService {

    private FeedRepository feedRepository;

    public List<Feed> getFeeds() {
        return feedRepository.findAllByDeleted(false);
    }

    public Feed getFeed(Long id) {
        return feedRepository.findById(id).get();
    }

    public void addFeed(Feed feed) {
        feedRepository.save(feed);
    }

    public Feed deleteFeed(Long id) {
        Feed feed = feedRepository.findById(id).get();
        feed.delete();
        return feed;
    }
}
