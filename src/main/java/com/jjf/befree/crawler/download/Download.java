package com.jjf.befree.crawler.download;

/**
 * Created by jjf_lenovo on 2017/5/13.
 */
//下载视频
public interface Download{
    public void download(String url) throws InterruptedException;
}
