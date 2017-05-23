package com.jjf.befree.crawler.download.entity;

import com.jjf.befree.crawler.Page;
import com.jjf.befree.crawler.Task;

/**
 * Created by jjf_lenovo on 2017/5/13.
 */
//下载视频
public interface Download{

    /**
     * 根据任务下载页面，可能对页面有一定的要求，动态页面什么的，考虑扩展
     *
     * @param task task
     * @return page
     */
    public Page download(Task task);

}
