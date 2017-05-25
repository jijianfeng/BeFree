package com.jjf.befree.crawler.queue.entity;

import com.jjf.befree.crawler.Task;

/**
 * Created by jjf_lenovo on 2017/5/24.
 */
public interface UrlQueue {

    /**
     * 阻塞式出队一个请求
     * @return
     */
    Task bPop(String crawlerName);

    /**
     * 入队一个请求
     * @param task
     * @return
     */
    boolean push(Task task);
    /**
     * 任务队列剩余长度
     * @return
     */
    long len(String crawlerName);

    /**
     * 判断一个URL是否处理过了
     * @param task
     * @return
     */
    boolean isProcessed(Task task);

    /**
     * 记录一个处理过的请求
     * @param task
     */
    void addProcessed(Task task);

    /**
     * 目前总共的抓取数量
     * @return
     */
    long totalCrawled(String crawlerName);
}
