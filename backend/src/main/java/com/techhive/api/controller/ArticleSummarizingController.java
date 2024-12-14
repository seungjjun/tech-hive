package com.techhive.api.controller;

import com.techhive.application.TechArticleSummarizingUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("summarize")
public class ArticleSummarizingController {

    private final TechArticleSummarizingUseCase summarizingUseCase;

    @GetMapping
    public void summarize() {
        summarizingUseCase.summarizeTechArticle();
    }

}
