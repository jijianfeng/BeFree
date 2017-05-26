package com.jjf.befree.crawler.client;

/**
 * Created by jjf_lenovo on 2017/5/12.
 */

import com.jjf.befree.crawler.utils.FormatResponse;
import com.jjf.befree.crawler.utils.HttpUserAgent;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientRequest {
    private static final int ERROR_CODE = 1;

    /**
     * get方式提交数据
     */
    public static Document doGet(HttpClient client,String url,String encoding) throws Exception{
        //System.out.println("doGet中使用代理："+proxyIp+":"+proxyPort);
//        HttpClient client = HttpConnectionManager.getHttpClient();//getHttpClientWithProxy(proxyIp,proxyPort);
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Accept-Language", "zh-cn,zh;q=0.5");
        httpGet.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
        httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//        httpGet.setHeader("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate");
        httpGet.setHeader("User-Agent", HttpUserAgent.get());
        try{
            //执行
            HttpResponse response = client.execute(httpGet);
            int statuCode = response.getStatusLine().getStatusCode();
            if(statuCode == 200){
                String html = FormatResponse.formatResponse(response,encoding);

                if(html != null){
                    return Jsoup.parse(html);
                }
                return null;

            } else {
                throw new HttpResponseException(statuCode,"请求URL【"+url+"】，"+statuCode+"错误");
            }
        } catch (ClientProtocolException e){
            throw new ClientProtocolException("发起链接异常");
        } finally {
            if(httpGet != null){
                httpGet.abort();
            }
        }
    }

    /**
     * get方式提交数据
     */
    public static HttpResponse doGetToResponse(HttpClient client,String url,String encoding) throws ClientProtocolException{
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Accept-Language", "zh-cn,zh;q=0.5");
        httpGet.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
        httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate");
        httpGet.setHeader("User-Agent", HttpUserAgent.get());
        try{
            //执行
            HttpResponse response = client.execute(httpGet);
            return response;
        } catch (IOException e){
            if(httpGet != null){
                httpGet.abort();
            }
            throw new ClientProtocolException("发起链接异常");
        }
    }

    /**
     * post方式提交
     * @throws Exception
     */
    public static Document doPost(HttpClient client,String url, Map<String,String> paramaters,String encoding) throws Exception{

//        HttpClient client = HttpConnectionManager.getHttpClient();

        HttpPost request = new HttpPost(url);

        request.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
        request.setHeader("Accept-Encoding", "gzip, deflate");
        request.setHeader("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
        request.setHeader("Cache-Control", "no-cache");
        request.setHeader("Connection", "keep-alive");
        request.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

        // 创建名/值组列表
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        for(String key : paramaters.keySet()){
            parameters.add(new BasicNameValuePair(key, paramaters.get(key)));
        }

        try {
            // 创建UrlEncodedFormEntity对象
            UrlEncodedFormEntity formEntiry = new UrlEncodedFormEntity(parameters);
            request.setEntity(formEntiry);

            // 执行请求
            HttpResponse response = client.execute(request);
            int statuCode = response.getStatusLine().getStatusCode();

            if (statuCode == 200) {
                String html = FormatResponse.formatResponse(response,encoding);

                if(html != null){
                    return Jsoup.parse(html);
                }

                return null;

            } else if (statuCode == 404) {
                throw new Exception(ERROR_CODE+"请求URL【"+url+"】，404错误");
            }
        } catch (Exception e) {

            throw new Exception(ERROR_CODE+ e.getMessage());

        } finally {
            if(request != null){
                request.abort();
            }
        }
        return null;
    }

//    /**
//     * 格式化请求结果
//     * @throws Exception
//     */
//    private static String formatResponse(HttpResponse response,String encoding) throws Exception {
//
//        ByteArrayInputStream bis = null;
//        try{
//            Header contentEncoding = response.getFirstHeader("Content-Encoding");
//
//            if(contentEncoding == null){
//                return EntityUtils.toString(response.getEntity(),encoding);
//            } else {
//
//                String charset = "utf-8";
//                Header contentType = response.getFirstHeader("Content-Type");
//
//                if(contentType != null){
//                    String contentTypeStr = contentType.getValue();
//                    if(contentTypeStr != null && !"".equals(contentTypeStr)){
//                        charset = contentTypeStr.substring(contentTypeStr.indexOf("=") + 1,contentTypeStr.length());
//
//                    }
//                }
//
//                String contentEncodingType = contentEncoding.getValue();
//                if(contentEncodingType.equalsIgnoreCase("gzip")){
//                    if(response.toString().contains("soufun"))
//                        charset = "gb2312";
//
//                    byte[] bytes = IOUtils.toByteArray(response.getEntity().getContent());
//                    bis = new ByteArrayInputStream(bytes);
//
//                    return uncompress(bis ,charset);
//                }
//
//            }
//
//        } catch(Exception e) {
//            throw new Exception(ERROR_CODE+"格式化HttpResponse出错");
//        } finally {
//            if(bis != null){
//                try {
//                    bis.close();
//                } catch (IOException e) {
//                    throw new Exception(ERROR_CODE+"格式化HttpResponse出错");
//                }
//            }
//        }
//
//        return null;
//    }
//
//
//    /**
//     * GZIP解压
//     */
//    private static String uncompress(ByteArrayInputStream in, String charset) {
//
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//
//        try {
//            GZIPInputStream gunzip = new GZIPInputStream(in);
//            byte[] buffer = new byte[256];
//            int n;
//            while((n = gunzip.read(buffer)) >=0 ){
//                out.write(buffer, 0, n);
//            }
//            return out.toString(charset);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


    /**
     * get方式提交数据
     */
    public static int doGetToGetResponseCode(HttpClient client,String url,String encoding) throws Exception{
        //System.out.println("doGet中使用代理："+proxyIp+":"+proxyPort);
//        HttpClient client = HttpConnectionManager.getHttpClient();//getHttpClientWithProxy(proxyIp,proxyPort);
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Accept-Language", "zh-cn,zh;q=0.5");
        httpGet.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
        httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate");
//        httpGet.setHeader("User-Agent", HttpUserAgent.get());
        try{
            //执行
            HttpResponse response = client.execute(httpGet);
            int statuCode = response.getStatusLine().getStatusCode();
            return statuCode;
        } catch (ClientProtocolException e){
            throw new ClientProtocolException("发起链接异常");
        } finally {
            if(httpGet != null){
                httpGet.abort();
            }
        }
    }
}
