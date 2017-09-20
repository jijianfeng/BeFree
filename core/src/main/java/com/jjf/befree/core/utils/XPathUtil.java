package com.jjf.befree.core.utils;

import cn.wanghaomiao.xpath.model.JXDocument;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jjf_lenovo on 2017/6/1.
 */
public class XPathUtil {

  /**
   * 从html里提出符合xpath的内容
   */
  public static List<String> getXPathResult(String html, String xpath) {
    JXDocument jxDocument = new JXDocument(html);
    List<String> result = new ArrayList<>();
    List<Object> rs = null;
    try {
      rs = jxDocument.sel(xpath);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return result;
    }
    int i = 0;
    for (Object o : rs) {
      if (o instanceof String) {
        result.add(o.toString());
      }
    }
    return result;
  }
}
