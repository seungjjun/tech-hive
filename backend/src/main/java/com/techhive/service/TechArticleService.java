package com.techhive.service;

import com.google.gson.Gson;
import com.techhive.api.dto.WebCrawlingResult;
import com.techhive.api.dto.request.techarticle.TechArticleSortType;
import com.techhive.entity.CategoryEntity;
import com.techhive.entity.CompanyEntity;
import com.techhive.entity.TechArticleEntity;
import com.techhive.model.CategoryType;
import com.techhive.model.CompanyType;
import com.techhive.model.openai.TechArticleSummaryBody;
import com.techhive.repository.CategoryRepository;
import com.techhive.repository.CompanyRepository;
import com.techhive.repository.OgMetaTagRepository;
import com.techhive.repository.TechArticleRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TechArticleService {

    private static final int RECOMMEND_TECH_ARTICLE_LIMIT = 4;
    private static final String SORT_PUBLISHED_DATE = "publishedDate";
    private static final String SORT_VIEW_COUNT = "viewCount";

    private final Gson gson = new Gson();
    private final TechArticleRepository techArticleRepository;
    private final CompanyRepository companyRepository;
    private final CategoryRepository categoryRepository;
    private final OgMetaTagRepository ogMetaTagRepository;

    public List<TechArticleEntity> getAllTechArticles() {
        return techArticleRepository.findAll(Sort.by(SORT_PUBLISHED_DATE).descending());
    }

    public TechArticleEntity getTechArticle(Long articleId) {
        return techArticleRepository.findById(articleId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 글입니다. - " + articleId));
    }

    public List<TechArticleEntity> getTechArticlesByCompany(Long companyId, TechArticleSortType sortType) {
        Sort sort = switch (sortType) {
            case LATEST -> Sort.by(SORT_PUBLISHED_DATE).descending();
            case VIEWER -> Sort.by(SORT_VIEW_COUNT).descending();
        };
        return techArticleRepository.findAllByCompanyId(companyId, sort);
    }

    public List<TechArticleEntity> getPopularTechArticles() {
        return techArticleRepository.findTop3ByOrderByViewCountDesc();
    }

    public List<TechArticleEntity> getRecommendTechArticles() {
        return techArticleRepository.findRandomTechArticles(RECOMMEND_TECH_ARTICLE_LIMIT);
    }

    @Transactional
    public void saveTechArticle(CompanyType companyType, WebCrawlingResult result, TechArticleSummaryBody body) {
        CompanyEntity company = companyRepository.findById(companyType.getId()).get();
        // TODO 원글 카테고리에 따른 수정 필요 -> result.category()
        CategoryEntity category = categoryRepository.findByNameIgnoreCase(CategoryType.BACKEND.getTypeName());

        LocalDateTime dateTime = result.dateTime();
        String threeLineSummary = gson.toJson(body.threeLineSummary());
        String coreLineSummary = gson.toJson(body.coreSummary());

        ogMetaTagRepository.save(result.ogMetaTag());
        TechArticleEntity techArticleEntity = new TechArticleEntity(
            company, category,
            result.ogMetaTag(),
            result.title(), result.url(),
            body.oneLineSummary(),
            threeLineSummary,
            coreLineSummary,
            result.content(),
            result.thumbnailImageUrl(),
            dateTime);
        techArticleRepository.save(techArticleEntity);
    }

    public List<TechArticleEntity> getSearchTechArticles(String searchTerm) {
        return techArticleRepository.searchByFullText(searchTerm);
    }
}
