package com.techhive.api.dto.response.techarticle;

import com.techhive.entity.TechArticleEntity;
import java.time.LocalDateTime;

public record TechArticleResponse(
    Long id,
    String companyName,
    String categoryName,
    String title,
    String link,
    String oneLineSummary,
    String threeLineSummary,
    String coreSummary,
    int viewCount,
    LocalDateTime publishedDate
) {
    public static TechArticleResponse from(TechArticleEntity techArticleEntity) {
        return new TechArticleResponse(
            techArticleEntity.getId(),
            techArticleEntity.getCompany().getName(),
            techArticleEntity.getCategory().getName(),
            techArticleEntity.getTitle(),
            techArticleEntity.getLink(),
            techArticleEntity.getOneLineSummary(),
            techArticleEntity.getThreeLineSummary(),
            techArticleEntity.getCoreSummary(),
            techArticleEntity.getViewCount(),
            techArticleEntity.getPublishedDate()
        );
    }
}
