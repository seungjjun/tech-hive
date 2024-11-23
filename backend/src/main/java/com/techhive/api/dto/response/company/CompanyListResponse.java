package com.techhive.api.dto.response.company;

import com.techhive.entity.CompanyEntity;
import java.util.List;

public record CompanyListResponse(List<CompanyResponse> companies) {

    public static CompanyListResponse from(List<CompanyEntity> companiesList) {
        return new CompanyListResponse(companiesList.stream().map(CompanyResponse::from).toList());
    }
}
