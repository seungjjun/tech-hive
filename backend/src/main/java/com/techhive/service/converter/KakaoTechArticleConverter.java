package com.techhive.service.converter;

import com.techhive.api.dto.WebCrawlingResult;
import com.techhive.entity.OgMetaTagEntity;
import com.techhive.utils.CrawlerUtils;
import com.techhive.utils.DateUtils;
import com.techhive.utils.ExtractorOgMeta;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoTechArticleConverter implements TechArticleConverter {

    private static final String KAKAO_BASE_URL  = "https://tech.kakaopay.com";

    private static final DateTimeFormatter ENG_FORMATTER = DateTimeFormatter.ofPattern("yyyy. MM. dd", Locale.ENGLISH);

    @Override
    public WebCrawlingResult convertFrom(Document document, String webUrl) throws IOException {
        String title = document.selectFirst("h1.astro-QLFJKSAO").text();
        String publishedDate = document.selectFirst("time.astro-QLFJKSAO").text();
        LocalDateTime dateTime = DateUtils.convertToLocalDateTimeFromDate(publishedDate, ENG_FORMATTER);
        OgMetaTagEntity ogTagMeta = ExtractorOgMeta.extractOgMetaTag(document, webUrl);

        String imageUrl = CrawlerUtils.getFirstImageUrl(document.select("img[alt='']"), KAKAO_BASE_URL);

        if (!StringUtils.hasText(imageUrl)) {
            imageUrl = ogTagMeta.getImageUrl();
        }

        Elements body = document.getElementsByClass("container astro-W4P2PMHA");
        StringBuilder contents = new StringBuilder();
        for (Element e : body) {
            contents.append(e.text()).append("\n");
        }

        return new WebCrawlingResult(
            title,
            dateTime,
            ogTagMeta,
            imageUrl,
            contents.toString(),
            webUrl
        );
    }
}
