package com.bluuminn.ncodeproject.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Feed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mdImages;

    private String mdName;

    @Lob
    private String contents;

    /* H2 db test 위해서 primitive 타입으로 설정 함
    *  보통은 래퍼 클래스 타입으로 지정 */
    private int countOfComments;
    private int countOfLikes;
    private int countOfShared;
    private boolean deleted;

    public void delete() {
        deleted = true;
    }
}