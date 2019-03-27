package com.jjf.befree.core;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jjf_lenovo on 2017/5/29.
 */
public class Runer {

    static Logger log = Logger.getLogger(Runer.class);

    public static void run(Crawler crawler, Task task, boolean isDevelop) {
        try {
            List<Task> list = new ArrayList<Task>();
            list.add(task);
            crawler.start(list, isDevelop);
        } catch (Exception e) {
            log.error(crawler.getCrawlerName() + "run error" + e.getMessage());
        }
    }

}
