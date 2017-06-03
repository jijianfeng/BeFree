package com.jjf.befree.crawler.download;

import com.jjf.befree.crawler.Page;
import com.jjf.befree.crawler.Site;
import com.jjf.befree.crawler.Task;
import com.jjf.befree.crawler.client.HttpClientManager;
import com.jjf.befree.crawler.client.HttpClientRequest;
import com.jjf.befree.crawler.exception.FormateResponseException;
import com.jjf.befree.crawler.utils.FormatResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.log4j.Logger;

/**
 * Created by jjf_lenovo on 2017/5/13.
 */
public class Download {

    static Logger log = Logger.getLogger(Download.class);

    /**
     * 根据任务下载页面，可能对页面有一定的要求，动态页面什么的，考虑扩展
     *
     * @param task task
     * @return page
     */
    public static Page download(Task task){
        Site site = task.getSite();
        HttpClient httpClient = HttpClientManager.getHttpClinet(site);
        int retryNumber = site.getRetryNumber(); //重试次数
        long sleepTime = site.getSleepTime();  //重试间隔的休息时间
        for(;;){
            try {
                HttpResponse response = HttpClientRequest.doGetToResponse(httpClient,task.getUrl(),site.getEncoding());
                return new Page(FormatResponse.formatResponse(response,site.getEncoding())
                        ,task.getUrl(),
                        response.getStatusLine().getStatusCode());
            } catch (ClientProtocolException e) {
                if(retryNumber==0){
                    log.error("give up retry "+e.getMessage());
                    break;
                }
                retryNumber--;
                log.info("retry "+e.getMessage());
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e1) {
                    log.error(e.getMessage());
                    break;
                }
            } catch (FormateResponseException e){
                log.error("page formate error "+e.getMessage());
                break;
            }
        }
        return null;
    };

}
