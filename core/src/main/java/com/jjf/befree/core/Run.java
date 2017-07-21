package com.jjf.befree.core;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jjf_lenovo on 2017/5/29.
 */
public class Run {

    static Logger log = Logger.getLogger(Run.class);

    public static void run(Crawler crawler,Task task){
        try{
            List<Task> list = new ArrayList<Task>();
            list.add(task);
            crawler.start(list,false);
        }
        catch (Exception e){
            log.error(crawler.getCrawlerName()+"run error"+e.getMessage());
        }
    }

}
