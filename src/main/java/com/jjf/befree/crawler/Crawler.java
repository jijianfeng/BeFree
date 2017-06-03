package com.jjf.befree.crawler;

import java.util.List;

/**
 * Created by jjf_lenovo on 2017/5/29.
 */
public interface Crawler {

    /**
     * 以起始的Request启动，可以应对更复杂的情况，当<code>String[] startUrls();</code>无法满足需求的情况下推荐使用
     */
    void start(List<Task> tasks);

    /**
     * 用于设置允许的请求URL匹配规则
     * @return 白名单规则正则表达式列表
     */
    boolean isAllowRules(String url);

    /**
     * 用于设置要放弃访问的请求URL匹配规则
     * @return 黑名单规则正则表达式列表
     */
    boolean isDenyRules(String url);
    /**
     * 当一个请求处理异常次数超过开发者所设置或是默认设置的最大重新处理次数时会调用该方法记录异常请求
     * @param task
     */
    void handleErrorRequest(Task task);

    /**
     * 处理返回的页面,获取更多的种子
     * @param page
     */
    List<Task> processPage(Page page,Site site);

    String getCrawlerName();
}
