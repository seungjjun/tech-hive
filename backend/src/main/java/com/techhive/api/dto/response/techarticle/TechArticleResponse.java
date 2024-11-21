package com.techhive.api.dto.response.techarticle;

import com.techhive.entity.TechArticle;
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
    public static TechArticleResponse from(TechArticle techArticle) {
        return new TechArticleResponse(
            techArticle.getId(),
            techArticle.getCompany().getName(),
            techArticle.getCategory().getName(),
            techArticle.getTitle(),
            techArticle.getLink(),
            techArticle.getOneLineSummary(),
            techArticle.getThreeLineSummary(),
            techArticle.getCoreSummary(),
            techArticle.getViewCount(),
            techArticle.getPublishedDate()
        );
    }
}
