package com.jjf.befree.youtube;

import com.jjf.befree.core.Page;
import com.jjf.befree.core.Site;
import com.jjf.befree.core.Task;
import com.jjf.befree.core.download.Download;
import com.jjf.befree.youtube.processor.YoutubeProcessor;
import com.jjf.befree.youtube.processor.entity.YoutubeVideo;

import org.junit.Test;

import java.util.List;

/**
 * Created by jjf_lenovo on 2017/5/13.
 */
public class YoutubeTest {
    /**
     * 测试爬取youtube视频的有效信息
     */
    @Test
    public void testYouTubeDetail(){
        String proxyIp = "127.0.0.1"; //走XX-net代理流量
        Integer proxyPort = 8087;
//        String url = "https://www.youtube.com/watch?v=FzU-czBuDbo";
        String url = "https://www.youtube.com/watch?v=EWhTOkDpyxM";
        Site site = new Site();//.setProxyIp(proxyIp).setProxyPort(proxyPort);
        Page page = Download
            .download(new YouTubeTask(url, "YoutTubeCrawler",site, 1d));//下载页面
        YoutubeVideo detail =  new YoutubeProcessor().getResultItems(page).getData();//处理页面
        System.out.println(detail.toString());
    }

    @Test
    public void testYouTubeListXPath(){
        String proxyIp = "127.0.0.1"; //走XX-net代理流量
        Integer proxyPort = 8087;
        String url = "https://www.youtube.com/watch?v=myQ8htc4obg&list=PL6NNWaAj2a3KwGWKKfidyYgZcLZU_B2sC&index=1";
        Site
            site = new Site().setProxyIp(proxyIp).setProxyPort(proxyPort).setDomain("https://www.youtube.com");
        Page page = Download
            .download(new YouTubeTask(url, "YoutTubeCrawler",site, 1d));//下载页面

        //测试XPath
        String xpath = "//*[@id='playlist-autoscroll-list']/li/a/@href";
//        List<String> list = XPathUtil.getXPathResult(page.getHtml(),xpath);
        List<Task> list = new YoutubeProcessor().getTasks(page, site, new YouTubeCrawler());//XPathUtil.getXPathResult(page.getHtml(),xpath);
        for(int i=0;i<list.size();i++){
            System.out.println(i+":"+list.get(i).getUrl());
        }
    }

    @Test
    public void testDownloadFile(){

    }
}
