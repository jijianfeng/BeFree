package com.jjf.befree.crawler;

/**
 * Created by jjf_lenovo on 2017/5/15.
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

/**
 * Selectable html.<br>
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.1.0
 */
public class Html  {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static volatile boolean INITED = false;

    /**
     * Disable jsoup html entity escape. It can be set just before any Html instance is created.
     */
    public static boolean DISABLE_HTML_ENTITY_ESCAPE = false;

    /**
     * Disable jsoup html entity escape. It is a hack way only for jsoup 1.7.2.
     */
    private void disableJsoupHtmlEntityEscape() {
        if (DISABLE_HTML_ENTITY_ESCAPE && !INITED) {
            Entities.EscapeMode.base.getMap().clear();
            Entities.EscapeMode.extended.getMap().clear();
            Entities.EscapeMode.xhtml.getMap().clear();
            INITED = true;
        }
    }

    /**
     * Store parsed document for better performance when only one text exist.
     */
    private Document document;

    public Html(String text, String url) {
        try {
            disableJsoupHtmlEntityEscape();
            this.document = Jsoup.parse(text, url);
        } catch (Exception e) {
            this.document = null;
            logger.warn("parse document error ", e);
        }
    }

    public Html(String text) {
        try {
            disableJsoupHtmlEntityEscape();
            this.document = Jsoup.parse(text);
        } catch (Exception e) {
            this.document = null;
            logger.warn("parse document error ", e);
        }
    }

    public Html(Document document) {
        this.document = document;
    }

    public Document getDocument() {
        return document;
    }

    protected List<Element> getElements() {
        return Collections.<Element>singletonList(getDocument());
    }

    public static Html create(String text) {
        return new Html(text);
    }

}
