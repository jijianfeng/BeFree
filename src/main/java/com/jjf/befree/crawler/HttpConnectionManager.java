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
 
      
    //���������  
//    public final static int MAX_TOTAL_CONNECTIONS = 800;  
//      
//    //��ȡ���ӵ����ȴ�ʱ��  
//    public final static int WAIT_TIMEOUT = 60000;  
//      
//    //ÿ��·�����������  
//    public final static int MAX_ROUTE_CONNECTIONS = 400;  
      
    //���ӳ�ʱʱ��  
    public final static int CONNECT_TIMEOUT = 20000;  
      
    //��ȡ��ʱʱ��  
    public final static int SOCKET_TIMEOUT = 20000;  
      
    /**
     * Ĭ�ϼ򵥵�
     * @return
     */
    public static HttpClient getHttpClient(){  
    	RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(SOCKET_TIMEOUT) //����socket��ʱ
				.setConnectTimeout(CONNECT_TIMEOUT) //����connect��ʱ
				.build();
		CloseableHttpClient httpClient = HttpClients.custom()
				.setDefaultRequestConfig(requestConfig)
				.build();
		return httpClient;
    } 
    
    /**
     * ��cookie��
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
				.setSocketTimeout(SOCKET_TIMEOUT) //����socket��ʱ
				.setConnectTimeout(CONNECT_TIMEOUT) //����connect��ʱ
				.build();
		CloseableHttpClient httpClient = HttpClients.custom()
				.setDefaultCookieSpecRegistry(r)
				.setDefaultRequestConfig(requestConfig)
				.build();
		return httpClient;
    } 
    
    /**
     * �������ô����
     * @param proxyIp
     * @param proxyPort
     * @return
     */
    public static HttpClient getHttpClientWithProxy(String proxyIp, Integer proxyPort){  
    	HttpHost proxy = new HttpHost(proxyIp, proxyPort);
		DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(2000) //����socket��ʱ
				.setConnectTimeout(2000) //����connect��ʱ
				.setProxy(proxy)
				.build();
		CloseableHttpClient httpClient = HttpClients.custom()
				.setRoutePlanner(routePlanner)
				.setDefaultRequestConfig(requestConfig)
				.build();
		return httpClient;
    } 
}  