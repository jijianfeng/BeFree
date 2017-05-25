package com.jjf.befree.crawler;

import java.io.Serializable;

/**
 * Created by jjf_lenovo on 2017/5/15.
 */
public class Task  implements Serializable{

    private String UUID;

    private Site site;

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }
}
