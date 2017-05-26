package com.jjf.befree.crawler.utils;

import com.jjf.befree.crawler.client.HttpClientManager;
import com.jjf.befree.crawler.Site;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.log4j.Logger;

import java.io.*;

/**
 * Created by jjf_lenovo on 2017/5/16.
 */
public class DownloadFileFromUrl {
    static Logger log = Logger.getLogger(DownloadFileFromUrl.class);
    public static Long downloadFileFromUrl(String url,String pathName,String proxyIp,int proxyPort)  {
        log.info("start download from url "+url);
        InputStream in = null;
        OutputStream out = null;
        long fileSize = 0;
        try {
            Site site = new Site().setProxyIp(proxyIp).setProxyPort(proxyPort);
            HttpClient client = HttpClientManager.getHttpClinet(site);
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = client.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            in = entity.getContent();
            fileSize = entity.getContentLength();
            if (fileSize <= 0) {
                log.error("file don't exit from "+url);
                return 0L;
            }
            log.info("file size from "+fileSize+"bytes---"+url);
            File file = new File(pathName);
            if(!file.exists()){
                file.createNewFile();
            }
            out = new FileOutputStream(file);
            byte[] buffer = new byte[4096];
            int readLength = 0;
            while ((readLength=in.read(buffer)) > 0) {
                byte[] bytes = new byte[readLength];
                System.arraycopy(buffer, 0, bytes, 0, readLength);
                out.write(bytes);
            }
            out.flush();
            log.info("download file from url success from "+url);
        } catch (Exception e) {
            log.error("fail download from "+url);
            log.error(e.getMessage());
            return 0L;
        }finally{
            try {
                if(in != null){
                    in.close();
                }
             } catch (IOException e) {
                    //e.printStackTrace();
                    log.error("in can't close");
                    log.error(e.getMessage());
                    return 0L;
             }

             try {
                if(out != null){
                    out.close();
                }
             } catch (IOException e) {
                 //e.printStackTrace();
                 log.error("out can't close");
                 log.error(e.getMessage());
                 return 0L;
             }
         }
        return fileSize;
    }

//    public static void main(String args[]){
//        String url = "https://r3---sn-vgqsrn7d.googlevideo.com/videoplayback?dur=1802.147&source=youtube&lmt=1489558682126127&requiressl=yes&signature=076ED25318A2551F25B59BAFF86A9C3F394AF133.6D7708EB8D9A2530EDD0199CECC2642CC9993C48&key=cms1&itag=22&expire=1496082684&ratebypass=yes&ei=nBQsWcGCHMfX4AL2n77wAQ&ip=112.10.180.114&pl=28&pcm2=no&id=o-ANYhU-YGZ2M-6iVZ--GsG0Fz7N4NK7a8r3KL1GC5OTUa&sparams=dur,ei,expire,id,initcwndbps,ip,ipbits,ipbypass,itag,lmt,mime,mip,mm,mn,ms,mv,pcm2,pl,ratebypass,requiressl,source&mime=video/mp4&ipbits=0&redirect_counter=1&req_id=b89d9448e304a3ee&cms_redirect=yes&ipbypass=yes&mip=107.178.195.208&mm=31&mn=sn-vgqsrn7d&ms=au&mt=1496060964&mv=m";
//        downloadFileFromUrl(url,"F:\\temp\\1.mp4","127.0.0.1",8087);
//    }
}
