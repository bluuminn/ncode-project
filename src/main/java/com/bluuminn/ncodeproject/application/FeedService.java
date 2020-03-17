package com.bluuminn.ncodeproject.application;

import com.bluuminn.ncodeproject.domain.feed.Feed;
import com.bluuminn.ncodeproject.domain.feed.FeedRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
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
        return feedRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public void addFeed(Feed feed) {
        feedRepository.save(feed);
    }

    public Feed deleteFeed(Long id) {
        Feed feed = feedRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        feed.delete();
        return feed;
    }
}


