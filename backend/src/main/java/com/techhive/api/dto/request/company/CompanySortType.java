package com.techhive.api.dto.request.company;

import lombok.Getter;

@Getter
public enum CompanySortType {
    RECENT("RECENT", "최근 등록 순"),
    COUNT("COUNT", "게시물 개수 순")
    ;

    private final String sort;
    private final String description;

    CompanySortType(String sort, String description) {
        this.sort = sort;
        this.description = description;
    }
}
