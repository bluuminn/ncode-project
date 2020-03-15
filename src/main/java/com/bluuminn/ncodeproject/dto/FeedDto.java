package com.bluuminn.ncodeproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedDto {

    private Long id;
    private String mdImages;
    private String mdName;
    private String contents;
    private Integer countOfComments;
    private Integer countOfLikes;
    private Integer countOfShared;

}
