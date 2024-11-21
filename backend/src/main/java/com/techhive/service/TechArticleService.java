package com.techhive.service;

import com.techhive.api.dto.request.techarticle.TechArticleSortType;
import com.techhive.entity.TechArticle;
import com.techhive.repository.TechArticleRepository;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TechArticleService {

    private static final String SORT_PUBLISHED_DATE = "publishedDate";
    private static final String SORT_VIEW_COUNT = "viewCount";

    private final TechArticleRepository techArticleRepository;

    public List<TechArticle> getAllTechArticles() {
        return techArticleRepository.findAll(Sort.by(SORT_PUBLISHED_DATE).descending());
    }

    public TechArticle getTechArticle(Long articleId) {
        return techArticleRepository.findById(articleId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 글입니다. - " + articleId));
    }

    public List<TechArticle> getTechArticlesByCompany(Long companyId, TechArticleSortType sortType) {
        Sort sort = switch (sortType) {
            case LATEST -> Sort.by(SORT_PUBLISHED_DATE).descending();
            case VIEWER -> Sort.by(SORT_VIEW_COUNT).descending();
        };
        return techArticleRepository.findAllByCompanyId(companyId, sort);
    }

    public List<TechArticle> getPopularTechArticles() {
        return techArticleRepository.findTop5ByOrderByViewCountDesc();
    }
}
