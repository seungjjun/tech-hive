package com.techhive.repository;

import com.techhive.entity.document.TechArticleDocument;
import java.util.List;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechArticleElasticsearchRepository extends ElasticsearchRepository<TechArticleDocument, Long> {
    List<TechArticleDocument> findByOriginArticleContaining(String originArticle);
}