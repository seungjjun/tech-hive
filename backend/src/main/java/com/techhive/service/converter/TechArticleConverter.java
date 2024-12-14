package com.techhive.service.converter;

import com.techhive.api.dto.WebCrawlingResult;
import java.io.IOException;
import org.jsoup.nodes.Document;

public interface TechArticleConverter {

    WebCrawlingResult convertFrom(Document document, String url) throws IOException;
}
