package com.techhive.api.dto.response.company;

import com.techhive.entity.Company;

public record CompanyResponse(Long id, String name) {
    public static CompanyResponse from(Company company) {
        return new CompanyResponse(company.getId(), company.getName());
    }
}
