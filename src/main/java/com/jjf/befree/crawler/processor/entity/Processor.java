package com.jjf.befree.crawler.processor.entity;

/**
 * Created by jjf_lenovo on 2017/5/23.
 */

import com.jjf.befree.crawler.*;

import java.util.List;

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
    List<Task> getTasks(Page page, Site site, Crawler crawler);

    /**
     * 根据页面信息提取有效的数据
     * @param page
     * @return
     */
    ResultItems getResultItems(Page page);
}
