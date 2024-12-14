package com.techhive.api.controller;

import com.techhive.model.CompanyType;
import com.techhive.service.crawler.ArticleCrawler;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("crawler")
@RequiredArgsConstructor
public class CrawlerController {

    private final ArticleCrawler crawler;

    @PostMapping
    public void crawlerTechArticle(
        @RequestParam(value = "url") String url,
        @RequestParam(value = "company") CompanyType companyType
    ) throws IOException {
        crawler.crawling(url, companyType);
    }
}
