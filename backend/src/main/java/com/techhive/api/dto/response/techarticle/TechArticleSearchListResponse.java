package com.techhive.api.dto.response.techarticle;

import java.util.List;

public record TechArticleSearchListResponse(
    List<TechArticleResponse> techArticleResponseList,
    long totalCount,
    int totalPages
) {
    public static TechArticleSearchListResponse from(TechArticleSearchResults searchResults) {
        return new TechArticleSearchListResponse(
            searchResults.techArticleEntities().stream().map(TechArticleResponse::from).toList(),
            searchResults.totalCount(),
            searchResults.totalPages()
        );
    }
}
