package com.techhive.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "og_meta_tag")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OgMetaTagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 500)
    private String imageUrl;

    @Column(length = 500)
    private String webUrl;

    public OgMetaTagEntity(String title, String description, String imageUrl, String webUrl) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.webUrl = webUrl;
    }
}
