package com.techhive.api.dto.response.company;

import com.techhive.entity.Company;
import java.util.List;

public record CompanyListResponse(List<CompanyResponse> companies) {

    public static CompanyListResponse from(List<Company> companiesList) {
        return new CompanyListResponse(companiesList.stream().map(CompanyResponse::from).toList());
    }
}
