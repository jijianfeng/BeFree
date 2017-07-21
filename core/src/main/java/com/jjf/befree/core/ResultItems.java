package com.jjf.befree.core;

import com.jjf.befree.core.processor.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by jjf_lenovo on 2017/5/30.
 */

/**
 * 根据页面解析出来的结果集 支持两种存储方式
 * 1.存储对象result
 * 2.存储Map<String,Object>>在集合中（通用）
 */
//TODO 设计有问题
public class ResultItems {
    private Object result ;//= new ArrayList();

    private Task task;

    private Map<String,Object> mapResult = new HashMap() ;

    public ResultItems(Result result){
        this.result = result;
    }

    public ResultItems(List<Map<String,Object>> data){
        result = data;
    }

    public <T> T get(String key) {
        Object o = mapResult.get(key);
        if (o == null) {
            return null;
        }
        return (T) mapResult.get(key);
    }

    public <T> T get() {
        return (T) this.result;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
