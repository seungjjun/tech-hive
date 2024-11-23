package com.techhive.api.dto;

import java.time.LocalDateTime;

public record WebCrawlingResult(
    String title,
    LocalDateTime dateTime,
    String category,
    String thumbnailImageUrl,
    String content,
    String url
) {
}
