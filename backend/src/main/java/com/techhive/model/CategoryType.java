package com.techhive.model;

import lombok.Getter;

@Getter
public enum CategoryType {
    BACKEND("Backend"),
    FRONTEND("Frontend"),
    DEV_OPS("DevOps"),
    AI_MACHINE_LEARNING("AI & Machine Learning"),
    ;

    private final String typeName;

    CategoryType(String typeName) {
        this.typeName = typeName;
    }
}
