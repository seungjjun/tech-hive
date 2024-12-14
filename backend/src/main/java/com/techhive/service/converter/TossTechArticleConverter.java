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
public class TossTechArticleConverter implements TechArticleConverter {

    private static final String TOSS_BASE_URL = "https://toss.tech";
    private static final DateTimeFormatter KOR_FORMATTER = DateTimeFormatter.ofPattern("yyyy'년' M'월' d'일'", Locale.KOREAN);

    @Override
    public WebCrawlingResult convertFrom(Document document, String url) throws IOException {
        String title = document.getElementsByClass("css-vf4rrt e132k2574").text();
        OgMetaTagEntity ogMetaTag = ExtractorOgMeta.extractOgMetaTag(document, url);

        String publishedDate = document.getElementsByClass("css-154r2lc esnk6d50").text();
        LocalDateTime dateTime = DateUtils.convertToLocalDateTimeFromDate(publishedDate,KOR_FORMATTER);
        String imageUrl = CrawlerUtils.getFirstImageUrl(document.select("img[alt='']"), TOSS_BASE_URL);

        if (!StringUtils.hasText(imageUrl)) {
            imageUrl = ogMetaTag.getImageUrl();
        }

        Elements body = document.getElementsByClass("css-1vn47db");
        StringBuilder contents = new StringBuilder();
        for (Element e : body) {
            contents.append(e.text()).append("\n");
        }

        return new WebCrawlingResult(
            title,
            dateTime,
            ogMetaTag,
            imageUrl,
            contents.toString(),
            url
        );
    }
}
