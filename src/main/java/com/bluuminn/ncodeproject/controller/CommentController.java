package com.bluuminn.ncodeproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feeds/{feedId}/comments")
public class CommentController {

    @GetMapping
    public void list() {

    }
}
