package com.techhive.api.dto.response.company;

public record CompanyDetailResponse(
    Long id,
    String name,
    int articleNumber
) {
    public static CompanyDetailResponse from(CompanyDetailResult result) {
        return new CompanyDetailResponse(
            result.company().getId(),
            result.company().getName(),
            result.articleNumber()
        );
    }
}
