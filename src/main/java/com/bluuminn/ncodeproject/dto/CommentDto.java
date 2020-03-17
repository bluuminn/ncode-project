package com.bluuminn.ncodeproject.dto;

import com.bluuminn.ncodeproject.domain.feed.Feed;
import com.github.dozermapper.core.Mapping;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto implements Serializable {

    @Mapping("id")
    private Long id;

    @NotNull
    @Mapping("writerId")
    private Long writerId;

    @Mapping("feed")
    private Feed feed;

    @NotBlank
    @Mapping("contents")
    private String contents;
}
