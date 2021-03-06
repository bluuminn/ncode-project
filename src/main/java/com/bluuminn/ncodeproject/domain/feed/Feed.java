package com.bluuminn.ncodeproject.domain.feed;

import com.bluuminn.ncodeproject.domain.BaseTimeEntity;
import com.bluuminn.ncodeproject.domain.comment.Comment;
import com.bluuminn.ncodeproject.domain.like.Liked;
import com.bluuminn.ncodeproject.domain.share.Share;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Feed extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mdImages;

    private String mdName;

    @Lob
    private String contents;

    @OneToMany(mappedBy = "feed")
    @OrderBy("createDate ASC")
    private List<Comment> comments;

    @OneToMany(mappedBy = "feed")
    private List<Liked> likes;

    @OneToMany(mappedBy = "feed")
    private List<Share> shares;

    /* H2 db test 위해서 primitive 타입으로 설정 함
     *  보통은 래퍼 클래스 타입으로 지정 */
    private int countOfComments;
    private int countOfLikes;
    private int countOfShared;
    private boolean deleted;

    public void delete() {
        deleted = true;
    }

    public void updateComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addComment() {
        this.countOfComments += 1;
    }

    public void deleteComment() {
        this.countOfComments -= 1;
    }

    public void addLiked() {
        this.countOfLikes += 1;
    }

    public void deleteLiked() {
        this.countOfLikes -= 1;
    }

    public void addShared() {
        this.countOfShared += 1;
    }
}