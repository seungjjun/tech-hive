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
public class KurlyTechArticleConverter implements TechArticleConverter {

    private static final String KURLY_BASE_URL = "https://helloworld.kurly.com";
    private static final DateTimeFormatter ENG_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd.", Locale.ENGLISH);

    @Override
    public WebCrawlingResult convertFrom(Document document, String webUrl) throws IOException {
        String title = document.selectFirst("h1.page-title").text();
        String publishedDate = document.selectFirst("span.post-date").text().replace("게시 날짜: ", "");
        LocalDateTime dateTime = DateUtils.convertToLocalDateTimeFromDate(publishedDate, ENG_FORMATTER);
        OgMetaTagEntity ogTagMeta = ExtractorOgMeta.extractOgMetaTag(document, webUrl);
        String imageUrl = CrawlerUtils.getFirstImageUrl(document.select("img[alt='']"), KURLY_BASE_URL);

        // 컬리는 본문 안의 이미지를 png파일로 갖고 있지 않음
        if (!StringUtils.hasText(imageUrl)) {
            imageUrl = ogTagMeta.getImageUrl();
        }

        Elements body = document.getElementsByClass("page-content");
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
