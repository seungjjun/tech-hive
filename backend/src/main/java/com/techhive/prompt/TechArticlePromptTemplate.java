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
        3. **Core Summary:** Provide a detailed list of the core points of the article. Each key point should include a title and a brief description.
        
        --- Examples ---
        {
          "one_line_summary": "이 글은 새로운 웹 개발 프레임워크에 대해 소개합니다.",
          "three_line_summary": [
              "새로운 프레임워크의 주요 기능을 설명합니다.",
              "기존 프레임워크와의 차별점을 강조합니다.",
              "실제 사용 사례를 통해 장점을 보여줍니다."
          ],
          "core_summary": [
            {
              "title": "주요 개념",
              "description": "프레임워크의 기본 구조와 작동 방식을 설명합니다."
            },
            {
              "title": "중요한 기능",
              "description": "자동화 도구, 템플릿 엔진 등의 주요 기능을 소개합니다."
            },
            {
              "title": "적용 사례",
              "description": "실제 프로젝트에서의 활용 사례를 제공합니다."
            }
          ]
        }
        --- Examples End ---
        
        <<DATA>>
        {{DATA}}
        <<ANSWER>>
        """;

    public String generatePrompt(String data) {
//        StringBuilder exampleBuilder = new StringBuilder();
//        for (Example example : examples) {
//            exampleBuilder.append("<<DATA>>\n").append(example.data()).append("\n");
//            exampleBuilder.append("<<ANSWER>>\n").append(example.answer()).append("\n");
//        }
        return TEMPLATE
//            .replace("{{EXAMPLES}}", exampleBuilder.toString())
            .replace("{{DATA}}", data);
    }

    public record Example(int number, String data, String answer) {}
}
