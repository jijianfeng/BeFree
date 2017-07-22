package com.jjf.befree.core.processor;

import com.jjf.befree.core.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jijianfeng on 2017/7/22.
 */

/**
 * 存储Map<String,M>>在集合中（通用）
 * @param <M>
 */
public class ResultItems<M> implements Result {
  //是否成功
  public boolean isSuccess = true;
  //返回信息
  public String message="";
  //数据items
  private List<Map<String,M>> items = new ArrayList<>();

  /**
   * 通用型的构造方法，适合简单的List<Map<String,M>>
   * @param items
   */
  public ResultItems(List<Map<String,M>> items){
    items = items;
  }

  /**
   * 异常处理
   * @param e
   */
  public ResultItems(Exception e){
    this.isSuccess = false;
    this.message = e.getClass().toString()+ e.getMessage();
  };
}
