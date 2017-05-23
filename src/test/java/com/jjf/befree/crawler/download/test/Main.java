package com.jjf.befree.crawler.download.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jjf.befree.crawler.Site;
import com.jjf.befree.crawler.client.HttpClientManager;
import com.jjf.befree.crawler.client.HttpClientRequest;
import com.jjf.befree.crawler.processor.YoutubeProcessor;
import com.jjf.befree.crawler.processor.entity.YoutubeVideo;
import com.jjf.befree.crawler.processor.entity.YoutubeVideoQuality;
import org.apache.http.client.HttpClient;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jjf_lenovo on 2017/5/13.
 */
public class Main {
    /**
     * 测试爬取youtube的真实连接
     */
    @Test
    public void test(){
        String proxyIp = "127.0.0.1"; //走XX-net代理流量
        Integer proxyPort = 8087;
        String url = "https://www.youtube.com/watch?v=fvFRySQtzZg";
        Site site = new Site().setProxyIp(proxyIp).setProxyPort(proxyPort);
        HttpClient client = HttpClientManager.getHttpClinet(site);
        String html = null;
        try {
            html = HttpClientRequest.doGet(client,url,"UTF-8").html();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //获得视频地址json集合
        html = html.substring(html.indexOf("ytplayer.config = ")+18,html.indexOf(";ytplayer.load"));
        YoutubeVideo youtubeVideo = new YoutubeVideo();
        List<YoutubeVideoQuality> urlResult = new ArrayList<YoutubeVideoQuality>(); //结果集
        JSONObject object = JSON.parseObject(html);
        //播放地址
        String videoUrls = (String)object.getJSONObject("args").get("url_encoded_fmt_stream_map");
        urlResult = YoutubeProcessor.getQualityResult(videoUrls);
        //下载地址
        String fmts = (String)object.getJSONObject("args").get("adaptive_fmts");
        List<YoutubeVideoQuality> urlResult2 = YoutubeProcessor.getQualityResult(fmts);
        urlResult.addAll(urlResult2);
        for(YoutubeVideoQuality yq :urlResult){
            System.out.println(yq.getQuality()+"::"+yq.getUrl());
        }
    }
}
