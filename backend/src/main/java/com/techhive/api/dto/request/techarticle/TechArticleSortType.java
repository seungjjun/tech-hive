package com.techhive.api.dto.request.techarticle;

import lombok.Getter;

@Getter
public enum TechArticleSortType {
    LATEST("latest", "최신순"),
    VIEWER("viewer", "인기순")
    ;

    private final String sort;
    private final String description;

    TechArticleSortType(String sort, String description) {
        this.sort = sort;
        this.description = description;
    }
}
