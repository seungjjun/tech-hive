package com.techhive.api.dto.response.techarticle;

import com.techhive.entity.TechArticleEntity;
import com.techhive.model.CompanyType;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.util.StringUtils;

public record TechArticleResponse(
    Long id,
    Long companyId,
    String companyName,
    List<String> categoryNames,
    String title,
    String link,
    String oneLineSummary,
    String threeLineSummary,
    String coreSummary,
    String imageUrl,
    int viewCount,
    String ogTitle,
    String ogImageUrl,
    String ogWebUrl,
    LocalDateTime publishedDate
) {
    public static TechArticleResponse from(TechArticleEntity techArticleEntity) {
        return new TechArticleResponse(
            techArticleEntity.getId(),
            techArticleEntity.getCompany().getId(),
            CompanyType.fromName(techArticleEntity.getCompany().getName()).orElse(CompanyType.UNKNOWN).getDisplayName(),
            techArticleEntity.getTechArticleCategories().stream()
                .map(c -> c.getCategory().getName())
                .toList(),
            techArticleEntity.getTitle(),
            techArticleEntity.getLink(),
            techArticleEntity.getOneLineSummary(),
            techArticleEntity.getThreeLineSummary(),
            techArticleEntity.getCoreSummary(),
            techArticleEntity.getThumbnailImageUrl(),
            techArticleEntity.getViewCount(),
            techArticleEntity.getOgMetaTag().getTitle(),
            techArticleEntity.getOgMetaTag().getImageUrl(),
            techArticleEntity.getOgMetaTag().getWebUrl(),
            techArticleEntity.getPublishedDate()
        );
    }
}
