package com.jjf.befree.crawler.download;

import com.jjf.befree.crawler.Page;
import com.jjf.befree.crawler.Site;
import com.jjf.befree.crawler.Task;
import com.jjf.befree.crawler.client.HttpClientManager;
import com.jjf.befree.crawler.client.HttpClientRequest;
import com.jjf.befree.crawler.download.entity.Download;
import com.jjf.befree.crawler.utils.FormatResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.log4j.Logger;

/**
 * Created by jjf_lenovo on 2017/5/13.
 */
public class DownloadFromYoutube implements Download {

    static Logger log = Logger.getLogger(DownloadFromYoutube.class);

    public Page download(Task task) {
        Site site = task.getSite();
        HttpClient httpClient = HttpClientManager.getHttpClinet(site);
        int retryNumber = site.getRetryNumber(); //重试次数
        long sleepTime = site.getSleepTime();  //重试间隔的休息时间
        for(;;){
            try {
                HttpResponse response = HttpClientRequest.doGetToResponse(httpClient,site.getDomain(),site.getEncoding());
                return new Page(FormatResponse.formatResponse(response,site.getEncoding())
                        ,site.getDomain(),
                        response.getStatusLine().getStatusCode());
            } catch (ClientProtocolException e) {
                if(retryNumber==0){
                    log.error(e.getMessage()+"give up retry");
                    break;
                }
                retryNumber--;
                log.info(e.getMessage()+"retry");
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e1) {
                    log.error(e.getMessage());
                }
            } catch (Exception e){
                //TODO 这里需要自定义formate异常
                log.error("page formate error"+e.getMessage());
                break;
            }
        }
        return null;
    }
}
