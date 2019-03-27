package com.jjf.befree.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.Args;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by jijianfeng on 2017/8/18.
 */
public class HttpUtils {

    private Logger logger = LoggerFactory.getLogger(HttpUtils.class);
    private int SOCK_TIMEOUT = 5000;
    private int CONN_TIMEOUT = 10000;
    private RequestConfig
            config =
            RequestConfig.custom().setSocketTimeout(SOCK_TIMEOUT).setConnectTimeout(CONN_TIMEOUT).build();
    private final int HTTP_SUCCESS = 200;
    private final String CHARSET_UTF8 = "UTF-8";
    private static volatile String PROXY_GATEWAY;

    /**
     * 简单装饰，像个Util类
     */
    public static HttpUtils getClientDefault() {
        return new HttpUtils();
    }

    /**
     * 简单装饰，像个Util类
     */
    public static HttpUtils getClient(int SOCK_TIMEOUT, int CONN_TIMEOUT) {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.config = RequestConfig
                .custom().setSocketTimeout(SOCK_TIMEOUT).setConnectTimeout(CONN_TIMEOUT).build();
        return httpUtils;
    }

    /**
     * 重新设置超时
     *
     * @param SOCK_TIMEOUT 读取超时
     * @param CONN_TIMEOUT 连接超时
     */
    public HttpUtils rebuildTime(int SOCK_TIMEOUT, int CONN_TIMEOUT) {
        config =
                RequestConfig.custom().setSocketTimeout(SOCK_TIMEOUT).setConnectTimeout(CONN_TIMEOUT)
                        .build();
        return this;
    }

    private HttpUtils() {
    }

    public String get(String url) {
        HttpGet method = (HttpGet) getProxyMethod(url, true);
        return executeMethod(method);
    }

    public String get(String url, Map<String, ?> paramsMap, String charset) {
        if (paramsMap != null) {
            Set<String> keySet = paramsMap.keySet();
            StringBuilder sb = new StringBuilder(keySet.size() * 8);
            sb.append("?");
            Iterator var5 = keySet.iterator();

            while (var5.hasNext()) {
                String key = (String) var5.next();

                try {
                    String value = URLEncoder.encode(String.valueOf(paramsMap.get(key)), charset);
                    sb.append(key).append("=").append(value).append("&");
                } catch (UnsupportedEncodingException var8) {
                    logger.error(var8.getMessage(), var8);
                }
            }

            String params = sb.toString();
            url = url + params.substring(0, params.length() - 1);
        }

        HttpGet method = (HttpGet) getProxyMethod(url, true);
        return executeMethod(method, charset, false);
    }

    public String get(String url, Map<String, ?> paramsMap) {
        if (paramsMap != null) {
            Set<String> keySet = paramsMap.keySet();
            StringBuilder sb = new StringBuilder(keySet.size() * 8);
            sb.append("?");
            Iterator var4 = keySet.iterator();

            while (var4.hasNext()) {
                String key = (String) var4.next();

                try {
                    String value = URLEncoder.encode(String.valueOf(paramsMap.get(key)), "UTF-8");
                    sb.append(key).append("=").append(value).append("&");
                } catch (UnsupportedEncodingException var7) {
                    logger.error(var7.getMessage(), var7);
                }
            }

            String params = sb.toString();
            url = url + params.substring(0, params.length() - 1);
        }

        HttpGet method = (HttpGet) getProxyMethod(url, true);
        return executeMethod(method);
    }

    public String post(String url, Map<String, ?> paramsMap, String charset,
                       boolean isLocalToString) {
        HttpPost method = (HttpPost) getProxyMethod(url, false);
        List<NameValuePair> nvps = new ArrayList(paramsMap.size());
        Set<String> keySet = paramsMap.keySet();
        Iterator var7 = keySet.iterator();

        while (var7.hasNext()) {
            String key = (String) var7.next();
            nvps.add(new BasicNameValuePair(key, String.valueOf(paramsMap.get(key))));
        }

        try {
            method.setEntity(new UrlEncodedFormEntity(nvps, charset));
        } catch (UnsupportedEncodingException var9) {
            logger.error(var9.getMessage(), var9);
        }

        return executeMethod(method, charset, isLocalToString);
    }

