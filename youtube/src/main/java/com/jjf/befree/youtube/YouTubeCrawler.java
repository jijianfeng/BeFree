package com.jjf.befree.youtube;

import com.jjf.befree.core.Crawler;
import com.jjf.befree.core.Page;
import com.jjf.befree.core.Site;
import com.jjf.befree.core.Task;
import com.jjf.befree.core.download.Download;
import com.jjf.befree.youtube.processor.YoutubeProcessor;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.*;

/**
 * Created by jjf_lenovo on 2017/5/29.
 */
public class YouTubeCrawler implements Crawler {

    static Logger log = Logger.getLogger(YouTubeCrawler.class);

    private String[] allowRules; //白名单

    private String[] denyRules; //黑名单

    private Map fileSaveMap ;//= new HashMap();

    public void start(List<Task> tasks, Boolean isDevelop) {
        YoutubeProcessor process = new YoutubeProcessor();
        for(Task task:tasks){
            //种子视频
            Page page = Download.download(task);
            if(isDevelop){
                List<Task>  takskDevelop = process.getTasks(page,task.getSite(),this);
                //TODO 发送到队列
            }
            Task downloadTask = process.getDownloadTask(page,task.getSite(),this);
            //TODO 发送到队列
        }
    }

    public boolean isAllowRules(String url) {
        for(String allowRule :allowRules){
            if(allowRule.equals(url)){
                return true;
            }
        }
        return false;
    }

    public boolean isDenyRules(String url) {
        for(String denyRule :denyRules){
            if(denyRule.equals(url)){
                return true;
            }
        }
        return false;
    }

    public void handleErrorRequest(Task task) {

    }

    public List<Task> processPage(Page page,Site site) {
        return new YoutubeProcessor().getTasks(page,site,this);
    }

    public String getCrawlerName() {
        return new String("YouTubeCrawler");
    }


    //TODO 适配jdk 1.8
//    /**
//     * 读取文件的存储路径，放到内存中，减少IO
//     */
//    public  Map getFileSavePath() throws FileNotFoundException{
//        if(fileSaveMap == null){
//            fileSaveMap = new HashMap();
//            Properties prop = new Properties();
//            InputStream in =null;
//            try {
//                //读取属性文件YouTubeFile.properties
//                String filePath = YouTubeCrawler.class
//                        .getClassLoader()
//                        .getResource("main/resources/YouTubeFile.properties")
//                        .getPath();
//                in = new BufferedInputStream(
//                        new FileInputStream(filePath)
//                );
//                prop.load(in);     ///加载属性列表
//                Iterator<String> it=prop.stringPropertyNames().iterator();
//                while(it.hasNext()){
//                    String key=it.next();
////                    System.out.println(key+":"+prop.getProperty(key));
//                    fileSaveMap.put(key,prop.getProperty(key));
//                }
//            } catch (FileNotFoundException e) {
//                throw e;
//            } catch (IOException e) {
//                log.error(e.getMessage());
//            } finally {
//                if(in != null){
//                    try {
//                        in.close();
//                    } catch (IOException e) {
//                        log.error(e.getMessage());
//                    }
//                }
//            }
//        }
//        return fileSaveMap;
//    }
}
