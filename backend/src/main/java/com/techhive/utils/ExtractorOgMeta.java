package com.techhive.utils;

import com.techhive.entity.OgMetaTagEntity;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ExtractorOgMeta {

    public static OgMetaTagEntity extractOgMetaTag(Document document, String webUrl) {
        OgMetaTagEntity ogMetaTag = null;
        Element ogTitleMeta = document.selectFirst("meta[property=og:title]");
        Element ogDescriptionMeta = document.selectFirst("meta[property=og:description]");
        Element ogImageUrl = document.selectFirst("meta[property=og:image]");

        if (ogTitleMeta != null && ogDescriptionMeta != null && ogImageUrl != null) {
            ogMetaTag = new OgMetaTagEntity(
                ogTitleMeta.attr("content"),
                ogDescriptionMeta.attr("content"),
                ogImageUrl.attr("content"),
                webUrl
            );
        }
        return ogMetaTag;
    }
}
