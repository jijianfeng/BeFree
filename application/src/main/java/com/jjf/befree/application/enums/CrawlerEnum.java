package com.jjf.befree.application.enums;

/**
 * Created by jijianfeng on 2017/9/20.
 */

public enum CrawlerEnum {

  YouTubeCrawler("YouTubeCrawler");

  private String name;

  CrawlerEnum(String name){
    this.name = name;
  }

  private String getName(){
    return name;
  }

  public static CrawlerEnum selectCrawlerByName(String name){
    for(CrawlerEnum crawlerTypes :values()){
      if(crawlerTypes.getName().equals(name)){
        return crawlerTypes;
      }
    }
    return null;
  }

}
