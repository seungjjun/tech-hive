package com.techhive.api.dto.response.company;

import com.techhive.entity.CompanyEntity;

public record CompanyResponse(Long id, String name) {
    public static CompanyResponse from(CompanyEntity companyEntity) {
        return new CompanyResponse(companyEntity.getId(), companyEntity.getName());
    }
}
