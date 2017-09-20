package com.jjf.befree.core;

import com.jjf.befree.core.utils.HttpConstant;

import lombok.Data;

/**
 * Created by jjf_lenovo on 2017/5/15.
 */
@Data
public class Page {

  private String html;

  private String url;

  private int statusCode = HttpConstant.StatusCode.CODE_200;

  public Page(String html, String url, int statusCode) {
    this.html = html;
    this.url = url;
    this.statusCode = statusCode;
  }

  public Page() {

  }

  public static Page fail() {
    Page page = new Page();
    return page;
  }

}