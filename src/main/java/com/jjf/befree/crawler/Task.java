package com.jjf.befree.crawler;

/**
 * Created by jjf_lenovo on 2017/5/15.
 */

/**
 * 任务 ，会放进队列里等合适的Processor来认领
 */
public interface Task {

    String getUrl();

    void setUrl();

    String getName();

    void setName(String name);

    Site getSite();

    void setSite(Site site);

    double getRating();

    void setRating(double rating);
}
