package com.techhive.api.controller;

import com.techhive.api.dto.request.techarticle.TechArticleSortType;
import com.techhive.api.dto.response.techarticle.TechArticleListResponse;
import com.techhive.api.dto.response.techarticle.TechArticleResponse;
import com.techhive.api.dto.response.techarticle.TechArticleSearchListResponse;
import com.techhive.entity.TechArticleEntity;
import com.techhive.service.TechArticleSearchService;
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
    private final TechArticleSearchService searchService;

    @GetMapping
    public TechArticleListResponse getTechArticles(@RequestParam(value = "size", defaultValue = "6", required = false) String size) {
        List<TechArticleEntity> allTechArticleEntities = techArticleService.getAllTechArticles(Integer.parseInt(size));
        return TechArticleListResponse.from(allTechArticleEntities);
    }

    @GetMapping("/{articleId}")
    public TechArticleResponse getTechArticle(@PathVariable Long articleId) {
        TechArticleEntity techArticleEntity = techArticleService.getTechArticle(articleId);
        return TechArticleResponse.from(techArticleEntity);
    }

    @GetMapping("/companies/{companyId}")
    public TechArticleListResponse getTechArticlesOfSpecificCompany(
        @PathVariable Long companyId,
        @RequestParam(value = "sort", defaultValue = "LATEST") TechArticleSortType sortType) {
        List<TechArticleEntity>
            techArticlesByCompanyEntity = techArticleService.getTechArticlesByCompany(companyId, sortType);
        return TechArticleListResponse.from(techArticlesByCompanyEntity);
    }

    @GetMapping("/recommend")
    public TechArticleListResponse getRecommendTechArticles() {
        List<TechArticleEntity> techArticles = techArticleService.getRecommendTechArticles();
        return TechArticleListResponse.from(techArticles);
    }

    @GetMapping("/popular")
    public TechArticleListResponse getPopularTechArticles() {
        List<TechArticleEntity> popularTechArticleEntities = techArticleService.getPopularTechArticles();
        return TechArticleListResponse.from(popularTechArticleEntities);
    }

    @GetMapping("/search")
    public TechArticleListResponse getSearchTechArticles(
        @RequestParam(value = "term") String searchTerm
    ) {
        List<TechArticleEntity> searchTechArticles = searchService.getSearchTechArticles(searchTerm);
        return TechArticleListResponse.from(searchTechArticles);
    }
}
