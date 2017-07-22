package com.jjf.befree.core;

import com.jjf.befree.core.processor.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;
/**
 * Created by jjf_lenovo on 2017/5/30.
 */

/**
 * 根据页面解析出来的结果集 支持两种存储方式
 * 1.存储对象result
 * 2.存储Map<String,M>>在集合中（通用）
 */
@Data
public class ResultItems<T extends Result,M> {
    private T result ;

    private Task task;

    private List<Map<String,M>> mapResult = new ArrayList<>();

    public ResultItems(T result){ //自定义类型result
        this.result = result;
    }

    public ResultItems(List<Map<String,M>> data){
        mapResult = data;
    }

    public M get(int index,String key) {
        return mapResult.get(index).get(key);
    }
}
