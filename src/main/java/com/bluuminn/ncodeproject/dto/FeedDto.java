package com.bluuminn.ncodeproject.dto;

import com.github.dozermapper.core.Mapping;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedDto {

    @Mapping("id")
    private Long id;

    @Mapping("mdImages")
    private String mdImages;

    @Mapping("mdName")
    private String mdName;

    @Mapping("contents")
    private String contents;

    @Mapping("countOfComments")
    private Integer countOfComments;

    @Mapping("countOfLikes")
    private Integer countOfLikes;

    @Mapping("countOfShared")
    private Integer countOfShared;

}
