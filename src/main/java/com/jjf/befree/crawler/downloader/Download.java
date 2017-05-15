package com.jjf.befree.crawler.downloader;

import com.jjf.befree.crawler.Page;
import com.jjf.befree.crawler.Task;

/**
 * Created by jjf_lenovo on 2017/5/13.
 */
//下载视频
public interface Download{

    /**
     * Downloads web pages and store in Page object.
     *
     * @param task task
     * @return page
     */
    public Page download(Task task);

}
