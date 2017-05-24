package com.jjf.befree.crawler.processor.entity;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * Created by jjf_lenovo on 2017/5/14.
 */
public class YoutubeVideo {
    int id;
    List<YoutubeVideoQuality> url;//视频地址
    String title; //标题
    String image; //图像预览取最高画质
    int lengthSeconds;//时长
    String videoId; //视频id
    int views;// 观看次数
    String avgRating; //评级分数
    String keywords;//视频标签，可以深度搜素
    String author;//作者

    public YoutubeVideo(){

    }

    public YoutubeVideo(int id,  List<YoutubeVideoQuality> url, String title, String image, int lengthSeconds, String videoId, int views, String avgRating, String keywords, String author) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.image = image;
        this.lengthSeconds = lengthSeconds;
        this.videoId = videoId;
        this.views = views;
        this.avgRating = avgRating;
        this.keywords = keywords;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<YoutubeVideoQuality> getUrl() {
        return url;
    }

    public void setUrl(List<YoutubeVideoQuality> url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getLengthSeconds() {
        return lengthSeconds;
    }

    public void setLengthSeconds(int lengthSeconds) {
        this.lengthSeconds = lengthSeconds;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(String avgRating) {
        this.avgRating = avgRating;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
    }
}
