package com.jjf.befree.core.queue;

import com.jjf.befree.core.Task;
import com.jjf.befree.core.enums.TaskStatus;
import com.jjf.befree.core.queue.entity.UrlQueue;

/**
 * Created by jijianfeng on 2017/10/13.
 */
public class DefaultQueue implements UrlQueue {

    @Override
    public Task bPop(String crawlerName) {
        return null;
    }

    @Override
    public boolean push(Task task) {
        return false;
    }

    @Override
    public long len(String crawlerName) {
        return 0;
    }

    @Override
    public int getStatus(Task task) {
        return 0;
    }

    @Override
    public void addStatus(Task task, TaskStatus status) {

    }

    @Override
    public long totalCrawled(String crawlerName) {
        return 0;
    }
}
