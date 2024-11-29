package com.techhive.prompt;

import java.util.List;

public class TechArticlePromptTemplate {

    private static final String TEMPLATE = """
        
        You are a Summary Expert. Your task is to summarize the given technical blog post in three different ways
        and provide the response in Korean.
        
        Please generate the response in the following format:
        {
          "one_line_summary": "<한줄 요약>",
          "three_line_summary": [
              "<첫 번째 요약 문장>",
              "<두 번째 요약 문장>",
              "<세 번째 요약 문장>"
          ],
          "core_summary": [
            {
              "title": "<핵심 포인트 제목>",
              "description": "<핵심 포인트 설명>"
            },
            {
              "title": "<핵심 포인트 제목>",
              "description": "<핵심 포인트 설명>"
            },
            ...
          ]
        }
        
        Summary Guidelines:
        
        1. **One-line Summary:** Provide a concise one-sentence summary of the main idea of the article.
        2. **Three-line Summary:** Summarize the key points of the article in three separate sentences.
        3. **Core Summary:** Provide a detailed list of the core points of the article.
        Each key point should include a title and a brief description.
        Please be very detailed, not just a one-line description of a given post
        
        --- Examples ---
        {{EXAMPLES}}
        --- Examples End ---
        <<DATA>>
        {{DATA}}
        <<ANSWER>>
        """;

    public String generatePrompt(List<Example> examples, String data) {
        StringBuilder exampleBuilder = new StringBuilder();
        for (Example example : examples) {
            exampleBuilder.append("<<DATA>>\n").append(example.data()).append("\n");
            exampleBuilder.append("<<ANSWER>>\n").append(example.answer()).append("\n");
        }
        return TEMPLATE
            .replace("{{EXAMPLES}}", exampleBuilder.toString())
            .replace("{{DATA}}", data);
    }

    public record Example(int number, String data, String answer) {}
}
