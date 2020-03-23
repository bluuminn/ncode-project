package com.bluuminn.ncodeproject.application;

import com.bluuminn.ncodeproject.domain.feed.Feed;
import com.bluuminn.ncodeproject.domain.feed.FeedRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@AllArgsConstructor
public class FeedService {

    @Autowired
    private FeedRepository feedRepository;

    public Page<Feed> getFeeds(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), 10);
        Page<Feed> feeds = feedRepository.findAllByDeletedOrderByCreateDateDesc(false, pageable);

        if (feeds.getNumber() >= feeds.getTotalPages()) {
            throw new EntityNotFoundException();
        }

        return feeds;
    }

    public Feed getFeed(Long id) {
        return feedRepository.findByDeletedAndId(false, id).orElseThrow(EntityNotFoundException::new);
    }

    public void addFeed(Feed feed) {
        feedRepository.save(feed);
    }

    public Feed deleteFeed(Long id) {
        Feed feed = feedRepository.findByDeletedAndId(false, id).orElseThrow(EntityNotFoundException::new);
        feed.delete();
        return feed;
    }

}


