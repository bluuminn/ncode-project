package com.bluuminn.ncodeproject.dto;

import com.github.dozermapper.core.Mapping;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedDto {

    @Mapping("id")
    private Long id;

    @NotBlank
    @Mapping("mdImages")
    private String mdImages;

    @NotBlank
    @Mapping("mdName")
    private String mdName;

    @NotBlank
    @Mapping("contents")
    private String contents;

    @Mapping("countOfComments")
    private Integer countOfComments;

    @Mapping("countOfLikes")
    private Integer countOfLikes;

    @Mapping("countOfShared")
    private Integer countOfShared;

}
