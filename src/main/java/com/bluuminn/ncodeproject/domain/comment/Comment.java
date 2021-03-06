package com.bluuminn.ncodeproject.domain.comment;

import com.bluuminn.ncodeproject.domain.BaseTimeEntity;
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
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @ManyToOne
    @JoinColumn(name = "FEED_ID")
    private Feed feed;

    @Lob
    private String contents;

    private boolean deleted;

    public void delete() {
        deleted = true;
    }

    public void changeByInfomation(Comment commentInformation) {
        this.contents = commentInformation.getContents();
    }

    public void updateFeed(Feed feed) {
        this.feed = feed;
        feed.getComments().add(this);
    }
}