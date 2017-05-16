package com.jjf.befree.crawler;

import com.jjf.befree.crawler.utils.HttpConstant;

import java.util.*;

/**
 * Created by jjf_lenovo on 2017/5/15.
 */
public class Site {
    private String domain; //域名，我的应用简单，就当url使

    private String userAgent;

    private Map<String, String> defaultCookies = new LinkedHashMap<String, String>();

    private Map<String, Map<String, String>> cookies = new HashMap<String, Map<String, String>>();

    private String charset;

    private int sleepTime = 20000;

    private int connectTimeout = 30000; // 链接建立的超时时间；

    private int socketTimeout = 30000;  //响应超时时间，超过此时间不再读取响应；

    private int connectionRequestTimeout = 30000;//http clilent中从connetcion pool中获得一个connection的超时时间

    private static final Set<Integer> DEFAULT_STATUS_CODE_SET = new HashSet<Integer>();

    private Set<Integer> acceptStatCode = DEFAULT_STATUS_CODE_SET;

    private Map<String, String> headers = new HashMap<String, String>();

    private boolean useGzip = true;

    private String proxyIp;

    private int proxyPort=0;

    boolean isIgnoreCer = true;

    static {
        DEFAULT_STATUS_CODE_SET.add(HttpConstant.StatusCode.CODE_200);
    }

    /**
     * new a Site
     *
     * @return new site
     */
    public static Site me() {
        return new Site();
    }

    /**
     * Add a cookie with domain {@link #getDomain()}
     *
     * @param name name
     * @param value value
     * @return this
     */
    public Site addCookie(String name, String value) {
        defaultCookies.put(name, value);
        return this;
    }

    /**
     * Add a cookie with specific domain.
     *
     * @param domain domain
     * @param name name
     * @param value value
     * @return this
     */
    public Site addCookie(String domain, String name, String value) {
        if (!cookies.containsKey(domain)){
            cookies.put(domain,new HashMap<String, String>());
        }
        cookies.get(domain).put(name, value);
        return this;
    }

    /**
     * set user agent
     *
     * @param userAgent userAgent
     * @return this
     */
    public Site setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    /**
     * get cookies
     *
     * @return get cookies
     */
    public Map<String, String> getCookies() {
        return defaultCookies;
    }

    /**
     * get cookies of all domains
     *
     * @return get cookies
     */
    public Map<String,Map<String, String>> getAllCookies() {
        return cookies;
    }

    /**
     * get user agent
     *
     * @return user agent
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * get domain
     *
     * @return get domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * set the domain of site.
     *
     * @param domain domain
     * @return this
     */
    public Site setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    /**
     * Set charset of page manually.<br>
     * When charset is not set or set to null, it can be auto detected by Http header.
     *
     * @param charset charset
     * @return this
     */
    public Site setCharset(String charset) {
        this.charset = charset;
        return this;
    }

    /**
     * get charset set manually
     *
     * @return charset
     */
    public String getCharset() {
        return charset;
    }

    /**
     * Set acceptStatCode.<br>
     * When status code of http response is in acceptStatCodes, it will be processed.<br>
     * {200} by default.<br>
     * It is not necessarily to be set.<br>
     *
     * @param acceptStatCode acceptStatCode
     * @return this
     */
    public Site setAcceptStatCode(Set<Integer> acceptStatCode) {
        this.acceptStatCode = acceptStatCode;
        return this;
    }

    /**
     * get acceptStatCode
     *
     * @return acceptStatCode
     */
    public Set<Integer> getAcceptStatCode() {
        return acceptStatCode;
    }

    /**
     * Set the interval between the processing of two pages.<br>
     * Time unit is micro seconds.<br>
     *
     * @param sleepTime sleepTime
     * @return this
     */
    public Site setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
        return this;
    }

    /**
     * Get the interval between the processing of two pages.<br>
     * Time unit is micro seconds.<br>
     *
     * @return the interval between the processing of two pages,
     */
    public int getSleepTime() {
        return sleepTime;
    }

    /**
     * Get retry times immediately when download fail, 0 by default.<br>
     *
     * @return retry times when download fail
     */

    public Map<String, String> getHeaders() {
        return headers;
    }

    /**
     * Put an Http header for downloader. <br>
     * Use {@link #addCookie(String, String)} for cookie and {@link #setUserAgent(String)} for user-agent. <br>
     *
     * @param key   key of http header, there are some keys constant in {@link HttpConstant.Header}
     * @param value value of header
     * @return this
     */
    public Site addHeader(String key, String value) {
        headers.put(key, value);
        return this;
    }

    public boolean isUseGzip() {
        return useGzip;
    }

    /**
     * Whether use gzip. <br>
     * Default is true, you can set it to false to disable gzip.
     *
     * @param useGzip useGzip
     * @return this
     */
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

    public Task toTask() {
        return new Task() {
            @Override
            public String getUUID() {
                String uuid = Site.this.getDomain();
                if (uuid == null) {
                    uuid = UUID.randomUUID().toString();
                }
                return uuid;
            }

            @Override
            public Site getSite() {
                return Site.this;
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Site site = (Site) o;
        if (sleepTime != site.sleepTime) return false;
        if (proxyIp != site.proxyIp) return false;
        if (proxyPort != site.proxyPort) return false;
        if (isIgnoreCer != site.isIgnoreCer) return false;
        if (sleepTime != site.sleepTime) return false;
        if (connectTimeout != site.connectTimeout) return false;
        if (socketTimeout != site.socketTimeout) return false;
        if (connectionRequestTimeout != site.connectionRequestTimeout) return false;
        if (acceptStatCode != null ? !acceptStatCode.equals(site.acceptStatCode) : site.acceptStatCode != null)
            return false;
        if (charset != null ? !charset.equals(site.charset) : site.charset != null) return false;
        if (defaultCookies != null ? !defaultCookies.equals(site.defaultCookies) : site.defaultCookies != null)
            return false;
        if (domain != null ? !domain.equals(site.domain) : site.domain != null) return false;
        if (headers != null ? !headers.equals(site.headers) : site.headers != null) return false;
        if (userAgent != null ? !userAgent.equals(site.userAgent) : site.userAgent != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = domain != null ? domain.hashCode() : 0;
        result = 31 * result + (userAgent != null ? userAgent.hashCode() : 0);
        result = 31 * result + (defaultCookies != null ? defaultCookies.hashCode() : 0);
        result = 31 * result + (charset != null ? charset.hashCode() : 0);
        result = 31 * result + sleepTime;
        result = 31 * result + proxyIp !=null? proxyIp.hashCode():0;
        result = 31 * result + proxyPort;
        result = 31 * result + (acceptStatCode != null ? acceptStatCode.hashCode() : 0);
        result = 31 * result + (headers != null ? headers.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Site{" +
                "domain='" + domain + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", cookies=" + defaultCookies +
                ", charset='" + charset + '\'' +
                ", sleepTime=" + sleepTime +
                ", connectTimeout=" + connectTimeout +
                ", socketTimeout=" + socketTimeout +
                ", connectionRequestTimeout=" + connectionRequestTimeout +
                ", proxyIp=" + proxyIp +
                ", proxyPort=" + proxyPort +
                ", acceptStatCode=" + acceptStatCode +
                ", headers=" + headers +
                ", isIgnoreCer=" + isIgnoreCer +
                '}';
    }
}
