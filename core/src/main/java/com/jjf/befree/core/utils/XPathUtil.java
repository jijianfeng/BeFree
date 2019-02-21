package com.jjf.befree.core.utils;


import org.jsoup.nodes.Element;
import org.seimicrawler.xpath.JXDocument;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jjf_lenovo on 2017/6/1.
 */
public class XPathUtil {

    /**
     * 从html里提出符合xpath的内容
     */
    public static List<String> getXPathStringResult(String html, String xpath) {
        List<Object> rs = xpathParse(html, xpath);
        List<String> result = new ArrayList<>();
        for (Object o : rs) {
            if (o instanceof String) {
                result.add(o.toString());
            }
        }
        return result;
    }

    /**
     * 从html里提出符合xpath的内容
     */
    public static List<Element> getXPathElementResult(String html, String xpath) {
        List<Object> rs = xpathParse(html, xpath);
        List<Element> result = new ArrayList<>();
        for (Object o : rs) {
            if (o instanceof Element) {
                result.add((Element) o);
            }
        }
        return result;
    }

    private static List<Object> xpathParse(String html, String xpath) {
        JXDocument jxDocument = JXDocument.create(html);
        return jxDocument.sel(xpath);
    }
}
