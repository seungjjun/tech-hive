package com.techhive.application;

import com.techhive.api.dto.WebCrawlingResult;
import com.techhive.entity.OriginTechArticleEntity;
import com.techhive.entity.TechArticleEntity;
import com.techhive.model.CompanyType;
import com.techhive.model.openai.TechArticleSummaryBody;
import com.techhive.service.TechArticleSearchService;
import com.techhive.service.TechArticleService;
import com.techhive.service.TechArticleSummarizingService;
import com.techhive.service.converter.TechArticleDocumentConverter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TechArticleSummarizingUseCase {

    private final TechArticleService techArticleService;
    private final TechArticleSearchService searchService;
    private final TechArticleSummarizingService techArticleSummarizingService;
    private final TechArticleDocumentConverter techArticleDocumentConverter;

    public void summarizeTechArticle() {
        List<OriginTechArticleEntity> originTechArticleList = techArticleService.getUnSummarizedTechArticle();

        for (OriginTechArticleEntity originTechArticle : originTechArticleList) {
            CompanyType companyType = CompanyType.fromName(originTechArticle.getCompanyName()).orElse(CompanyType.UNKNOWN);
            if (companyType.equals(CompanyType.UNKNOWN)) {
                log.debug("This company of tech article is unknown. company name: {}", originTechArticle.getCompanyName());
                continue;
            }
            Document document = Jsoup.parse(originTechArticle.getOriginArticle());

            try {
                WebCrawlingResult result = techArticleDocumentConverter.convertToResultFrom(companyType, document, originTechArticle.getLink());
                TechArticleSummaryBody body = techArticleSummarizingService.generateTechArticleSummary(result.content());

                TechArticleEntity techArticleEntity = techArticleService.saveSummarizedTechArticle(originTechArticle, companyType, result, body);
                searchService.saveTechArticle(techArticleEntity);
            } catch (Exception e) {
                throw new IllegalArgumentException("summarize failed tech article", e);
            }
        }
    }
}
