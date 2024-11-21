package com.techhive.api.controller;

import com.techhive.api.dto.request.techarticle.TechArticleSortType;
import com.techhive.api.dto.response.techarticle.TechArticleListResponse;
import com.techhive.api.dto.response.techarticle.TechArticleResponse;
import com.techhive.entity.TechArticle;
import com.techhive.service.TechArticleService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tech-articles")
public class TechArticleController {

    private final TechArticleService techArticleService;

    @GetMapping
    public TechArticleListResponse getTechArticles() {
        List<TechArticle> allTechArticles = techArticleService.getAllTechArticles();
        return TechArticleListResponse.from(allTechArticles);
    }

    @GetMapping("/{articleId}")
    public TechArticleResponse getTechArticle(@PathVariable Long articleId) {
        TechArticle techArticle = techArticleService.getTechArticle(articleId);
        return TechArticleResponse.from(techArticle);
    }

    @GetMapping("/companies/{companyId}")
    public TechArticleListResponse getTechArticlesOfSpecificCompany(
        @PathVariable Long companyId,
        @RequestParam(value = "sort", defaultValue = "LATEST") TechArticleSortType sortType) {
        List<TechArticle> techArticlesByCompany = techArticleService.getTechArticlesByCompany(companyId, sortType);
        return TechArticleListResponse.from(techArticlesByCompany);
    }

    @GetMapping("/popular")
    public TechArticleListResponse getPopularTechArticles() {
        List<TechArticle> popularTechArticles = techArticleService.getPopularTechArticles();
        return TechArticleListResponse.from(popularTechArticles);
    }
}
