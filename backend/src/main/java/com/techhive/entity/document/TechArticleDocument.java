package com.techhive.entity.document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "tech_articles", createIndex = false)
@Getter
@NoArgsConstructor
public class TechArticleDocument {
    @Id
    private Long id;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String originArticle;

    public TechArticleDocument(Long id, String title, String originArticle) {
        this.id = id;
        this.title = title;
        this.originArticle = originArticle;
    }
}
