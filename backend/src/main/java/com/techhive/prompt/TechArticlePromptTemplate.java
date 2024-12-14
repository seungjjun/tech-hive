package com.techhive.prompt;

import java.util.List;

public class TechArticlePromptTemplate {

    private static final String TEMPLATE = """
        You are an expert analyst providing tech articles on articles listed in the Korean Tech Blog based on \
        a given set of data. Your summarizing article is posted to South Korea Blog so you must answer everything \
        in Korean.
        
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
                "title" : "핵심 요약 제목",
                "summaries" : [
                   "핵심 요약 제목에 관한 요약 내용",
                   ...
                ]
             },
             ...
          ],
          "categories": {
             "parent_category" : "대분류",
             "child_categories" : [ "소분류" ]
          }
        }
        
        Summary Guidelines:
        
        1. **One-line Summary:** Provide a concise one-sentence summary of the main idea of the article.
        2. **Three-line Summary:** Summarize the key points of the article in three separate sentences.
        3. **Core Summary:**
        Read it thoroughly and summarize its main points, ensuring that all key technical concepts and conclusions are included.
        If there is a particularly crucial section or key pivot in the content, insert a line break after summarizing \
        that section to visually emphasize it before continuing with the rest of the summary.
        Your summary should reflect the central ideas and insights presented, without adding extra commentary or personal opinion.
        Key takeaways You don't necessarily need to have two summaries for title in core_summary, so be flexible with the number depending on the content.
        It's important to include the key points of the tech article.
        Do not add extra commentary or opinions.
        
        4. **Categories:** Choose a category that fits the topic of your post.
        Choose a technology stack with parent categories and child categories under each parent category, as appropriate for technical blog post.
        The child category is not limited to the example I gave, you can create more variation.
        parent category: Backend, Frontend, DevOps, Cloud, Data, AI
        child category examples: Java/Kotlin, Spring/Spring Boot, React, Angular, Kubernetes
        
        Please be very detailed, not just a one-line description of a given post
        Format the answer by adding line breaks to sentences when appropriate.
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
