package com.jjf.befree.youtube;

import com.jjf.befree.core.Crawler;
import com.jjf.befree.core.Page;
import com.jjf.befree.core.Site;
import com.jjf.befree.core.Task;
import com.jjf.befree.core.download.Download;
import com.jjf.befree.youtube.processor.YoutubeProcessor;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.*;

/**
 * Created by jjf_lenovo on 2017/5/29.
 */
public class YouTubeCrawler implements Crawler {

  static Logger log = Logger.getLogger(YouTubeCrawler.class);

  private String[] allowRules = {}; //白名单

  private String[] denyRules = {}; //黑名单

  private Map fileSaveMap;//= new HashMap();

  public void start(List<Task> tasks, boolean isDevelop) {
    YoutubeProcessor process = new YoutubeProcessor();
    for (Task task : tasks) {
      //种子视频
      if (!isAllowRules(task.getUrl())) {
        continue;
      }
      Page page = Download.download(task);
      if (isDevelop) {
        List<Task> takskDevelop = process.getTasks(page, task.getSite(), this);
        //TODO 发送到队列
      }
      Task downloadTask = process.getDownloadTask(page, task.getSite(), this);
      //TODO 发送到队列
    }
  }

  public boolean isAllowRules(String url) {
    for (String allowRule : allowRules) {
      if (allowRule.equals(url)) {
        return true;
      }
    }
    return false;
  }

  public boolean isDenyRules(String url) {
    for (String denyRule : denyRules) {
      if (denyRule.equals(url)) {
        return true;
      }
    }
    return false;
  }

  public void handleErrorRequest(Task task) {

  }

  public List<Task> processPage(Page page, Site site) {
    return new YoutubeProcessor().getTasks(page, site, this);
  }

  public String getCrawlerName() {
    return new String("YouTubeCrawler");
  }

}
