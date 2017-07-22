package com.jjf.befree.core;

import com.jjf.befree.core.utils.HttpConstant;

import java.util.*;

/**
 * Created by jjf_lenovo on 2017/5/15.
 */
public class Site {
    private String domain;

//    private String url; //爬取的域名

    private String userAgent;

    private Map<String, String> defaultCookies = new LinkedHashMap<String, String>();

    private Map<String, Map<String, String>> cookies = new HashMap<String, Map<String, String>>();

    private String charset;

    private String encoding ="UTF-8";

    private int sleepTime = 2000; //休息时间

    private int retryNumber = 5; // 请求失败后的重试次数

    private int connectTimeout = 30000; // 链接建立的超时时间；

    private int socketTimeout = 30000;  //响应超时时间，超过此时间不再读取响应；

    private int connectionRequestTimeout = 30000;//http clilent中从connetcion pool中获得一个connection的超时时间

    private static final Set<Integer> DEFAULT_STATUS_CODE_SET = new HashSet<Integer>();

    private Set<Integer> acceptStatCode = DEFAULT_STATUS_CODE_SET;

    private Map<String, String> headers = new HashMap<String, String>(); //request header对象

    private boolean useGzip = true;   //使用gzip压缩

    private String proxyIp; //代理ip

    private int proxyPort=0; //代理端口

    boolean isIgnoreCer = true; //是否无视https证书

    static {
        DEFAULT_STATUS_CODE_SET.add(HttpConstant.StatusCode.CODE_200);
    }

    public static Site me() {
        return new Site();
    }

    public String getDomain() {
        return domain;
    }

    public Site setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public Site addCookie(String name, String value) {
        defaultCookies.put(name, value);
        return this;
    }

    public Site addCookie(String domain, String name, String value) {
        if (!cookies.containsKey(domain)){
            cookies.put(domain,new HashMap<String, String>());
        }
        cookies.get(domain).put(name, value);
        return this;
    }

    public Site setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    public Map<String, String> getCookies() {
        return defaultCookies;
    }

    public Map<String,Map<String, String>> getAllCookies() {
        return cookies;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public Site setCharset(String charset) {
        this.charset = charset;
        return this;
    }

    public String getCharset() {
        return charset;
    }

    public Site setAcceptStatCode(Set<Integer> acceptStatCode) {
        this.acceptStatCode = acceptStatCode;
        return this;
    }

    public Set<Integer> getAcceptStatCode() {
        return acceptStatCode;
    }

    public Site setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
        return this;
    }

    public int getSleepTime() {
        return sleepTime;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Site addHeader(String key, String value) {
        headers.put(key, value);
        return this;
    }

    public boolean isUseGzip() {
        return useGzip;
    }

    public Site setUseGzip(boolean useGzip) {
        this.useGzip = useGzip;
        return this;
    }

    public String getProxyIp() {
        return proxyIp;
    }

    public Site setProxyIp(String proxyIp) {
        this.proxyIp = proxyIp;
        return this;
    }

    public int getProxyPort() {
        return proxyPort;
    }

    public Site setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
        return this;
    }

    public boolean isIgnoreCer() {
        return isIgnoreCer;
    }

    public Site setIgnoreCer(boolean ignoreCer) {
        isIgnoreCer = ignoreCer;
        return this;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public Site setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public Site setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
        return this;
    }

    public int getConnectionRequestTimeout() {
        return connectionRequestTimeout;
    }

    public Site setConnectionRequestTimeout(int connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
        return this;
    }

    public int getRetryNumber() {
        return retryNumber;
    }

    public Site setRetryNumber(int retryNumber) {
        this.retryNumber = retryNumber;
        return this;
    }

    public String getEncoding() {
        return encoding;
    }

    public Site setEncoding(String encoding) {
        this.encoding = encoding;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Site site = (Site) o;
        if (domain != site.domain) return false;
        if (sleepTime != site.sleepTime) return false;
        if (proxyIp != site.proxyIp) return false;
        if (proxyPort != site.proxyPort) return false;
        if (isIgnoreCer != site.isIgnoreCer) return false;
        if (sleepTime != site.sleepTime) return false;
        if (encoding != site.encoding) return false;
        if (connectTimeout != site.connectTimeout) return false;
        if (socketTimeout != site.socketTimeout) return false;
        if (retryNumber != site.retryNumber) return false;
        if (connectionRequestTimeout != site.connectionRequestTimeout) return false;
        if (acceptStatCode != null ? !acceptStatCode.equals(site.acceptStatCode) : site.acceptStatCode != null)
            return false;
        if (charset != null ? !charset.equals(site.charset) : site.charset != null) return false;
        if (defaultCookies != null ? !defaultCookies.equals(site.defaultCookies) : site.defaultCookies != null)
            return false;
        if (headers != null ? !headers.equals(site.headers) : site.headers != null) return false;
      return userAgent != null ? userAgent.equals(site.userAgent) : site.userAgent == null;
    }

    @Override
    public int hashCode() {
        int result = domain != null ? domain.hashCode() : 0;
        result = 31 * result + (userAgent != null ? userAgent.hashCode() : 0);
        result = 31 * result + (defaultCookies != null ? defaultCookies.hashCode() : 0);
        result = 31 * result + (charset != null ? charset.hashCode() : 0);
        result = 31 * result + sleepTime;
        result = 31 * result + proxyIp !=null? proxyIp.hashCode():0;
        result = 31 * result + retryNumber;
        result = 31 * result + encoding.hashCode();
        result = 31 * result + proxyPort;
        result = 31 * result + (acceptStatCode != null ? acceptStatCode.hashCode() : 0);
        result = 31 * result + (headers != null ? headers.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Site{" +
                ", domain='" + domain + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", cookies=" + defaultCookies +
                ", charset='" + charset + '\'' +
                ", sleepTime=" + sleepTime +
                ", connectTimeout=" + connectTimeout +
                ", socketTimeout=" + socketTimeout +
                ", connectionRequestTimeout=" + connectionRequestTimeout +
                ", encoding=" + encoding +
                ", proxyIp=" + proxyIp +
                ", proxyPort=" + proxyPort +
                ", retryNumber=" + retryNumber +
                ", acceptStatCode=" + acceptStatCode +
                ", headers=" + headers +
                ", isIgnoreCer=" + isIgnoreCer +
                '}';
    }
}
