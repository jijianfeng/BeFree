package com.jjf.befree.crawler.processor.entity;

/**
 * Created by jjf_lenovo on 2017/5/16.
 */
public class YoutubeVideoQuality {
    int itag ;
    String type;
    String quality;
    String signature;
    String url;
    
    public YoutubeVideoQuality() {

    }

    public YoutubeVideoQuality(int itag, String type, String quality, String signature, String url) {
        this.itag = itag;
        this.type = type;
        this.quality = quality;
        this.signature = signature;
        this.url = url;
    }

    public int getItag() {
        return itag;
    }

    public void setItag(int itag) {
        this.itag = itag;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
