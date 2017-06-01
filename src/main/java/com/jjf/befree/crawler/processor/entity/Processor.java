package com.jjf.befree.crawler.processor.entity;

import cn.wanghaomiao.xpath.model.JXDocument;
import com.jjf.befree.crawler.Crawler;
import com.jjf.befree.crawler.Page;
import com.jjf.befree.crawler.ResultItems;
import com.jjf.befree.crawler.Task;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;

import java.util.List;

/**
 * Created by jjf_lenovo on 2017/5/23.
 */

/**
 * 处理页面page获得种子和页面信息
 * @see com.jjf.befree.crawler.processor.YoutubeProcessor
 */
public abstract class Processor {

    static Logger log = Logger.getLogger(Processor.class);
    /**
     * 根据种子页面page获得更多的种子，继续爬取
     * @param page
     * @return
     */
    public abstract Task[] getTasks(Page page, Crawler crawler);

    /**
     * 根据页面信息提取有效的数据
     * @param page
     * @return
     */
    public abstract ResultItems getResultItems(Page page);

    public static String[] getXPathResult(String html,String xpath){
        JXDocument jxDocument = new JXDocument(html);
        List<Object> rs = null;
        try {
            rs = jxDocument.sel(xpath);
        } catch (Exception e) {
            log.error("xpath formate error ,message:"+e.getMessage());
            return new String[0];
        }
        for (Object o:rs){
            if (o instanceof Element){
                int index = ((Element) o).siblingIndex();
                System.out.println(((Element)o).toString());
                System.out.println(index);
            }
            System.out.println(o.toString());
        }
        return new String[0];
    }
}
