package com.jjf.befree.youtube;

import com.jjf.befree.core.Site;
import com.jjf.befree.core.Task;

/**
 * Created by jjf_lenovo on 2017/6/3.
 */
public class YouTubeTask implements Task {

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

  public String getUrl() {
    return url;
  }

  public void setUrl() {
    this.url = url;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Site getSite() {
    return site;
  }

  public void setSite(Site site) {
    this.site = site;
  }

  public double getRating() {
    return rating;
  }

  public void setRating(double rating) {
    this.rating = rating;
  }
}
