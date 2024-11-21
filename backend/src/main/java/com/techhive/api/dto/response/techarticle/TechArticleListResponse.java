package com.techhive.api.dto.response.techarticle;

import com.techhive.entity.TechArticle;
import java.util.List;

public record TechArticleListResponse(List<TechArticleResponse> techArticleResponseList) {

    public static TechArticleListResponse from(List<TechArticle> techArticleList) {
        return new TechArticleListResponse(
            techArticleList.stream()
                .map(TechArticleResponse::from)
                .toList()
        );
    }
}
