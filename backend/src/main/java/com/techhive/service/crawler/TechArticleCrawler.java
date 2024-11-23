package com.techhive.service.crawler;

import com.techhive.api.dto.WebCrawlingResult;
import java.io.IOException;

public interface TechArticleCrawler {

    WebCrawlingResult crawling(String webUrl) throws IOException;
}
