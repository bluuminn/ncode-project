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
public class ShareDto {
    @Mapping("id")
    private Long id;

    @Mapping("userId")
    private Long userId;
}
