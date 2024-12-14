package com.techhive.api.dto.response.company;

import com.techhive.model.CompanyType;

public record CompanyDetailResponse(
    Long id,
    String name,
    int articleNumber
) {
    public static CompanyDetailResponse from(CompanyDetailResult result) {
        return new CompanyDetailResponse(
            result.companyEntity().getId(),
            CompanyType.fromName(result.companyEntity().getName()).orElse(CompanyType.UNKNOWN).getDisplayName(),
            result.articleNumber()
        );
    }
}
