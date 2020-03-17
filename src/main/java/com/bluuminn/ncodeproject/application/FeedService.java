package com.bluuminn.ncodeproject.application;

import com.bluuminn.ncodeproject.domain.feed.Feed;
import com.bluuminn.ncodeproject.domain.feed.FeedRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class FeedService {
    //TODO: 피드 삭제 되면 거기에 딸린 댓글,좋아요,공유 모두 지워 주기

    @Autowired
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


