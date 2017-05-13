package com.jjf.befree.crawler.download;

import com.jjf.befree.crawler.HttpClientManager;
import com.jjf.befree.crawler.HttpClientRequest;
import com.jjf.befree.crawler.util.URLEncodeDecode;
import org.apache.http.client.HttpClient;

/**
 * Created by jjf_lenovo on 2017/5/13.
 */
public class DownloadFromYoutube implements Download {
    //https://www.youtube.com/get_video_info?video_id=wWPSpDg5fXg 提取视频信息
    @Override
    public void download(String url) {
        String proxyIp = "127.0.0.1"; //走XX-net代理流量
        Integer proxyPort = 8087;
        HttpClient client = HttpClientManager.getHttpClientWithProxy(proxyIp,proxyPort,true);//.getHttpClient();
        try {
            String html =  HttpClientRequest.doGet(client,url,"UTF-8").html();
            html = URLEncodeDecode.decode(html);//url解密
            //提取下url
            String urls[] = html.split("https://");
            for(String ss :urls){
                System.out.println("https://"+ss.substring(0,ss.indexOf(";")==-1?ss.length():ss.indexOf(";")));
            }
//            System.out.println(html);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args){
        DownloadFromYoutube d = new DownloadFromYoutube();
        d.download("https://www.youtube.com/get_video_info?video_id=wWPSpDg5fXg");
    }
}
