package com.techhive.model;

import java.util.Arrays;
import java.util.Optional;
import lombok.Getter;

@Getter
public enum CompanyType {

    TOSS(2L, "Toss"),
    KAKAO(3L, "Kakao"),
    DAANGN(5L, "Daangn"),
    WOOWAHAN(6L, "우아한형제들"),
    YOGIYO(7L, "요기요"),
    KURLY(8L, "Kurly"),
    YEOGI(9L, "여기어때"),
    MUSINSA(10L, "무신사"),
    GANGNAM_UNNI(11L, "강남언니"),
    UNKNOWN(99L, "Unknown")
    ;

    private final Long id;
    private final String displayName;

    CompanyType(Long id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public static Optional<CompanyType> fromName(String name) {
        return Arrays.stream(CompanyType.values())
            .filter(type -> type.name().equalsIgnoreCase(name))
            .findFirst();
    }

}