    public String post(String url, Map<String, ?> paramsMap) {
        HttpPost method = (HttpPost) getProxyMethod(url, false);
        List<NameValuePair> nvps = new ArrayList(paramsMap.size());
        Set<String> keySet = paramsMap.keySet();
        Iterator var5 = keySet.iterator();

        while (var5.hasNext()) {
            String key = (String) var5.next();
            nvps.add(new BasicNameValuePair(key, String.valueOf(paramsMap.get(key))));
        }

        try {
            method.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
        } catch (UnsupportedEncodingException var7) {
            logger.error(var7.getMessage(), var7);
        }

        return executeMethod(method);
    }

    public String postXml(String url, String body, String charset) {
        HttpPost method = (HttpPost) getProxyMethod(url, false);
        method.setEntity(new StringEntity(body, ContentType.create("text/xml", charset)));
        return executeMethod(method, charset, false);
    }

    public String postXmlStream(String url, String body, String charset) {
        HttpPost method = (HttpPost) getProxyMethod(url, false);
        method.setEntity(new StringEntity(body, ContentType.create("text/xml", charset)));
        return executeMethod(method, charset);
    }

    public String postXml(String url, String body) {
        HttpPost method = (HttpPost) getProxyMethod(url, false);
        method.setEntity(new StringEntity(body, ContentType.create("text/xml", "UTF-8")));
        return executeMethod(method);
    }

    public String postJson(String url, String body, String charset) {
        HttpPost method = (HttpPost) getProxyMethod(url, false);
        method.setEntity(new StringEntity(body, ContentType.create("application/json", charset)));
        return executeMethod(method, charset, false);
    }

    public String postJson(String url, String body) {
        HttpPost method = (HttpPost) getProxyMethod(url, false);
        method.setEntity(new StringEntity(body, ContentType.create("application/json", "UTF-8")));
        return executeMethod(method);
    }

    private final HttpRequestBase getProxyMethod(String url, boolean isGet) {
        int protocolEnd = url.indexOf("://");
        String protocol = url.substring(0, protocolEnd);
        String remainUrl = url.substring(protocolEnd + 3);
        if (!remainUrl.contains("/")) {
            remainUrl = remainUrl + "/";
        }

        int hostEnd = remainUrl.indexOf("/");
        String host = remainUrl.substring(0, hostEnd);
        if (StringUtils.isBlank(PROXY_GATEWAY)) {
            PROXY_GATEWAY = "http://proxy-static.s.qima-inc.com";
        }

        String proxyUrl = PROXY_GATEWAY + remainUrl.substring(hostEnd);
        HttpRequestBase method = null;
        if (isGet) {
            method = new HttpGet(proxyUrl);
        } else {
            method = new HttpPost(proxyUrl);
        }

        ((HttpRequestBase) method).setHeader("Host", host);
        if ("https".equalsIgnoreCase(protocol)) {
            ((HttpRequestBase) method).setHeader("Scheme", "https");
        } else {
            ((HttpRequestBase) method).setHeader("Scheme", "http");
        }

        return (HttpRequestBase) method;
    }

    private final String executeMethod(HttpRequestBase method, String charset,
                                       boolean isLocalToString) {
        CloseableHttpClient client = HttpClients.createDefault();
        method.setConfig(config);

        String var5;
        try {
            HttpResponse response = client.execute(method);
            if (200 != response.getStatusLine().getStatusCode()) {
                logger.warn("http error req={},resp={}", ToStringBuilder.reflectionToString(method),
                        EntityUtils
                                .toString(response.getEntity(), charset));
                return null;
            }

            if (isLocalToString) {
                var5 = toString(response.getEntity(), charset);
                return var5;
            }

            var5 = EntityUtils.toString(response.getEntity(), charset);
        } catch (Exception var17) {
            logger.error("#http client post error with reason :  msg={} # req={}", var17.getMessage(),
                    ToStringBuilder
                            .reflectionToString(method));
            logger.error(var17.getMessage(), var17);
            return null;
        } finally {
            method.releaseConnection();

            try {
                client.close();
            } catch (IOException var16) {
                logger.error("#http client close post connection error with reason : {}#",
                        var16.getMessage());
                logger.error(var16.getMessage(), var16);
            }

        }

        return var5;
    }

