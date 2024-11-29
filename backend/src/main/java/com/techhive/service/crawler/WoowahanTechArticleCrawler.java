package com.techhive.service.crawler;

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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WoowahanTechArticleCrawler implements TechArticleCrawler {

    private static final String WOOWAHAN_BASE_URL = "https://techblog.woowahan.com";

    private static final DateTimeFormatter ENG_FORMATTER = DateTimeFormatter.ofPattern("MMM'.'dd'.'yyyy", Locale.ENGLISH);

    public WebCrawlingResult crawling(String webUrl) throws IOException {
        Document document = Jsoup.connect(webUrl).get();

        Elements header = document.getElementsByClass("post-header");
        String title = header.select("h1").text();
        Elements headerMeta = document.getElementsByClass("post-header-author");
        String publishedDate = headerMeta.select("span:nth-child(1)").text();
        LocalDateTime dateTime = DateUtils.convertToLocalDateTimeFromDate(publishedDate, ENG_FORMATTER);
        OgMetaTagEntity ogTagMeta = ExtractorOgMeta.extractOgMetaTag(document);

        String category = document.getElementsByClass("cat-tag").text();
        String imageUrl = CrawlerUtils.getFirstImageUrl(document.select("img[alt='']"), WOOWAHAN_BASE_URL);

        Elements body = document.getElementsByClass("post-content-body");
        StringBuilder contents = new StringBuilder();
        for (Element e : body) {
            contents.append(e.text()).append("\n");
        }

        return new WebCrawlingResult(
            title,
            dateTime,
            ogTagMeta,
            category,
            imageUrl,
            contents.toString(),
            webUrl
        );
    }


}
