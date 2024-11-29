package com.techhive.utils;

import com.techhive.entity.OgMetaTagEntity;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ExtractorOgMeta {

    public static OgMetaTagEntity extractOgMetaTag(Document document) {
        OgMetaTagEntity ogMetaTag = null;
        Element ogTitleMeta = document.selectFirst("meta[property=og:title]");
        Element ogWebUrlMeta = document.selectFirst("meta[property=og:url]");
        Element ogDescriptionMeta = document.selectFirst("meta[property=og:description]");
        Element ogImageUrl = document.selectFirst("meta[property=og:image]");

        if (ogTitleMeta != null && ogWebUrlMeta != null && ogDescriptionMeta != null && ogImageUrl != null) {
            ogMetaTag = new OgMetaTagEntity(
                ogTitleMeta.attr("content"),
                ogDescriptionMeta.attr("content"),
                ogImageUrl.attr("content"),
                ogWebUrlMeta.attr("content")
            );
        }
        return ogMetaTag;
    }
}
