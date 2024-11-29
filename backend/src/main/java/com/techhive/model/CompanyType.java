package com.techhive.model;

import lombok.Getter;

@Getter
public enum CompanyType {

    NAVER(1L, "Naver"),
    LINE(2L, "Line"),
    WOOWAHAN(3L, "Woowahan"),
    TOSS(4L, "Toss"),
    KAKAO_BANK(5L, "Kakaobank"),
    KAKAO_PAY(6L, "Kakaopay"),
    YOGIYO(7L, "Yogiyo"),
    DAANGN(12L, "Daangn")
    ;

    private final Long id;
    private final String displayName;

    CompanyType(Long id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }
}
