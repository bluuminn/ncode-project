package com.bluuminn.ncodeproject.controller;

import com.bluuminn.ncodeproject.application.ShareService;
import com.bluuminn.ncodeproject.domain.share.Share;
import com.bluuminn.ncodeproject.dto.ShareDto;
import com.github.dozermapper.core.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/feeds/{feedId}/shared")
public class ShareController {

    @Autowired
    Mapper mapper;

    private final ShareService shareService;

    @GetMapping
    public List<ShareDto> list(
            @PathVariable Long feedId
    ) {
        List<Share> shares = shareService.getShares(feedId);

        return shares.stream()
                .map(share -> mapper.map(share, ShareDto.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
            @PathVariable Long feedId,
            @Valid @RequestBody ShareDto shareDto
    ) {
        Share share = mapper.map(shareDto, Share.class);
        shareService.addShare(share, feedId);
    }
}
