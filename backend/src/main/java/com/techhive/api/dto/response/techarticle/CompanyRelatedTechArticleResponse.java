package com.techhive.api.dto.response.techarticle;

import com.techhive.entity.TechArticleEntity;

public record CompanyRelatedTechArticleResponse(
    Long relatedArticleId,
    String title,
    String thumbnailImageUrl
) {
    public static CompanyRelatedTechArticleResponse from(TechArticleEntity techArticle) {
        return new CompanyRelatedTechArticleResponse(techArticle.getId(), techArticle.getTitle(), techArticle.getThumbnailImageUrl());
    }
}
