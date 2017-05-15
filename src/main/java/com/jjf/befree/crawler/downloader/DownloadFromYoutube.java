package com.jjf.befree.crawler.downloader;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jjf.befree.crawler.Client.HttpClientManager;
import com.jjf.befree.crawler.Client.HttpClientRequest;
import com.jjf.befree.crawler.utils.URLEncodeDecode;
import org.apache.http.client.HttpClient;

/**
 * Created by jjf_lenovo on 2017/5/13.
 */
public class DownloadFromYoutube  {
        //https://www.youtube.com/get_video_info?video_id=wWPSpDg5fXg 提取视频信息
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
        //获得视频地址json集合
        html = html.substring(html.indexOf("ytplayer.config = ")+18,html.indexOf(";ytplayer.load"));
        JSONObject object = JSON.parseObject(html);
        String args = (String)object.getJSONObject("args").get("url_encoded_fmt_stream_map");
        System.out.println(URLEncodeDecode.decode(args));
    }

    public static void main(String[] args){
        DownloadFromYoutube d = new DownloadFromYoutube();
        d.download("https://www.youtube.com/watch?v=FzU-czBuDbo");
        d.download("https://www.youtube.com/watch?v=mex2BLETNGk");
    }
}
