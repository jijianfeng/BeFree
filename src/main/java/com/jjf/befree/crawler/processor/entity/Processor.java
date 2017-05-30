package com.jjf.befree.crawler.processor.entity;

import com.jjf.befree.crawler.Page;
import com.jjf.befree.crawler.ResultItems;
import com.jjf.befree.crawler.Task;

/**
 * Created by jjf_lenovo on 2017/5/23.
 */

/**
 * 处理页面page获得种子和页面信息
 * @see com.jjf.befree.crawler.processor.YoutubeProcessor
 */
public interface Processor {
    /**
     * 根据种子页面page获得更多的种子，继续爬取
     * @param page
     * @return
     */
    public Task[] getTasks(Page page);

    /**
     * 根据页面信息提取有效的数据
     * @param page
     * @return
     */
    public ResultItems getResultItems(Page page);
}
