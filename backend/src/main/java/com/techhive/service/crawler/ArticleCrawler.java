package com.techhive.service.crawler;

import com.techhive.entity.OriginTechArticleEntity;
import com.techhive.model.CompanyType;
import com.techhive.repository.OriginTechArticleRepository;
import java.io.IOException;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArticleCrawler {

    private final OriginTechArticleRepository techArticleRepository;

    @Transactional
    public void crawling(String url, CompanyType companyType) throws IOException {
        Document document = Jsoup.connect(url).get();

        OriginTechArticleEntity originTechArticleEntity = new OriginTechArticleEntity(
            companyType.name(),
            document.html(),
            url,
            LocalDateTime.now()
        );

        techArticleRepository.save(originTechArticleEntity);
    }
}
