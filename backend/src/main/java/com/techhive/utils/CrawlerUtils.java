package com.techhive.utils;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawlerUtils {

    public static String getFirstImageUrl(Elements imgElements, String baseUrl) {
        String imageUrl = "";
        for (Element imgElement : imgElements) {
            String src = imgElement.attr("src");
            String alt = imgElement.attr("alt").toLowerCase();
            if (src.endsWith(".svg") || src.contains("svg") || alt.contains("svg") || alt.contains("logo") || alt.contains("icon")) {
                continue;
            }
            imageUrl = src.startsWith("https") ? src : baseUrl + src;
            break;
        }
        return imageUrl;
    }
}
