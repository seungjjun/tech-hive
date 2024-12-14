package com.techhive.model;

import java.util.Arrays;
import java.util.Optional;
import lombok.Getter;

@Getter
public enum CategoryType {
    BACKEND(1L, "Backend"),
    FRONTEND(2L,"Frontend"),
    DEV_OPS(3L, "DevOps"),
    CLOUD(4L, "Cloud"),
    DATA(5L, "Data"),
    AI(6L, "AI"),
    NOT_FOUND(99L, "Not found")
    ;

    private final Long id;
    private final String typeName;

    CategoryType(Long id, String typeName) {
        this.id = id;
        this.typeName = typeName;
    }

    public static Optional<CategoryType> fromTypeName(String typeName) {
        return Arrays.stream(CategoryType.values())
            .filter(type -> type.typeName.equalsIgnoreCase(typeName))
            .findFirst();
    }
}
