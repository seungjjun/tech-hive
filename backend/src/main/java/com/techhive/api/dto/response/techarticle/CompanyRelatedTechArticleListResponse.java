package com.techhive.api.dto.response.techarticle;

import com.techhive.entity.TechArticleEntity;
import java.util.List;

public record CompanyRelatedTechArticleListResponse(List<CompanyRelatedTechArticleResponse> relatedTechArticles) {
    public static CompanyRelatedTechArticleListResponse from(List<TechArticleEntity> relatedTechArticles) {
        return new CompanyRelatedTechArticleListResponse(relatedTechArticles.stream()
            .map(CompanyRelatedTechArticleResponse::from)
            .toList());
    }
}
