package com.jjf.befree.crawler;

import com.jjf.befree.crawler.utils.HttpConstant;

/**
 * Created by jjf_lenovo on 2017/5/15.
 */
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

    public static Page fail(){
        Page page = new Page();
        return page;
    }

    /**
     * get html content of page
     *
     * @return html
     */
    public String getHtml() {
        return html;
    }

    /**
     * @param html html
     * @deprecated since 0.4.0
     * The html is parse just when first time of calling {@link #getHtml()}
     */
    public void setHtml(String html) {
        this.html = html;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "Page{" +
                ", html=" + html +
                ", url=" + url +
                ", statusCode=" + statusCode +
                '}';
    }
}