package com.bluuminn.ncodeproject.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Feed {

    private Long id;
    private String mdImages;

    @Getter
    private String mdName;

    @Getter
    private String contents;

    @Getter
    private Integer countOfComments;

    @Getter
    private Integer countOfLikes;

    @Getter
    private Integer countOfShared;

}
