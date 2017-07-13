package com.jjf.befree.crawler;

/**
 * Created by jjf_lenovo on 2017/6/3
 */
public class YouTubeTask implements Task{
    private String url; //爬取地址
    private String name;//任务名
    private Site site;//配置
    private double rating;//评级

    public YouTubeTask(String url, String name, Site site, double rating) {
        this.url = url;
        this.name = name;
        this.site = site;
        this.rating = rating;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl() {
        this.url = url;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void setSite(Site site) {
        this.site = site;
    }

    @Override
    public double getRating() {
        return rating;
    }

    @Override
    public void setRating(double rating) {
        this.rating = rating;
    }
}
