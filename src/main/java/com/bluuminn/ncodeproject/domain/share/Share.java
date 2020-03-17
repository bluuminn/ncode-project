package com.bluuminn.ncodeproject.domain.share;

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
public class Share {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_shared_to_feed"))
    private Feed feed;

    public void updateFeed(Feed feed) {
        this.feed = feed;
    }
}
