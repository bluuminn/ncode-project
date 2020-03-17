package com.bluuminn.ncodeproject.application;

import com.bluuminn.ncodeproject.domain.feed.Feed;
import com.bluuminn.ncodeproject.domain.feed.FeedRepository;
import com.bluuminn.ncodeproject.domain.like.Liked;
import com.bluuminn.ncodeproject.domain.like.LikedRepository;
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
public class LikedService {

    @Autowired
    private LikedRepository likedRepository;

    @Autowired
    private FeedRepository feedRepository;

    public List<Liked> getLikes(Long feedId) {
        Feed feed = feedRepository.findById(feedId).orElseThrow(EntityNotFoundException::new);
        return likedRepository.findAllByFeedEquals(feed);
    }

    public void addLike(Liked liked, Long feedId) {
        Feed feed = feedRepository.findById(feedId).orElseThrow(EntityNotFoundException::new);
        liked.updateFeed(feed);
        feed.addLiked();
        likedRepository.save(liked);
    }

    public void deleteLike(Long id, Long feedId) {
        Feed feed = feedRepository.findById(feedId).orElseThrow(EntityNotFoundException::new);
        Liked liked = likedRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        feed.deleteLiked();
        likedRepository.deleteById(id);
    }

}
