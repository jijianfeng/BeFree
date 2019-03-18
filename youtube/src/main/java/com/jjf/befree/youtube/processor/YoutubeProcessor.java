package com.jjf.befree.youtube.processor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jjf.befree.core.Crawler;
import com.jjf.befree.core.processor.ResultData;
import com.jjf.befree.core.Task;
import com.jjf.befree.core.processor.Processor;
import com.jjf.befree.youtube.YouTubeTask;
import com.jjf.befree.core.utils.URLEncodeDecode;
import com.jjf.befree.core.utils.YoutubeItagToQuality;
import com.jjf.befree.core.Page;
import com.jjf.befree.core.Site;
import com.jjf.befree.core.utils.XPathUtil;
import com.jjf.befree.youtube.processor.entity.YoutubeVideo;
import com.jjf.befree.youtube.processor.entity.YoutubeVideoQuality;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by jjf_lenovo on 2017/5/23.
 */
public class YoutubeProcessor implements Processor {

  static Logger log = Logger.getLogger(YoutubeProcessor.class);

  /**
   * 建立youtube的下载视频任务
   */
  public Task getDownloadTask(Page page, Site site, Crawler crawler) {
    JSONObject videoJson = getVideoJsonFromUrl(page).getJSONObject("args");//视频信息
    return new YouTubeTask(page.getUrl(), "DownloadTask", site, videoJson.getDouble("avg_rating"));
  }

  /**
   * 根据下载任务
   */
  public Boolean downloadFileFromTask(Task task) {
    return false;
  }

  /**
   * 解析列表页面展示的其他相关视频且建立task
   */

  public List<Task> getTasks(Page page, Site site, Crawler crawler) {
    /**
     * url为以下三种情况之一的 ，肯定是视频列表
     * 1.**watch?v=**&list=**  不包含index
     * 2.**watch?v=**&index=*&list=**
     * 3.和2类似，param中index和list的顺序替换
     * 提取视频url的path: //*[@id='playlist-autoscroll-list']/li/a/@href
     */
    List<Task> tasks = new ArrayList<Task>();
    if (page.getUrl().contains("&list=")) {
      List<String>
          taskUrls =
          XPathUtil
              .getXPathStringResult(page.getHtml(), "//*[@id='playlist-autoscroll-list']/li/a/@href");
      for (String url : taskUrls) {
        if (!url.startsWith("https")) {//相对路径
          url = site.getDomain() + url;
        }
        tasks.add(
            new YouTubeTask(url, crawler.getCrawlerName() + UUID.randomUUID().toString(), site,
                            0d));
      }
    }
    return tasks;
  }

  /**
   * 返回youtube视频信息
   */
  public ResultData<YoutubeVideo> getResultItems(Page page) {
    YoutubeVideo youtubeVideo = new YoutubeVideo();
    try {
      JSONObject videoJson = getVideoJsonFromUrl(page);//视频信息
      youtubeVideo.setUrl(getVideoUrlsFromPage(videoJson)); //不同格式清晰度的视频连接
      videoJson = videoJson.getJSONObject("args");
      youtubeVideo.setVideoId(videoJson.getString("video_id"));
      youtubeVideo.setImage("https://i.ytimg.com/vi/" + youtubeVideo.getVideoId()
                            + "/hqdefault.jpg");//https://i.ytimg.com/vi/FzU-czBuDbo/hqdefault.jpg
      youtubeVideo.setTitle(videoJson.getString("title"));
      youtubeVideo.setAuthor(videoJson.getString("author"));
      youtubeVideo.setLengthSeconds(videoJson.getInteger("length_seconds"));
      youtubeVideo.setViews(videoJson.getInteger("view_count"));
      youtubeVideo.setAvgRating(videoJson.getString("avg_rating"));
      youtubeVideo.setKeywords(videoJson.getString("keywords"));
    } catch (Exception e) {
      log.error("解析youtube page异常：" + e.getMessage());
      ResultData<YoutubeVideo> result = new ResultData(e);
      return new ResultData(e);
    }
    return new ResultData(youtubeVideo);
  }

  /**
   * 从youtube单视频页面中分析出视频信息生成json
   */
  public static JSONObject getVideoJsonFromUrl(Page page) {
    String html = page.getHtml();
    html = html.substring(html.indexOf("ytplayer.config = ") + 18, html.indexOf(";ytplayer.load"));
    return JSON.parseObject(html);
  }

  /**
   * 从page里获取到有效的视频下载信息
   */
  public static List<YoutubeVideoQuality> getVideoUrlsFromPage(JSONObject json) {
    //播放地址
    String videoUrls = (String) json.getJSONObject("args").get("url_encoded_fmt_stream_map");
    List<YoutubeVideoQuality> urlResult = getQualityResult(videoUrls);
    //下载地址
    String fmts = (String) json.getJSONObject("args").get("adaptive_fmts");
    List<YoutubeVideoQuality> urlResult2 = getQualityResult(fmts);
    urlResult2.addAll(urlResult);
    return urlResult2;
  }

  /**
   * 从youtubeURL集合中提取有效信息
   */
  public static List<YoutubeVideoQuality> getQualityResult(String videoUrls) {
    List<YoutubeVideoQuality> urlResult = new ArrayList<YoutubeVideoQuality>(); //结果集
    String videos[] = videoUrls.split(",");
    for (String video : videos) {
      YoutubeVideoQuality youtubeVideoQuality = new YoutubeVideoQuality();
      //获取type，url。sin等类型
      String itags[] = video.split("&");
      for (String itag : itags) {
        String nameValue[] = itag.split("=");
//                if(nameValue[0].equals("signature")){
//                    youtubeVideoQuality.setSignature(URLEncodeDecode.decode(nameValue[1]));
//                }
        if (nameValue[0].equals("url")) {
          youtubeVideoQuality.setUrl(URLEncodeDecode.decode(nameValue[1]));
        }
        if (nameValue[0].equals("itag")) {
          youtubeVideoQuality.setItag(Integer.valueOf(nameValue[1]));
          youtubeVideoQuality.setQuality(
              YoutubeItagToQuality.getResolutionByItag(youtubeVideoQuality.getItag()));
          youtubeVideoQuality
              .setType(YoutubeItagToQuality.getTypeByItag(youtubeVideoQuality.getItag()));
        }
      }
      if (youtubeVideoQuality.getQuality() != null) {
        log.info(
            "success ~ " + youtubeVideoQuality.getType() + ":" + youtubeVideoQuality.getQuality()
            + ":" + youtubeVideoQuality.getUrl());
        urlResult.add(youtubeVideoQuality);
      }
    }
    return urlResult;
  }
}
