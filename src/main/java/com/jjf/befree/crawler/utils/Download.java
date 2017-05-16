package com.jjf.befree.crawler.utils;

import org.apache.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

/**
 * Created by jjf_lenovo on 2017/5/16.
 */
public class Download {
    static Logger log = Logger.getLogger(Download.class);
    public static boolean downloadFileFromUrl(String url,String pathName)  {
        URL ul = null;
        try {
            ul = new URL(url);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8087));
        HttpURLConnection conn = (HttpURLConnection) ul.openConnection(proxy);
        BufferedInputStream bi = new BufferedInputStream(conn.getInputStream());
        FileOutputStream bs = new FileOutputStream(pathName);
        //System.out.println("文件大约："+(conn.getContentLength()/1024)+"K");
        log.info("start download from "+url);
        log.info("file size ablout:" +conn.getContentLength()/1024/1024+"Mb");
        byte[] by = new byte[1024];
        int len = 0;
        while((len=bi.read(by))!=-1){
            bs.write(by,0,len);
        }
        bs.close();
        bi.close();
        } catch (IOException e) {
            log.error("fail download from "+url);
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    public static void main(String args[]){
        downloadFileFromUrl("https://r5---sn-i3b7kn7r.googlevideo.com/videoplayback?dur=1802.100&id=o-ANMudGWpmGXTKNA3s-h92IyDJIH5idmAjs46K8kgbVW7&ei=P78aWe_JMIyuqAGZvarADQ&requiressl=yes&sparams=clen,dur,ei,gir,id,ip,ipbits,itag,keepalive,lmt,mime,mm,mn,ms,mv,pl,requiressl,source,upn,expire&expire=1494946719&itag=160&mime=video/mp4&source=youtube&clen=6517120&lmt=1489553943604300&upn=vVECko5F1KI&signature=2F86133EC9A808D4473C1D5D35CCA9FECD59F907.AA645FD9FD8A543919247BCEA649FA8E3BE3574F&mt=1494924919&mv=u&pl=17&ipbits=0&ms=au&mm=31&mn=sn-i3b7kn7r&ip=112.10.180.221&key=yt6&keepalive=yes&gir=yes","c:1.mp4");
    }
}
