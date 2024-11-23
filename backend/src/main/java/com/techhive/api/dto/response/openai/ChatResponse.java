package com.techhive.api.dto.response.openai;

import java.util.List;

public record ChatResponse(List<Choice> choices) {

    public record Choice(int index, Message message) {
    }

    public record Message(String role, String content) {
    }
}
