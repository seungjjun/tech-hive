package com.techhive.service;

import com.techhive.api.dto.response.techarticle.TechArticleSearchResults;
import com.techhive.entity.TechArticleEntity;
import com.techhive.entity.document.TechArticleDocument;
import com.techhive.repository.TechArticleElasticsearchRepository;
import com.techhive.repository.TechArticleRepository;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TechArticleSearchService {

    private static final String ORDER_BY_PUBLISHED_DATE = "publishedDate";

    private final TechArticleRepository techArticleRepository;
    private final TechArticleElasticsearchRepository techArticleSearchRepository;

    public void saveTechArticle(TechArticleEntity techArticleEntity) {
        TechArticleDocument techArticleDocument = new TechArticleDocument(
            techArticleEntity.getId(),
            techArticleEntity.getTitle(),
            techArticleEntity.getOriginArticle()
        );
        techArticleSearchRepository.save(techArticleDocument);
    }

    public TechArticleSearchResults getSearchTechArticles(String searchTerm, int page, int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit);
        Page<TechArticleDocument> techArticleDocuments;
        if (searchTerm != null && !searchTerm.isEmpty()) {
            techArticleDocuments = techArticleSearchRepository.findByOriginArticleContaining(searchTerm, pageable);
        } else {
            techArticleDocuments = techArticleSearchRepository.findAll(pageable);
        }

        List<Long> techArticleIds = techArticleDocuments.stream()
            .map(TechArticleDocument::getId)
            .toList();

        Sort sort = Sort.by(Sort.Direction.DESC, ORDER_BY_PUBLISHED_DATE);
        List<TechArticleEntity> techArticles = techArticleRepository.findByIdIn(techArticleIds, sort)
            .stream()
            .toList();
        return TechArticleSearchResults.from(techArticles, techArticleDocuments.getTotalElements(), techArticleDocuments.getTotalPages());
    }
}
