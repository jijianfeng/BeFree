package com.jjf.befree.crawler;

import com.jjf.befree.crawler.utils.HttpConstant;
import java.util.Map;

import java.util.List;

/**
 * Created by jjf_lenovo on 2017/5/15.
 */
public class Page {

    private String html;

    private String url;

    private Request request;

    private Map<String,List<String>> headers;

    private int statusCode = HttpConstant.StatusCode.CODE_200;

    private boolean downloadSuccess = true;

    public Page() {
    }

    public static Page fail(){
        Page page = new Page();
        page.setDownloadSuccess(false);
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

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * get request of current page
     *
     * @return request
     */
    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }

    public boolean isDownloadSuccess() {
        return downloadSuccess;
    }

    public void setDownloadSuccess(boolean downloadSuccess) {
        this.downloadSuccess = downloadSuccess;
    }

    @Override
    public String toString() {
        return "Page{" +
                "request=" + request +
                ", html=" + html +
                ", url=" + url +
                ", headers=" + headers +
                ", statusCode=" + statusCode +
                ", success=" + downloadSuccess +
                '}';
    }
}