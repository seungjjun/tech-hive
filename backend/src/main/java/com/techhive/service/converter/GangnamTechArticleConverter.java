package com.techhive.service.converter;

import com.techhive.api.dto.WebCrawlingResult;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GangnamTechArticleConverter implements TechArticleConverter {

    @Override
    public WebCrawlingResult convertFrom(Document document, String url) throws IOException {
        return null;
    }
}
