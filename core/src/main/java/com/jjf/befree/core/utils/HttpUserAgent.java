package com.jjf.befree.core.utils;

/**
 * Created by jjf_lenovo on 2017/5/12.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HttpUserAgent {


    private static List<String> agents;

    static{
        agents = new ArrayList<String>();

        //IE
        agents.add("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0)");
        agents.add("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.2)");
        agents.add("Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
        agents.add("Mozilla/4.0 (compatible; MSIE 5.0; Windows NT)");

        //Firefox
        agents.add("Mozilla/5.0 (Windows; U; Windows NT 5.2) Gecko/2008070208 Firefox/3.0.1 ");
        agents.add("Mozilla/5.0 (Windows; U; Windows NT 5.1) Gecko/20070309 Firefox/2.0.0.3 ");
        agents.add("Mozilla/5.0 (Windows; U; Windows NT 5.1) Gecko/20070803 Firefox/1.5.0.12");
        agents.add("Mozilla/5.0 (Windows NT 6.3; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0)");
        agents.add("Mozilla/5.0 (Windows NT 5.1; rv:5.0) Gecko/20100101 Firefox/5.0");

        //Opera
        agents.add("Opera/9.27 (Windows NT 5.2; U; zh-cn)");
        agents.add("Opera/8.0 (Macintosh; PPC Mac OS X; U; en)");
        agents.add("Mozilla/5.0 (Macintosh; PPC Mac OS X; U; en) Opera 8.0");

        //Safari
        agents.add("Mozilla/5.0 (Windows; U; Windows NT 5.2) AppleWebKit/525.13 (KHTML, like Gecko) Version/3.1 Safari/525.13");
        agents.add("Mozilla/5.0 (iPhone; U; CPU like Mac OS X) AppleWebKit/420.1 (KHTML, like Gecko) Version/3.0 Mobile/4A93 Safari/419.3");

        //Chrome
        agents.add("Mozilla/5.0 (Windows; U; Windows NT 5.2) AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.2.149.27 Safari/525.13");
        agents.add("Mozilla/5.0 (Windows NT 5.2) AppleWebKit/534.30 (KHTML, like Gecko) Chrome/12.0.742.122 Safari/534.30");

        //Navigator
        agents.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.8.1.12) Gecko/20080219 Firefox/2.0.0.12 Navigator/9.0.0.6");

        //360极速浏览器
        agents.add("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1) ;  QIHU 360EE)");

    }


    public static String get(){
        return agents.get(new Random().nextInt(agents.size()-1));
    }
}