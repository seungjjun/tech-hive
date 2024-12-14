package com.techhive.service;

import com.google.gson.Gson;
import com.techhive.api.dto.WebCrawlingResult;
import com.techhive.api.dto.request.techarticle.TechArticleSortType;
import com.techhive.entity.CategoryEntity;
import com.techhive.entity.CompanyEntity;
import com.techhive.entity.OriginTechArticleEntity;
import com.techhive.entity.OriginTechArticleStatus;
import com.techhive.entity.TechArticleEntity;
import com.techhive.model.CategoryType;
import com.techhive.model.CompanyType;
import com.techhive.model.openai.CoreSummaryItem;
import com.techhive.model.openai.TechArticleSummaryBody;
import com.techhive.repository.CategoryRepository;
import com.techhive.repository.CompanyRepository;
import com.techhive.repository.OgMetaTagRepository;
import com.techhive.repository.OriginTechArticleRepository;
import com.techhive.repository.TechArticleRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TechArticleService {

    private static final String SORT_PUBLISHED_DATE = "publishedDate";
    private static final String SORT_VIEW_COUNT = "viewCount";

    private static final int RECOMMEND_TECH_ARTICLE_LIMIT = 4;

    private final Gson gson = new Gson();
    private final TechArticleRepository techArticleRepository;
    private final OriginTechArticleRepository originTechArticleRepository;
    private final CompanyRepository companyRepository;
    private final CategoryRepository categoryRepository;
    private final OgMetaTagRepository ogMetaTagRepository;

    public List<TechArticleEntity> getAllTechArticles(int page, int limit, TechArticleSortType sortType) {
        Sort sort = switch (sortType) {
            case RECENT -> Sort.by(SORT_PUBLISHED_DATE).descending();
            case POPULAR -> Sort.by(SORT_VIEW_COUNT).descending();
        };
        Pageable pageable = PageRequest.of(page - 1, limit, sort);
        return techArticleRepository.findAll(pageable).getContent();
    }

    @Transactional
    public TechArticleEntity getTechArticle(Long articleId) {
        techArticleRepository.incrementViewCount(articleId);
        return techArticleRepository.findById(articleId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 글입니다. - " + articleId));
    }

    public List<TechArticleEntity> getRelatedTechArticles(Long articleId, Long companyId) {
        return techArticleRepository.findRandomTechArticlesByCompanyId(3, articleId, companyId);
    }

    public List<TechArticleEntity> getTechArticlesByCompany(Long companyId, TechArticleSortType sortType, int page, int limit) {
        Sort sort = switch (sortType) {
            case RECENT -> Sort.by(SORT_PUBLISHED_DATE).descending();
            case POPULAR -> Sort.by(SORT_VIEW_COUNT).descending();
        };
        Pageable pageable = PageRequest.of(page - 1, limit, sort);
        return techArticleRepository.findAllByCompanyId(companyId, pageable).getContent();
    }

    public List<TechArticleEntity> getPopularTechArticles() {
        return techArticleRepository.findTop5ByOrderByViewCountDesc();
    }

    public List<TechArticleEntity> getRecommendTechArticles() {
        return techArticleRepository.findRandomTechArticles(RECOMMEND_TECH_ARTICLE_LIMIT);
    }

    public List<OriginTechArticleEntity> getUnSummarizedTechArticle() {
        return originTechArticleRepository.findByStatus(OriginTechArticleStatus.UN_SUMMARIZED);
    }

    @Transactional
    public TechArticleEntity saveSummarizedTechArticle(OriginTechArticleEntity originTechArticle, CompanyType companyType, WebCrawlingResult result, TechArticleSummaryBody body) {
        TechArticleEntity techArticleEntity = saveTechArticle(companyType, result, body);
        originTechArticle.markAsSummarized();
        return techArticleEntity;
    }

    public TechArticleEntity saveTechArticle(CompanyType companyType, WebCrawlingResult result, TechArticleSummaryBody body) {
        CompanyEntity company = companyRepository.findById(companyType.getId()).orElseThrow(() -> new NoSuchElementException("존재하지 않는 기업입니다."));

        CategoryType parentCategoryType = CategoryType.fromTypeName(body.categories().parentCategory()).orElse(CategoryType.NOT_FOUND);
        CategoryEntity parentCategory = categoryRepository.findById(parentCategoryType.getId())
            .orElseThrow(() -> new NoSuchElementException("존재하지 않는 카테고리 입니다."));

        List<CategoryEntity> newChildCategories = new ArrayList<>();
        List<String> childCategories = body.categories().childCategories();
        for (String category : childCategories) {
            CategoryEntity childCategory = categoryRepository.findByNameIgnoreCase(category)
                .orElseGet(() -> new CategoryEntity(parentCategory, category));
            categoryRepository.save(childCategory);
            newChildCategories.add(childCategory);
        }

        LocalDateTime dateTime = result.dateTime();
        String threeLineSummary = gson.toJson(body.threeLineSummary());
        String coreLineSummary = gson.toJson(body.coreSummaryItems());

        ogMetaTagRepository.save(result.ogMetaTag());
        TechArticleEntity techArticleEntity = new TechArticleEntity(
            company,
            result.ogMetaTag(),
            result.title(), result.url(),
            body.oneLineSummary(),
            threeLineSummary,
            coreLineSummary,
            result.content(),
            result.thumbnailImageUrl(),
            dateTime);

        techArticleEntity.addCategory(parentCategory);
        for (CategoryEntity childCategory : newChildCategories) {
            techArticleEntity.addCategory(childCategory);
        }

        techArticleRepository.save(techArticleEntity);
        return techArticleEntity;
    }
}
