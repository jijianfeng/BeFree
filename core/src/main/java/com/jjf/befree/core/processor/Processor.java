package com.jjf.befree.core.processor;

/**
 * Created by jjf_lenovo on 2017/5/23.
 */

import com.jjf.befree.core.Crawler;
import com.jjf.befree.core.Task;

import java.util.List;

import com.jjf.befree.core.Page;
import com.jjf.befree.core.Site;

/**
 * 处理页面page获得种子和页面信息
 */
public interface Processor {

    /**
     * 根据种子页面page获得更多的种子，继续爬取
     */
    List<Task> getTasks(Page page, Site site, Crawler crawler);

    /**
     * 根据页面信息提取有效的数据
     */
    Result getResultItems(Page page);
}
