package com.jjf.befree.crawler.Client;

/**
 * Created by jjf_lenovo on 2017/5/12.
 */

import com.jjf.befree.crawler.Site;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.cookie.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.cookie.BestMatchSpecFactory;
import org.apache.http.impl.cookie.BrowserCompatSpec;
import org.apache.http.impl.cookie.BrowserCompatSpecFactory;
import org.apache.http.protocol.HttpContext;
import org.apache.log4j.Logger;

import javax.net.ssl.SSLContext;

public class HttpClientManager {

    static Logger log = Logger.getLogger(HttpClientManager.class);
    //最大连接数
//    public final static int MAX_TOTAL_CONNECTIONS = 800;
//
//    //获取连接的最大等待时间
//    public final static int WAIT_TIMEOUT = 60000;
//
//    //每个路由最大连接数
//    public final static int MAX_ROUTE_CONNECTIONS = 400;

    //连接超时时间
    public final static int CONNECT_TIMEOUT = 200000;

    //读取超时时间
    public final static int SOCKET_TIMEOUT = 200000;

    /**
     * 默认简单的
     */
    public static HttpClient getHttpClient(){
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(SOCKET_TIMEOUT) //设置socket超时
                .setConnectTimeout(CONNECT_TIMEOUT) //设置connect超时
                .build();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .build();
        return httpClient;
    }

    /**
     * 带cookie的
     * @return
     */
    public static HttpClient getHttpClientWithCookie(){
        CookieSpecProvider cookieSpecProvider = new CookieSpecProvider() {
            public CookieSpec create(HttpContext context) {
                return new BrowserCompatSpec() {
                    @Override
                    public void validate(Cookie cookie, CookieOrigin origin) throws MalformedCookieException {
                        //Oh, I am easy;
                    }
                };
            }
        };
        Registry<CookieSpecProvider> r = RegistryBuilder
                .<CookieSpecProvider> create()
                .register(CookieSpecs.BEST_MATCH, new BestMatchSpecFactory())
                .register(CookieSpecs.BROWSER_COMPATIBILITY, new BrowserCompatSpecFactory())
                .register("easy", cookieSpecProvider)
                .build();
        RequestConfig requestConfig = RequestConfig.custom()
                .setCookieSpec("easy")
                .setSocketTimeout(SOCKET_TIMEOUT) //设置socket超时
                .setConnectTimeout(CONNECT_TIMEOUT) //设置connect超时
                .build();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultCookieSpecRegistry(r)
                .setDefaultRequestConfig(requestConfig)
                .build();
        return httpClient;
    }

    /**
     * 可以设置代理的
     * @param proxyIp
     * @param proxyPort
     * @return
     */
     public static HttpClient getHttpClientWithProxy(String proxyIp, Integer proxyPort ,Boolean isIgnoreCer){
        HttpHost proxy = new HttpHost(proxyIp, proxyPort);
        DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(SOCKET_TIMEOUT) //链接建立的超时时间；
                .setConnectTimeout(CONNECT_TIMEOUT) //响应超时时间，超过此时间不再读取响应；
                .setConnectionRequestTimeout(200000)//http clilent中从connetcion pool中获得一个connection的超时时间；
                .setProxy(proxy)
                .build();
        if(!isIgnoreCer) { //不需要跳过证书验证
            return HttpClients.custom()
                    .setRoutePlanner(routePlanner)
                    .setDefaultRequestConfig(requestConfig)
                    .build();
        }
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build();
            SSLConnectionSocketFactory sslCSF = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            return HttpClients.custom()
                    .setRoutePlanner(routePlanner)
                    .setDefaultRequestConfig(requestConfig)
                    .setSSLSocketFactory(sslCSF)
                    .build();
        } catch (Exception e) {
            log.error("自定义SSLSocketFactory失败，可能导致无法绕过证书验证");
        }
        return null;
    }

    public static HttpClient getHttpClinet(Site site){
        RequestConfig.Builder configBuilder = RequestConfig.custom();
        HttpClientBuilder clientBuilder = HttpClients.custom();
        //如果有-设置代理
        if((!StringUtils.isEmpty(site.getProxyIp()))&&(site.getProxyPort()!=0)){
            HttpHost proxy = new HttpHost(site.getProxyIp(), site.getProxyPort());
            DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
            configBuilder = configBuilder.setProxy(proxy);
        }
        //设置超时时间
        configBuilder.setConnectTimeout(site.getConnectTimeout())
                .setSocketTimeout(site.getSocketTimeout())
                .setConnectionRequestTimeout(site.getConnectionRequestTimeout());
        RequestConfig requestConfig = configBuilder.build();
        //处理clientBuilder
        //如果有，跳过https证书验证
        /**
         * @see http://blog.csdn.net/a1031397017/article/details/72337281
         */
        if(site.isIgnoreCer()){
            SSLContext sslContext = null;
            try {
                sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build();
            } catch (Exception e) {
                //e.printStackTrace();
                log.error("fali custom SSL , ignoreCer fail");
            }
            SSLConnectionSocketFactory sslCSF = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            clientBuilder = clientBuilder.setSSLSocketFactory(sslCSF);
        }
        //加载config
        clientBuilder = clientBuilder.setDefaultRequestConfig(requestConfig);
        return clientBuilder.build();
    }
}