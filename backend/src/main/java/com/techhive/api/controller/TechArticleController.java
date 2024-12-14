package com.techhive.api.controller;

import com.techhive.api.dto.request.techarticle.TechArticleSortType;
import com.techhive.api.dto.response.techarticle.CompanyRelatedTechArticleListResponse;
import com.techhive.api.dto.response.techarticle.TechArticleListResponse;
import com.techhive.api.dto.response.techarticle.TechArticleResponse;
import com.techhive.api.dto.response.techarticle.TechArticleSearchListResponse;
import com.techhive.api.dto.response.techarticle.TechArticleSearchResults;
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

    private static final String DEFAULT_ARTICLES_SIZE = "6";
    private static final String DEFAULT_SEARCH_ARTICLES_LIMIT = "4";
    private static final String DEFAULT_PAGE = "1";

    private final TechArticleService techArticleService;
    private final TechArticleSearchService searchService;

    @GetMapping
    public TechArticleListResponse getTechArticles(
        @RequestParam(value = "sort", defaultValue = "RECENT", required = false) TechArticleSortType sort,
        @RequestParam(defaultValue = DEFAULT_PAGE, name = "page") int page,
        @RequestParam(value = "size", defaultValue = DEFAULT_ARTICLES_SIZE, required = false) String size) {
        List<TechArticleEntity> allTechArticleEntities = techArticleService.getAllTechArticles(page, Integer.parseInt(size), sort);
        return TechArticleListResponse.from(allTechArticleEntities);
    }

    @GetMapping("/{articleId}")
    public TechArticleResponse getTechArticle(@PathVariable Long articleId) {
        TechArticleEntity techArticleEntity = techArticleService.getTechArticle(articleId);
        return TechArticleResponse.from(techArticleEntity);
    }

    @GetMapping("{articleId}/companies/{companyId}/related")
    public CompanyRelatedTechArticleListResponse getRelatedTechArticleList(
        @PathVariable Long articleId,
        @PathVariable Long companyId) {
        List<TechArticleEntity> relatedTechArticles = techArticleService.getRelatedTechArticles(articleId, companyId);
        return CompanyRelatedTechArticleListResponse.from(relatedTechArticles);
    }

    @GetMapping("/companies/{companyId}")
    public TechArticleListResponse getTechArticlesOfSpecificCompany(
        @PathVariable Long companyId,
        @RequestParam(value = "sort", defaultValue = "RECENT") TechArticleSortType sortType,
        @RequestParam(defaultValue = DEFAULT_PAGE, name = "page") int page,
        @RequestParam(value = "size", defaultValue = "10", required = false) String size) {
        List<TechArticleEntity> techArticlesByCompanyEntity = techArticleService.getTechArticlesByCompany(companyId, sortType, page, Integer.parseInt(size));
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
    public TechArticleSearchListResponse getSearchTechArticles(
        @RequestParam(name = "term", required = false) String searchTerm,
        @RequestParam(defaultValue = DEFAULT_PAGE, name = "page") int page,
        @RequestParam(defaultValue = DEFAULT_SEARCH_ARTICLES_LIMIT, name = "limit") int limit
    ) {
        TechArticleSearchResults searchResults = searchService.getSearchTechArticles(searchTerm, page, limit);
        return TechArticleSearchListResponse.from(searchResults);
    }
}
