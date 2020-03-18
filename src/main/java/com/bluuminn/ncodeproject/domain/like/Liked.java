package com.bluuminn.ncodeproject.domain.like;

import com.bluuminn.ncodeproject.domain.feed.Feed;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Liked {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_like_to_feed"))
    private Feed feed;

    public void updateFeed(Feed feed) {
        this.feed = feed;
    }
}
