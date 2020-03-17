package com.bluuminn.ncodeproject.application;

import com.bluuminn.ncodeproject.domain.feed.Feed;
import com.bluuminn.ncodeproject.domain.feed.FeedRepository;
import com.bluuminn.ncodeproject.domain.share.Share;
import com.bluuminn.ncodeproject.domain.share.ShareRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
@NoArgsConstructor
@AllArgsConstructor
public class ShareService {
    
    @Autowired
    private ShareRepository shareRepository;

    @Autowired
    private FeedRepository feedRepository;

    public List<Share> getShares(Long feedId) {
        Feed feed = feedRepository.findById(feedId).orElseThrow(EntityNotFoundException::new);
        if (feed.isDeleted()) {
            throw new EntityNotFoundException();
        }
        return shareRepository.findAllByFeedEquals(feed);
    }

    public void addShare(Share share, Long feedId) {
        Feed feed = feedRepository.findById(feedId).orElseThrow(EntityNotFoundException::new);
        share.updateFeed(feed);
        feed.addShared();
        shareRepository.save(share);
    }
}
