package com.techhive.model;

import lombok.Getter;

@Getter
public enum CompanyType {

    NAVER(1L, "naver"),
    LINE(2L, "line"),
    WOOWAHAN(3L, "woowahan"),
    TOSS(4L, "toss"),
    KAKAO_BANK(5L, "kakaobank"),
    KAKAO_PAY(6L, "kakaopay"),
    YOGIYO(7L, "yogiyo")
    ;

    private final Long id;
    private final String name;

    CompanyType(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
