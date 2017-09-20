package com.jjf.befree.core.processor;

import com.jjf.befree.core.Task;

import lombok.Data;
/**
 * Created by jjf_lenovo on 2017/5/30.
 */

/**
 * 根据页面解析出来的结果集 支持两种存储方式
 *
 * @see com.jjf.befree.core.processor.ResultData 1.存储对象result
 * @see com.jjf.befree.core.processor.ResultItems 2.存储Map<String,M>>在集合中（通用）
 */
@Data
public class ResultData<T> implements Result {

  //是否成功
  public boolean isSuccess = true;
  //返回信息
  public String message = "";
  //数据
  private T data;

  /**
   * 自定义ResultItems的构造方法，满足自定义需求
   */
  public ResultData(T data) { //自定义类型result
    this.data = data;
  }

  /**
   * 异常处理
   */
  public ResultData(Exception e) {
    this.isSuccess = false;
    this.message = e.getClass().toString() + e.getMessage();
  }
}
