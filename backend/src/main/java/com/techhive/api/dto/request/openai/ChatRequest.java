package com.techhive.api.dto.request.openai;

import java.util.List;
import lombok.Builder;

@Builder
public record ChatRequest(
    String model,
    List<Message> messages,
    int n,
    double temperature
) {

    public record Message(String role, String content) {
    }
}
