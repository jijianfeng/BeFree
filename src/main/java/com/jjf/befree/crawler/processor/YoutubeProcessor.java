package com.jjf.befree.crawler.processor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jjf.befree.crawler.Page;
import com.jjf.befree.crawler.processor.entity.Processor;
import com.jjf.befree.crawler.processor.entity.YoutubeVideo;
import com.jjf.befree.crawler.processor.entity.YoutubeVideoQuality;
import com.jjf.befree.crawler.utils.URLEncodeDecode;
import com.jjf.befree.crawler.utils.YoutubeItagToQuality;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jjf_lenovo on 2017/5/23.
 */
public class YoutubeProcessor implements Processor {

    static Logger log = Logger.getLogger(YoutubeProcessor.class);

    @Override
    public YoutubeVideo processor(Page page) {
        YoutubeVideo youtubeVideo = new YoutubeVideo();
        youtubeVideo.setUrl(getVideoUrlsFromPage(page));
        return null;
    }

    /**
     * 从page里获取到有效的视频下载信息
     * @param page
     * @return
     */
    public static List<YoutubeVideoQuality> getVideoUrlsFromPage(Page page){
        String html =page.getHtml();
        html = html.substring(html.indexOf("ytplayer.config = ")+18,html.indexOf(";ytplayer.load"));
        YoutubeVideo youtubeVideo = new YoutubeVideo();
        List<YoutubeVideoQuality> urlResult = new ArrayList<YoutubeVideoQuality>(); //结果集
        JSONObject object = JSON.parseObject(html);
        //播放地址
        String videoUrls = (String)object.getJSONObject("args").get("url_encoded_fmt_stream_map");
        urlResult = getQualityResult(videoUrls);
        //下载地址
        String fmts = (String)object.getJSONObject("args").get("adaptive_fmts");
        List<YoutubeVideoQuality> urlResult2 = getQualityResult(fmts);
        urlResult2.addAll(urlResult);
        return urlResult2;
    }

    /**
     * 从youtubeURL集合中提取有效信息
     * @param videoUrls
     * @return
     */
    public static List<YoutubeVideoQuality> getQualityResult(String videoUrls){
        List<YoutubeVideoQuality> urlResult = new ArrayList<YoutubeVideoQuality>(); //结果集
        String videos[] = videoUrls.split(",");
        for(String video:videos){
            YoutubeVideoQuality youtubeVideoQuality = new YoutubeVideoQuality();
            //获取type，url。sin等类型
            String itags[] = video.split("&");
            for(String itag : itags){
                String nameValue[] = itag.split("=");
//                if(nameValue[0].equals("signature")){
//                    youtubeVideoQuality.setSignature(URLEncodeDecode.decode(nameValue[1]));
//                }
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
                log.info("success ~ "+youtubeVideoQuality.getType()+":"+youtubeVideoQuality.getQuality()+":"+youtubeVideoQuality.getUrl());
                urlResult.add(youtubeVideoQuality);
            }
        }
        return urlResult;
    }
}
