package com.jjf.befree.youtube.processor.entity;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

import lombok.Data;

/**
 * Created by jjf_lenovo on 2017/5/14.
 */
@Data
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

  public YoutubeVideo() {

  }

  public YoutubeVideo(int id, List<YoutubeVideoQuality> url, String title, String image,
                      int lengthSeconds, String videoId, int views, String avgRating,
                      String keywords, String author) {
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

  @Override
  public String toString() {
    return JSONObject.toJSONString(this);
  }
}
