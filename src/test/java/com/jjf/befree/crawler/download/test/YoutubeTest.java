package com.jjf.befree.crawler.download.test;

import com.jjf.befree.crawler.Page;
import com.jjf.befree.crawler.Site;
import com.jjf.befree.crawler.download.Download;
import com.jjf.befree.crawler.processor.YoutubeProcessor;
import com.jjf.befree.crawler.processor.entity.YoutubeVideo;
import org.junit.Test;

/**
 * Created by jjf_lenovo on 2017/5/13.
 */
public class YoutubeTest {
    /**
     * 测试爬取youtube视频的有效信息
     */
    @Test
    public void testYoutubeDetail(){
        String proxyIp = "127.0.0.1"; //走XX-net代理流量
        Integer proxyPort = 8087;
//        String url = "https://www.youtube.com/watch?v=FzU-czBuDbo";
        String url = "https://www.youtube.com/watch?v=aPzvKH8AVf0";
        Site site = new Site().setUrl(url).setProxyIp(proxyIp).setProxyPort(proxyPort);
        Page page = Download.download(site.toTask());//下载页面
        YoutubeVideo detail =  new YoutubeProcessor().getResultItems(page).get();//处理页面
        System.out.println(detail.toString());
    }
}
