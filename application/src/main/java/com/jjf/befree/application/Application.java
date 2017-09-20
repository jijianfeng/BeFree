package com.jjf.befree.application;

import com.jjf.befree.application.enums.CrawlerEnum;
import com.jjf.befree.core.Crawler;
import com.jjf.befree.core.Runer;
import com.jjf.befree.core.Site;
import com.jjf.befree.youtube.YouTubeCrawler;
import com.jjf.befree.youtube.YouTubeTask;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Data;

/**
 * Created by jijianfeng on 2017/9/20.
 */
@Controller
@EnableAutoConfiguration
@RequestMapping(path = "/")
public class Application {

  static Logger log = Logger.getLogger(Application.class);

  @RequestMapping(method = RequestMethod.GET, path = "runDefault")
  @ResponseBody
  ResultItem runDefault(@RequestParam("url") String url, @RequestParam("crawlerName") String crawlerName,
                        @RequestParam("isDevelop") boolean isDevelop) {
    CrawlerEnum crawler = CrawlerEnum.selectCrawlerByName(crawlerName);
    if (crawler == null) {
      log.warn("传入的【" + crawlerName + "】找不到对应的crawler");
    }

    switch (crawler) {
      case YouTubeCrawler: {
        Runer.run(new YouTubeCrawler(), new YouTubeTask(url, "", new Site(), 0.05D),isDevelop);
        break;
      }
    }

    ResultItem resultItem = new ResultItem();
    return resultItem;
  }


  public static void main(String[] args) throws Exception {
    SpringApplication.run(Application.class, args);
  }

  @Data
  private class ResultItem<T> {

    T data;
    String message;
    boolean success = true;
    int code = 200;

    ResultItem() {
    }

    ResultItem(T data) {
      this.data = data;
    }

    /**
     * 添加异常信息
     */
    public void setErrorMessage(String message, int code) {
      this.message = message;
      this.code = code;
      this.success = false;
    }
  }
}
