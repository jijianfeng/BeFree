package com.jjf.befree.youtube.processor.entity;

/**
 * Created by jjf_lenovo on 2017/5/16.
 */

import lombok.Data;

@Data
public class YoutubeVideoQuality {

  int itag;
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
}
