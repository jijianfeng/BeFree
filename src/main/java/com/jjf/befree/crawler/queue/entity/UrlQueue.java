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
     * 判断一个task的状态
     * @param task
     * @return
     */
    int getStatus(Task task);

    /**
     * 改变任务的状态
     * @param task
     */
    void addStatus(Task task,TaskStatus status);

    /**
     * 目前总共的抓取数量
     * @return
     */
    long totalCrawled(String crawlerName);
}
