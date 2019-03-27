package com.jjf.befree.core.enums;

/**
 * Created by jijianfeng on 2017/6/25.
 */
public enum TaskStatus {

    NORMAL(0, "正常"),
    DOING(1, "正在进行中"),
    FINISH(2, "已完成"),
    FAIL(-1, "失败");

    private int status;

    private String name;

    TaskStatus(int status, String name) {
        this.status = status;
        this.name = name;
    }

    TaskStatus() {
    }//防止破坏结构

    public int getStatus() {
        return this.status;
    }

    public String getName() {
        return this.name;
    }
}

