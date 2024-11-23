package com.techhive.model.openai;

import lombok.Getter;

@Getter
public enum GptModel {
    GPT_4o_MINI("gpt-4o-mini"),
    GPT_4o("gpt-4o");

    private final String modelName;

    GptModel(String modelName) {
        this.modelName = modelName;
    }
}
