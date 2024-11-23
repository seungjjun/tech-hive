package com.techhive.api.dto.response.company;

import com.techhive.entity.CompanyEntity;

public record CompanyDetailResult(
    CompanyEntity companyEntity,
    int articleNumber
) {
    public static CompanyDetailResult of(CompanyEntity companyEntity, int articleNumber) {
        return new CompanyDetailResult(companyEntity, articleNumber);
    }
}
