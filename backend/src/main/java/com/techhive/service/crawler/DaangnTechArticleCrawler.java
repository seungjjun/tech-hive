package com.techhive.service.crawler;

import com.techhive.api.dto.WebCrawlingResult;
import com.techhive.entity.OgMetaTagEntity;
import com.techhive.utils.DateUtils;
import com.techhive.utils.ExtractorOgMeta;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DaangnTechArticleCrawler implements TechArticleCrawler {

    private final static String DAANGN_BASE_URL = "https://medium.com/daangn";

    @Override
    public WebCrawlingResult crawling(String webUrl) throws IOException {
        Document document = Jsoup.connect(webUrl).get();

        String title = document.selectFirst("h1[data-testid=storyTitle]").text();
        LocalDateTime dateTime = extractPublishedDateTime(document);
        OgMetaTagEntity ogTagMeta = ExtractorOgMeta.extractOgMetaTag(document);
        String content = extractContent(document);
        String thumbnailImageUrl = extractThumbnailImageUrl(document.selectFirst("source[data-testid=og]"));

        return new WebCrawlingResult(
            title,
            dateTime,
            ogTagMeta,
            "category",
            thumbnailImageUrl,
            content,
            webUrl
        );
    }

    private static String extractContent(Document document) {
        StringBuilder contents = new StringBuilder();
        Elements elements = document.select("[class^=pw-post-body-paragraph]");
        for (Element element : elements) {
            contents.append(element.text()).append("\n");
        }
        String content = contents.toString();
        return content;
    }

    private static LocalDateTime extractPublishedDateTime(Document document) {
        Element publishedMeta = document.selectFirst("meta[property=article:published_time]");
        LocalDateTime dateTime = LocalDateTime.now();
        if (publishedMeta != null) {
            String publishedTime = publishedMeta.attr("content");
            dateTime = DateUtils.convertToLocalDateTimeFromDateTime(publishedTime);
        }
        return dateTime;
    }

    private String extractThumbnailImageUrl(Element element) {
        String thumbnailImageUrl = "";
        if (element != null) {
            String srcset = element.attr("srcset");
            String firstImageUrl = extractFirstImageUrl(srcset);
            if (firstImageUrl != null) {
                thumbnailImageUrl = firstImageUrl;
            }
        } else {
            log.error("No matching element found.");
        }
        return thumbnailImageUrl;
    }

    private static String extractFirstImageUrl(String srcset) {
        if (srcset == null || srcset.isEmpty()) {
            return null;
        }

        String[] entries = srcset.split(",");
        for (String entry : entries) {
            String url = entry.trim().split(" ")[0];
            if (url.endsWith(".png") || url.endsWith(".jpg")) {
                return url;
            }
        }
        return null;
    }
}
