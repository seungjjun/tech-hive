package com.techhive.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    @OneToMany(mappedBy = "techArticle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TechArticleCategoryEntity> techArticleCategories = new ArrayList<>();

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
                             OgMetaTagEntity ogMetaTag,
                             String title,
                             String link,
                             String oneLineSummary,
                             String threeLineSummary,
                             String coreSummary, String originArticle,
                             String thumbnailImageUrl,
                             LocalDateTime publishedDate) {
        this.company = company;
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

    public void addCategory(CategoryEntity category) {
        techArticleCategories.add(new TechArticleCategoryEntity(this, category));
    }
}
