package com.bluuminn.ncodeproject.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Feed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
