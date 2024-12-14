package com.techhive.api.dto.response.company;

import com.techhive.model.CompanyType;
import com.techhive.model.jdbc.CompanyTechArticle;
import java.time.LocalDateTime;

public record CompanyResponse(Long id, String name, Long techArticleCount, LocalDateTime latestPublishedDate, boolean isUpdated) {
    public static CompanyResponse from(CompanyTechArticle companyTechArticle) {
        return new CompanyResponse(
            companyTechArticle.getCompanyId(),
            CompanyType.fromName(companyTechArticle.getCompanyName()).orElse(CompanyType.UNKNOWN).getDisplayName(),
            companyTechArticle.getTechArticleCount(),
            companyTechArticle.getLatestPublishedDate(),
            companyTechArticle.isUpdated());
    }
}
