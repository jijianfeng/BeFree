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
public class Youtube {
    /**
     * 测试爬取youtube视频的有效信息
     */
    @Test
    public void testYoutubeDetail(){
        String proxyIp = "127.0.0.1"; //走XX-net代理流量
        Integer proxyPort = 8087;
        String url = "https://www.youtube.com/watch?v=FzU-czBuDbo";
        Site site = new Site().setDomain(url).setProxyIp(proxyIp).setProxyPort(proxyPort);
        Page page = Download.download(site.toTask());
        YoutubeVideo detail = null;
        detail = new YoutubeProcessor().processor(page);
        System.out.println(detail.toString());
    }
}
