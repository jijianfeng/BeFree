package com.jjf.befree.crawler.client;

/**
 * Created by jjf_lenovo on 2017/5/12.
 */

import com.jjf.befree.crawler.Site;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.log4j.Logger;

import javax.net.ssl.SSLContext;

public class HttpClientManager {

    static Logger log = Logger.getLogger(HttpClientManager.class);

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

//    /**
//     * 带cookie的
//     * @return
//     */
//    public static HttpClient getHttpClientWithCookie(){
//        CookieSpecProvider cookieSpecProvider = new CookieSpecProvider() {
//            public CookieSpec create(HttpContext context) {
//                return new BrowserCompatSpec() {
//                    @Override
//                    public void validate(Cookie cookie, CookieOrigin origin) throws MalformedCookieException {
//                        //Oh, I am easy;
//                    }
//                };
//            }
//        };
//        Registry<CookieSpecProvider> r = RegistryBuilder
//                .<CookieSpecProvider> create()
//                .register(CookieSpecs.BEST_MATCH, new BestMatchSpecFactory())
//                .register(CookieSpecs.BROWSER_COMPATIBILITY, new BrowserCompatSpecFactory())
//                .register("easy", cookieSpecProvider)
//                .build();
//        RequestConfig requestConfig = RequestConfig.custom()
//                .setCookieSpec("easy")
//                .setSocketTimeout(SOCKET_TIMEOUT) //设置socket超时
//                .setConnectTimeout(CONNECT_TIMEOUT) //设置connect超时
//                .build();
//        CloseableHttpClient httpClient = HttpClients.custom()
//                .setDefaultCookieSpecRegistry(r)
//                .setDefaultRequestConfig(requestConfig)
//                .build();
//        return httpClient;
//    }

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