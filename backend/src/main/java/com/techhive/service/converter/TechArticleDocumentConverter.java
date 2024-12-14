package com.techhive.service.converter;

import com.techhive.api.dto.WebCrawlingResult;
import com.techhive.model.CompanyType;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TechArticleDocumentConverter {

    private final TossTechArticleConverter tossTechArticleConverter;
    private final KakaoTechArticleConverter kakaoTechArticleConverter;
    private final WoowahanTechArticleConverter woowahanTechArticleConverter;
    private final MediumTechArticleConverter mediumTechArticleConverter;
    private final KurlyTechArticleConverter kurlyTechArticleConverter;
    private final GangnamTechArticleConverter gangnamTechArticleConverter;

    public WebCrawlingResult convertToResultFrom(CompanyType companyType, Document document, String link)
        throws IOException {
        return switch (companyType) {
            case TOSS -> tossTechArticleConverter.convertFrom(document, link);
            case KAKAO -> kakaoTechArticleConverter.convertFrom(document, link);
            case WOOWAHAN -> woowahanTechArticleConverter.convertFrom(document, link);
            case KURLY -> kurlyTechArticleConverter.convertFrom(document, link);
            case GANGNAM_UNNI -> gangnamTechArticleConverter.convertFrom(document, link);
            case DAANGN, YOGIYO, MUSINSA, YEOGI -> mediumTechArticleConverter.convertFrom(document, link);
            case UNKNOWN -> null;
        };
    }
}
