package com.techhive.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "origin_tech_article")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OriginTechArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String originArticle;

    private String link;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OriginTechArticleStatus status;

    private LocalDateTime createdAt;

    public OriginTechArticleEntity(String companyName, String originArticle, String link, LocalDateTime createdAt) {
        this.companyName = companyName;
        this.originArticle = originArticle;
        this.link = link;
        this.createdAt = createdAt;
        this.status = OriginTechArticleStatus.UN_SUMMARIZED;
    }

    public void markAsSummarized() {
        this.status = OriginTechArticleStatus.SUMMARIZED;
    }
}
