package com.bluuminn.ncodeproject.controller;

import com.bluuminn.ncodeproject.application.ShareService;
import com.bluuminn.ncodeproject.domain.share.Share;
import com.bluuminn.ncodeproject.dto.ShareDto;
import com.github.dozermapper.core.Mapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.action.internal.EntityActionVetoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
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
    public void list() {
        throw new EntityNotFoundException();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
            @PathVariable Long feedId,
            @Valid @RequestBody ShareDto shareDto
    ) {
        Share share = mapper.map(shareDto, Share.class);
        shareService.addShare(share, feedId);
    }
}
