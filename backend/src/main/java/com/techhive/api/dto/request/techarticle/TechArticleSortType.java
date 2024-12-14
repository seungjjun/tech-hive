package com.techhive.api.dto.request.techarticle;

import lombok.Getter;

@Getter
public enum TechArticleSortType {
    RECENT("recent", "최신순"),
    POPULAR("popular", "인기순")
    ;

    private final String sort;
    private final String description;

    TechArticleSortType(String sort, String description) {
        this.sort = sort;
        this.description = description;
    }
}