    private final String executeMethod(HttpRequestBase method, String charset) {
        CloseableHttpClient client = HttpClients.createDefault();
        method.setConfig(config);

        String var4;
        try {
            HttpResponse response = client.execute(method);
            if (200 != response.getStatusLine().getStatusCode()) {
                logger.warn("http error req={},resp={}", ToStringBuilder.reflectionToString(method),
                        toString((HttpResponse) response, (String) charset));
                return null;
            }

            var4 = toString((HttpResponse) response, (String) charset);
        } catch (Exception var15) {
            logger.error("#http client post error with reason :  msg={} # req={}", var15.getMessage(),
                    ToStringBuilder
                            .reflectionToString(method));
            logger.error(var15.getMessage(), var15);
            return null;
        } finally {
            method.releaseConnection();

            try {
                client.close();
            } catch (IOException var14) {
                logger.error("#http client close post connection error with reason : {}#",
                        var14.getMessage());
                logger.error(var14.getMessage(), var14);
            }

        }

        return var4;
    }

    private final String executeMethod(HttpRequestBase method) {
        CloseableHttpClient client = HttpClients.createDefault();
        method.setConfig(config);

        try {
            HttpResponse response = client.execute(method);
            if (200 == response.getStatusLine().getStatusCode()) {
                String var3 = EntityUtils.toString(response.getEntity(), "UTF-8");
                return var3;
            }

            logger
                    .warn("http error req={},resp={}", ToStringBuilder.reflectionToString(method), EntityUtils
                            .toString(response.getEntity(), "UTF-8"));
        } catch (Exception var14) {
            logger.error("#http client post error with reason :  msg={} # req={}", var14.getMessage(),
                    ToStringBuilder
                            .reflectionToString(method));
            logger.error(var14.getMessage(), var14);
        } finally {
            method.releaseConnection();

            try {
                client.close();
            } catch (IOException var13) {
                logger.error("#http client close post connection error with reason : {}#",
                        var13.getMessage());
                logger.error(var13.getMessage(), var13);
            }

        }

        return null;
    }

    public String toString(HttpEntity entity, String defaultCharset) throws IOException,
            ParseException {
        return toString(entity, defaultCharset != null ? Charset.forName(defaultCharset) : null);
    }

    public String toString(HttpEntity entity, Charset defaultCharset) throws IOException,
            ParseException {
        Args.notNull(entity, "Entity");
        InputStream instream = entity.getContent();
        if (instream == null) {
            return null;
        } else {
            try {
                Args.check(entity.getContentLength() <= 2147483647L,
                        "HTTP entity too large to be buffered in memory");
                int i = (int) entity.getContentLength();
                if (i < 0) {
                    i = 4096;
                }

                Charset charset = null;
                if (charset == null) {
                    charset = defaultCharset;
                }

                if (charset == null) {
                    try {
                        ContentType contentType = ContentType.get(entity);
                        if (contentType != null) {
                            charset = contentType.getCharset();
                        }
                    } catch (UnsupportedCharsetException var13) {
                        if (defaultCharset == null) {
                            throw new UnsupportedEncodingException(var13.getMessage());
                        }
                    }
                }

                if (charset == null) {
                    charset = HTTP.DEF_CONTENT_CHARSET;
                }

                Reader reader = new InputStreamReader(instream, charset);
                CharArrayBuffer buffer = new CharArrayBuffer(i);
                char[] tmp = new char[1024];

                int l;
                while ((l = reader.read(tmp)) != -1) {
                    buffer.append(tmp, 0, l);
                }

                String var9 = buffer.toString();
                return var9;
            } finally {
                instream.close();
            }
        }
    }

    public String toString(HttpResponse response, String charset) throws Exception {
        InputStreamReader isr = new InputStreamReader(response.getEntity().getContent(), charset);

        String result;
        int tmp;
        for (result = ""; (tmp = isr.read()) != -1; result = result + (char) tmp) {
            ;
        }

        return result;
    }

//  static {
//    InputStream ins = HttpOutUtils.class.getClassLoader().getResourceAsStream("container.properties");
//    Properties props = new Properties();
//
//    try {
//      props.load(ins);
//      PROXY_GATEWAY = (String)props.get("http.proxy.gateway");
//    } catch (IOException var11) {
//      var11.printStackTrace();
//    } finally {
//      try {
//        if(ins != null) {
//          ins.close();
//        }
//      } catch (IOException var10) {
//        ;
//      }
//
//    }
//
//    if(StringUtils.isBlank(PROXY_GATEWAY)) {
//      PROXY_GATEWAY = "";
//    }
//
//  }
}
