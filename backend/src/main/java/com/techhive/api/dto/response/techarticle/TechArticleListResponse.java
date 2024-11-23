package com.techhive.api.dto.response.techarticle;

import com.techhive.entity.TechArticleEntity;
import java.util.List;

public record TechArticleListResponse(List<TechArticleResponse> techArticleResponseList) {

    public static TechArticleListResponse from(List<TechArticleEntity> techArticleEntityList) {
        return new TechArticleListResponse(
            techArticleEntityList.stream()
                .map(TechArticleResponse::from)
                .toList()
        );
    }
}
