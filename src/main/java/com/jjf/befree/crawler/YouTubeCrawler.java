package com.jjf.befree.crawler;

import com.jjf.befree.crawler.processor.YoutubeProcessor;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.*;

/**
 * Created by jjf_lenovo on 2017/5/29.
 */
public class YouTubeCrawler  implements Crawler{

    static Logger log = Logger.getLogger(YouTubeCrawler.class);

    private String[] allowRules; //白名单

    private String[] denyRules; //黑名单

    private Map fileSaveMap ;//= new HashMap();

    @Override
    public void start(List<Task> tasks) {

    }

    @Override
    public boolean isAllowRules(String url) {
        for(String allowRule :allowRules){
            if(allowRule.equals(url)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isDenyRules(String url) {
        for(String denyRule :denyRules){
            if(denyRule.equals(url)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void handleErrorRequest(Task task) {

    }

    @Override
    public List<Task> processPage(Page page,Site site) {
        return new YoutubeProcessor().getTasks(page,site,this);
    }

    @Override
    public String getCrawlerName() {
        return new String("YouTubeCrawler");
    }

    /**
     * 读取文件的存储路径，放到内存中，减少IO
     */
    public  Map getFileSavePath() throws FileNotFoundException{
        if(fileSaveMap == null){
            fileSaveMap = new HashMap();
            Properties prop = new Properties();
            InputStream in =null;
            try {
                //读取属性文件YouTubeFile.properties
                String filePath = YouTubeCrawler.class
                        .getClassLoader()
                        .getResource("YouTubeFile.properties")
                        .getPath();
                in = new BufferedInputStream(
                        new FileInputStream(filePath)
                );
                prop.load(in);     ///加载属性列表
                Iterator<String> it=prop.stringPropertyNames().iterator();
                while(it.hasNext()){
                    String key=it.next();
//                    System.out.println(key+":"+prop.getProperty(key));
                    fileSaveMap.put(key,prop.getProperty(key));
                }
            } catch (FileNotFoundException e) {
                throw e;
            } catch (IOException e) {
                log.error(e.getMessage());
            } finally {
                if(in != null){
                    try {
                        in.close();
                    } catch (IOException e) {
                        log.error(e.getMessage());
                    }
                }
            }
        }
        return fileSaveMap;
    }

//    public static void main(String args[]) throws FileNotFoundException {
//        YouTubeCrawler youTubeCrawler = new YouTubeCrawler();
//        System.out.println(youTubeCrawler.getFileSavePath().toString());
//    }
}
