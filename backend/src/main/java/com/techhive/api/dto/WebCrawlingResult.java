package com.techhive.api.dto;

import com.techhive.entity.OgMetaTagEntity;
import java.time.LocalDateTime;

public record WebCrawlingResult(
    String title,
    LocalDateTime dateTime,
    OgMetaTagEntity ogMetaTag,
    String category,
    String thumbnailImageUrl,
    String content,
    String url
) {
}
