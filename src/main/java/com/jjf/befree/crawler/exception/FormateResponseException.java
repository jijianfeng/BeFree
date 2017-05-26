package com.jjf.befree.crawler.exception;

/**
 * Created by jjf_lenovo on 2017/5/26.
 */

import java.io.IOException;

/**
 * 对response返回的内容信息进行格式化编码
 */
public class FormateResponseException extends IOException {
    public FormateResponseException(String message){
        super(message);
    }
    public FormateResponseException(Exception exception){
        super(exception);
    }
}
