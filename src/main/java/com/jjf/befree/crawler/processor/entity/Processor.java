package com.jjf.befree.crawler.processor.entity;

import com.jjf.befree.crawler.Page;

/**
 * Created by jjf_lenovo on 2017/5/23.
 */
public interface Processor {
    public Object processor(Page page);
}
