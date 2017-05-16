package com.jjf.befree.crawler.downloader;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jjf.befree.crawler.Client.HttpClientManager;
import com.jjf.befree.crawler.Client.HttpClientRequest;
import com.jjf.befree.crawler.Page;
import com.jjf.befree.crawler.Task;
import com.jjf.befree.crawler.downloader.entity.Download;
import com.jjf.befree.crawler.downloader.entity.YoutubeVideo;
import com.jjf.befree.crawler.downloader.entity.YoutubeVideoQuality;
import com.jjf.befree.crawler.utils.URLEncodeDecode;
import com.jjf.befree.crawler.utils.YoutubeItagToQuality;
import org.apache.http.client.HttpClient;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jjf_lenovo on 2017/5/13.
 */
public class DownloadFromYoutube implements Download {

    static Logger log = Logger.getLogger(DownloadFromYoutube.class);
    //https://www.youtube.com/get_video_info?video_id=wWPSpDg5fXg 提取视频信息
    public Page download(Task task) {
        return null;
    }

    /**
     * 从youtubeURL集合中提取有效信息
     * @param videoUrls
     * @return
     */
    public static  List<YoutubeVideoQuality> getQualityResult(String videoUrls){
        List<YoutubeVideoQuality> urlResult = new ArrayList<YoutubeVideoQuality>(); //结果集
        String videos[] = videoUrls.split(",");
        for(String video:videos){
            YoutubeVideoQuality youtubeVideoQuality = new YoutubeVideoQuality();
            //获取type，url。sin等类型
            String itags[] = video.split("&");
            for(String itag : itags){
                String nameValue[] = itag.split("=");
                if(nameValue[0].equals("signature")){
                    youtubeVideoQuality.setSignature(URLEncodeDecode.decode(nameValue[1]));
                }
                if(nameValue[0].equals("url")){
                    youtubeVideoQuality.setUrl(URLEncodeDecode.decode(nameValue[1]));
                }
                if(nameValue[0].equals("itag")){
                    youtubeVideoQuality.setItag(Integer.valueOf(nameValue[1]));
                    youtubeVideoQuality.setQuality(YoutubeItagToQuality.getResolutionByItag(youtubeVideoQuality.getItag()));
                    youtubeVideoQuality.setType(YoutubeItagToQuality.getTypeByItag(youtubeVideoQuality.getItag()));
                }
            }
            if(youtubeVideoQuality.getQuality()!=null){
                urlResult.add(youtubeVideoQuality);
            }
        }
        return urlResult;
    }

    public static void main(String[] args){
        String proxyIp = "127.0.0.1"; //走XX-net代理流量
        Integer proxyPort = 8087;
//        String url = "https://www.youtube.com/watch?v=FzU-czBuDbo";
        String url = "https://www.youtube.com/watch?v=28pheRq8LLs";
        HttpClient client = HttpClientManager.getHttpClientWithProxy(proxyIp,proxyPort,true);//.getHttpClient();
        String html = null;
        try {
            html = HttpClientRequest.doGet(client,url,"UTF-8").html();
        } catch (Exception e) {
            log.error("httpclient get fail ,check Internet");
            e.printStackTrace();
        }
        //获得视频地址json集合
        html = html.substring(html.indexOf("ytplayer.config = ")+18,html.indexOf(";ytplayer.load"));
        YoutubeVideo youtubeVideo = new YoutubeVideo();
        List<YoutubeVideoQuality> urlResult = new ArrayList<YoutubeVideoQuality>(); //结果集
        JSONObject object = JSON.parseObject(html);
        //播放地址
        String videoUrls = (String)object.getJSONObject("args").get("url_encoded_fmt_stream_map");
        urlResult = getQualityResult(videoUrls);
        //下载地址
        String fmts = (String)object.getJSONObject("args").get("adaptive_fmts");
        List<YoutubeVideoQuality> urlResult2 = getQualityResult(fmts);;
        for(YoutubeVideoQuality youtubeVideoQuality:urlResult2){
            urlResult.add(youtubeVideoQuality);
        }
        for(YoutubeVideoQuality youtubeVideoQuality:urlResult){
            if(youtubeVideoQuality.getType().equals("mp4"))
            System.out.println(youtubeVideoQuality.getQuality()+":"+youtubeVideoQuality.getUrl());
        }
    }
}
