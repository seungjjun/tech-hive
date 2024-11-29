package com.techhive.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tech_articles")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TechArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @OneToOne
    @JoinColumn(name = "og_meta_tag_id")
    private OgMetaTagEntity ogMetaTag;

    private String title;

    private String link;

    @Column(columnDefinition = "TEXT")
    private String oneLineSummary;

    @Column(columnDefinition = "TEXT")
    private String threeLineSummary;

    @Column(columnDefinition = "TEXT")
    private String coreSummary;

    @Column(columnDefinition = "TEXT")
    private String originArticle;

    @Column(length = 500)
    private String thumbnailImageUrl;

    private int viewCount;

    private LocalDateTime publishedDate;

    public TechArticleEntity(CompanyEntity company,
                             CategoryEntity category,
                             OgMetaTagEntity ogMetaTag,
                             String title,
                             String link,
                             String oneLineSummary,
                             String threeLineSummary,
                             String coreSummary, String originArticle,
                             String thumbnailImageUrl,
                             LocalDateTime publishedDate) {
        this.company = company;
        this.category = category;
        this.ogMetaTag = ogMetaTag;
        this.title = title;
        this.link = link;
        this.oneLineSummary = oneLineSummary;
        this.threeLineSummary = threeLineSummary;
        this.coreSummary = coreSummary;
        this.originArticle = originArticle;
        this.thumbnailImageUrl = thumbnailImageUrl;
        this.viewCount = 0;
        this.publishedDate = publishedDate;
    }
}
