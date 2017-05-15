package com.jjf.befree.crawler.downloader;

import com.jjf.befree.crawler.utils.URLEncodeDecode;
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
        String html = null;
        try {
            html = HttpClientRequest.doGet(client,url,"UTF-8").html();
        } catch (Exception e) {
            e.printStackTrace();
        }
        html = html.substring(html.indexOf("ytplayer.config = "),html.indexOf("};ytplayer.load"));
        html = URLEncodeDecode.decode(html);//url解密
        System.out.println(html);
        //提取下url
//        String urls[] = html.split("https://");
//        int count =0;
//        boolean has = false;
//        for(String ss :urls){
//            ss = "https://"+ss.substring(0,ss.indexOf(";")==-1?ss.length():ss.indexOf(";"));
//            System.out.println(ss);
//        }

    }

    public static void main(String[] args){
        DownloadFromYoutube d = new DownloadFromYoutube();
        d.download("https://www.youtube.com/watch?v=sDbbo8OWz2g");
    }
}
