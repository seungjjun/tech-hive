package com.techhive.api.controller;

import com.techhive.api.dto.WebCrawlingResult;
import com.techhive.model.CompanyType;
import com.techhive.model.openai.TechArticleSummaryBody;
import com.techhive.service.TechArticleSummarizingService;
import com.techhive.service.TechArticleService;
import com.techhive.service.crawler.TossTechArticleCrawler;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("crawler")
@RequiredArgsConstructor
public class CrawlerController {

    private final TechArticleSummarizingService techArticleSummarizingService;
    private final TechArticleService techArticleService;

    private final TossTechArticleCrawler tossTechArticleCrawler;

    @PostMapping
    public void crawlerTechArticle(
        @RequestParam(value = "url") String url,
        @RequestParam(value = "company") CompanyType companyType) throws IOException {
        WebCrawlingResult result = tossTechArticleCrawler.crawling(url);
        TechArticleSummaryBody body  = techArticleSummarizingService.generateTechArticleSummary(result.content());
        techArticleService.saveTechArticle(companyType, result, body);
    }
}
