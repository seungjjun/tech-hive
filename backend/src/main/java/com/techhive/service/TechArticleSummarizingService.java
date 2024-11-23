package com.techhive.service;

import com.google.gson.Gson;
import com.techhive.api.dto.request.openai.ChatRequest;
import com.techhive.api.dto.response.openai.ChatResponse;
import com.techhive.model.openai.GptModel;
import com.techhive.model.openai.TechArticleSummaryBody;
import com.techhive.prompt.TechArticlePromptBuilder;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class TechArticleSummarizingService {

    private static final String USER_ROLE = "user";
    private static final Gson GSON = new Gson().newBuilder().setPrettyPrinting().create();

    @Value("${openai.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public TechArticleSummaryBody generateTechArticleSummary(String data) {
        ChatResponse chatResponse = null;
        List<ChatRequest.Message> ChatMessages = new ArrayList<>();
        ChatRequest.ChatRequestBuilder request = ChatRequest.builder();
        try {

            TechArticlePromptBuilder builder = new TechArticlePromptBuilder(data);
            String prompt = builder.generatePrompt();

            ChatMessages.add(new ChatRequest.Message(USER_ROLE, prompt));

            ChatRequest chatRequest = request
                .model(GptModel.GPT_4o_MINI.getModelName())
                .messages(ChatMessages)
                .n(1)
                .temperature(0.2D)
                .build();

            chatResponse = restTemplate.postForObject(apiUrl, chatRequest, ChatResponse.class);
            String content = chatResponse.choices().get(0).message().content();
            return GSON.fromJson(content, TechArticleSummaryBody.class);
        } catch(Exception e) {
            throw new RuntimeException("Tech article summarization failed." + e.getMessage());
        }
    }
}
