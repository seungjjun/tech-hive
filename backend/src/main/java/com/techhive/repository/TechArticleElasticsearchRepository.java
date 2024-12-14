package com.techhive.repository;

import com.techhive.entity.document.TechArticleDocument;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechArticleElasticsearchRepository extends ElasticsearchRepository<TechArticleDocument, Long> {
    Page<TechArticleDocument> findByOriginArticleContaining(String originArticle, Pageable pageable);
}