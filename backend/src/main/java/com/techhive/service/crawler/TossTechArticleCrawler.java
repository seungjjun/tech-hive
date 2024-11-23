package com.techhive.service.crawler;

import com.techhive.api.dto.WebCrawlingResult;
import com.techhive.utils.CrawlerUtils;
import com.techhive.utils.DateUtils;
import java.io.IOException;
import java.time.LocalDateTime;
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
public class TossTechArticleCrawler implements TechArticleCrawler {

    private final static String TOSS_BASE_URL = "https://toss.tech";


    @Override
    public WebCrawlingResult crawling(String webUrl) throws IOException {
        Document document = Jsoup.connect(webUrl).get();

        String title = document.getElementsByClass("css-vf4rrt e132k2574").text();
        Elements categories = document.getElementsByClass("p-chip typography--semibold p-chip--small typography--small p-chip--grey");
        String category = "";
        if (!categories.isEmpty()) {
            Element categoriesFirst = categories.getFirst();
            category = categoriesFirst.text().replace("#", "");
        }

        String publishedDate = document.getElementsByClass("css-154r2lc esnk6d50").text();
        LocalDateTime dateTime = DateUtils.convertToLocalDateTimeFromKorDate(publishedDate);
        String imageUrl = CrawlerUtils.getFirstImageUrl(document.select("img[alt='']"), TOSS_BASE_URL);

        Elements body = document.getElementsByClass("css-1vn47db");
        StringBuilder contents = new StringBuilder();
        for (Element e : body) {
            contents.append(e.text()).append("\n");
        }

        return new WebCrawlingResult(
            title,
            dateTime,
            category,
            imageUrl,
            contents.toString(),
            webUrl
        );
    }
}
