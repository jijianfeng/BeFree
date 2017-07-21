package com.jjf.befree.core.processor;

/**
 * Created by jjf_lenovo on 2017/5/30.
 */
import lombok.Data;
/**
 * 结果集对象
 */
@Data
public class Result<T> {
  //是否成功
  private boolean isSuccess = true;
  //返回信息
  private String message="";
  //数据
  private T data;
}
