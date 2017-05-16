package com.jjf.befree.crawler.utils;

import com.jjf.befree.crawler.Client.HttpClientManager;
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
public class Download {
    static Logger log = Logger.getLogger(Download.class);
    public static boolean downloadFileFromUrl(String url,String pathName,String proxyIp,int proxyPort)  {
        InputStream in = null;
        OutputStream out = null;
        try {
            Site site = new Site().setProxyIp(proxyIp).setProxyPort(proxyPort);
            HttpClient client = HttpClientManager.getHttpClinet(site);
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = client.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            in = entity.getContent();
            long length = entity.getContentLength();
            if (length <= 0) {
                log.error("file don't exit from "+url);
                return false;
             }
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
        } catch (Exception e) {
            log.error("fail download from "+url);
            log.error(e.getMessage());
            return false;
        }finally{
            try {
                if(in != null){
                    in.close();
                }
             } catch (IOException e) {
                    //e.printStackTrace();
                    log.error("in can't close");
                    return false;
             }

             try {
                if(out != null){
                    out.close();
                }
             } catch (IOException e) {
                 //e.printStackTrace();
                 log.error("out can't close");
                 return false;
             }
         }
        return true;
    }

    public static void main(String args[]){
        downloadFileFromUrl("https://r5---sn-i3b7kn7r.googlevideo.com/videoplayback?sparams=dur,ei,id,ip,ipbits,itag,lmt,mime,mm,mn,ms,mv,pl,ratebypass,requiressl,source,upn,expire&signature=9E8003285F4062AFF33EFF26218C2457FB276C35.4D287CAC995F6278130095751AF160BBFCD62C69&ipbits=0&ip=112.10.180.221&lmt=1489558682126127&itag=22&ei=VwsbWYqzL8mZ_AO7r4fYBg&id=o-AATutRZP_68QDKKJSvc7FH3tTQcab7Av9eKV0tzxFGEc&upn=8OjCNgTQnZE&mm=31&mn=sn-i3b7kn7r&ratebypass=yes&mime=video/mp4&requiressl=yes&source=youtube&pl=19&dur=1802.147&expire=1494966199&mt=1494944391&mv=u&ms=au&key=yt6","D:\\1.mp4","127.0.0.1",8087);
    }
}
