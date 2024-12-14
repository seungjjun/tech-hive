package com.techhive.api.dto.response.company;

import com.techhive.model.jdbc.CompanyTechArticle;
import java.util.List;

public record CompanyListResponse(List<CompanyResponse> companies) {

    public static CompanyListResponse from(List<CompanyTechArticle> companiesList) {
        return new CompanyListResponse(companiesList.stream().map(CompanyResponse::from).toList());
    }
}
