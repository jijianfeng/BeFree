package com.jjf.befree.crawler;

/**
 * Created by jjf_lenovo on 2017/5/15.
 */
/**
 * Interface for identifying different tasks.<br>
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.1.0
 */
public interface Task {

    /**
     * unique id for a task.
     *
     * @return uuid
     */
    public String getUUID();

    /**
     * site of a task
     *
     * @return site
     */
    public Site getSite();

}
