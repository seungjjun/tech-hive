package com.techhive.model.jdbc;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class CompanyTechArticle {

    private Long companyId;
    private String companyName;
    private Long techArticleCount;
    private LocalDateTime latestPublishedDate;
    private boolean isUpdated;

    public CompanyTechArticle(Long companyId, String companyName, Long techArticleCount, LocalDateTime latestPublishedDate,
                              boolean isUpdated) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.techArticleCount = techArticleCount;
        this.latestPublishedDate = latestPublishedDate;
        this.isUpdated = isUpdated;
    }
}
