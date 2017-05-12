package com.jjf.befree.crawler;

import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.cookie.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.cookie.BestMatchSpecFactory;
import org.apache.http.impl.cookie.BrowserCompatSpec;
import org.apache.http.impl.cookie.BrowserCompatSpecFactory;
import org.apache.http.protocol.HttpContext;

public class HttpConnectionManager {  
 
      
    //最大连接数  
//    public final static int MAX_TOTAL_CONNECTIONS = 800;  
//      
//    //获取连接的最大等待时间  
//    public final static int WAIT_TIMEOUT = 60000;  
//      
//    //每个路由最大连接数  
//    public final static int MAX_ROUTE_CONNECTIONS = 400;  
      
    //连接超时时间  
    public final static int CONNECT_TIMEOUT = 20000;  
      
    //读取超时时间  
    public final static int SOCKET_TIMEOUT = 20000;  
      
    /**
     * 默认简单的
     * @return
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
    public static HttpClient getHttpClientWithProxy(String proxyIp, Integer proxyPort){  
    	HttpHost proxy = new HttpHost(proxyIp, proxyPort);
		DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(2000) //设置socket超时
				.setConnectTimeout(2000) //设置connect超时
				.setProxy(proxy)
				.build();
		CloseableHttpClient httpClient = HttpClients.custom()
				.setRoutePlanner(routePlanner)
				.setDefaultRequestConfig(requestConfig)
				.build();
		return httpClient;
    } 
}  