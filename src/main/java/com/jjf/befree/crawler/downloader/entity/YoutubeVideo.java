package com.jjf.befree.crawler.downloader.entity;

import java.util.List;

/**
 * Created by jjf_lenovo on 2017/5/14.
 */
public class YoutubeVideo {
    int id;
    String itags;
    List<YoutubeVideoQuality> url;//视频地址
    String title; //标题
    String image; //图像预览取最高画质
    int lengthSeconds;//时长
    String videoId; //视频id
    String views;// 观看次数
    String rating;//评级|标签|类型
    String author;//作者

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItags() {
        return itags;
    }

    public void setItags(String itags) {
        this.itags = itags;
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

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
