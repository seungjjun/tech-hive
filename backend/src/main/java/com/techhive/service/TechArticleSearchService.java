package com.techhive.service;

import com.techhive.entity.TechArticleEntity;
import com.techhive.entity.document.TechArticleDocument;
import com.techhive.repository.TechArticleElasticsearchRepository;
import com.techhive.repository.TechArticleRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TechArticleSearchService {

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

    public List<TechArticleEntity> getSearchTechArticles(String searchTerm) {
        List<TechArticleDocument> techArticleDocuments = techArticleSearchRepository.findByOriginArticleContaining(searchTerm);
        List<Long> techArticleIds = techArticleDocuments.stream().map(TechArticleDocument::getId).toList();
        return techArticleRepository.findAllById(techArticleIds);
    }
}
