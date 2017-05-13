package com.jjf.befree.crawler.download.test;

import com.jjf.befree.crawler.download.DownloadFromYoutube;

/**
 * Created by jjf_lenovo on 2017/5/13.
 */
public class Main {
    public static void main(String[] args){
        DownloadFromYoutube d = new DownloadFromYoutube();
        d.download("https://www.youtube.com/get_video_info?video_id=wWPSpDg5fXg");
    }
}
