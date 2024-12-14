package com.techhive.api.dto.response.techarticle;

import com.techhive.entity.TechArticleEntity;
import java.util.List;

public record TechArticleSearchResults(
    List<TechArticleEntity> techArticleEntities,
    long totalCount,
    int totalPages
) {

    public static TechArticleSearchResults from(List<TechArticleEntity> techArticles,
                                               long totalElements,
                                               int totalPages) {
        return new TechArticleSearchResults(techArticles, totalElements, totalPages);
    }
}
