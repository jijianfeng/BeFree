package com.jjf.befree.crawler.processor.entity;

import com.jjf.befree.crawler.*;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by jjf_lenovo on 2017/5/23.
 */

/**
 * 处理页面page获得种子和页面信息
 * @see com.jjf.befree.crawler.processor.YoutubeProcessor
 */
public interface Processor {

    static Logger log = Logger.getLogger(Processor.class);
    /**
     * 根据种子页面page获得更多的种子，继续爬取
     * @param page
     * @return
     */
    public abstract List<Task> getTasks(Page page, Site site, Crawler crawler);

    /**
     * 根据页面信息提取有效的数据
     * @param page
     * @return
     */
    public abstract ResultItems getResultItems(Page page);

}
