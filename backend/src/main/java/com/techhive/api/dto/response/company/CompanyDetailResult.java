package com.techhive.api.dto.response.company;

import com.techhive.entity.Company;

public record CompanyDetailResult(
    Company company,
    int articleNumber
) {
    public static CompanyDetailResult of(Company company, int articleNumber) {
        return new CompanyDetailResult(company, articleNumber);
    }
}
