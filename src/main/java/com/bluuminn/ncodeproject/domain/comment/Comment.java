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

    private Long writerId;

    @MapsId(value = "feedId")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FEED_ID", updatable = false, insertable = false, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Feed feed;

    @Lob
    private String contents;

    private boolean deleted;

    public void delete() {
        deleted = true;
    }

    public void changeByInfomation(Comment commentInfomation) {
        this.contents = commentInfomation.getContents();
    }
}