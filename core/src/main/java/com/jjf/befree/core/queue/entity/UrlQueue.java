package com.jjf.befree.core.queue.entity;

import com.jjf.befree.core.Task;
import com.jjf.befree.core.enums.TaskStatus;

/**
 * Created by jjf_lenovo on 2017/5/24.
 */
public interface UrlQueue {

    /**
     * 阻塞式出队一个请求
     */
    Task bPop(String crawlerName);

    /**
     * 入队一个请求
     */
    boolean push(Task task);

    /**
     * 任务队列剩余长度
     */
    long len(String crawlerName);

    /**
     * 判断一个task的状态
     */
    int getStatus(Task task);

    /**
     * 改变任务的状态
     */
    void addStatus(Task task, TaskStatus status);

    /**
     * 目前总共的抓取数量
     */
    long totalCrawled(String crawlerName);
}
