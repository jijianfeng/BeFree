package com.jjf.befree.core.queue;

import com.alibaba.fastjson.JSONObject;
import com.jjf.befree.core.Task;
import com.jjf.befree.core.enums.TaskStatus;
import com.jjf.befree.core.queue.entity.UrlQueue;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;

/**
 * Created by jjf_lenovo on 2017/5/24.
 */
public class RedisQueue implements UrlQueue {
    private String host = "";
    private int port = 6379;
    private String password = "";
    private String quueName = "RedisQueue";
    private JedisPool wpool = null;
    static Logger log = Logger.getLogger(RedisQueue.class);

    public void refresh(){
        if (wpool!=null){
            this.wpool.destroy();
            this.wpool = null;
        }
    }

    public JedisPool getWritePool() {
        if (wpool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(500);
            config.setMaxIdle(200);
            config.setMinIdle(100);
            config.setMaxWaitMillis(1000 * 100);
            if (StringUtils.isNotBlank(password)){
                wpool = new JedisPool(config, this.host, this.port, 0, password);
            }else {
                wpool = new JedisPool(config, this.host, this.port, 0);
            }
        }
        return wpool;
    }

    public Jedis getWClient() {
        return getWritePool().getResource();
    }

    /**
     * 要有一定权重关系，比如评分、时间等
     * @param crawlerName
     * @return
     */
    @Override
    public Task bPop(String crawlerName) {
        Jedis jedis = null;
        Task task = null;
        try {
            jedis = getWClient();
            List<String> res = jedis.brpop(0,quueName+crawlerName);
            task = JSONObject.parseObject(res.get(1),Task.class);
        }catch (Exception e){
            log.warn(e.getMessage());
        }finally {
            if (jedis!=null){
                jedis.close();
            }
        }
        return task;
    }

    @Override
    public boolean push(Task task) {
        Jedis jedis = null;
        boolean res = false;
        try {
            jedis = getWClient();
            res = jedis.lpush(quueName+task.getName(),JSONObject.toJSONString(task))>0;
        }catch (Exception e){
            log.warn(e.getMessage());
        }finally {
            if (jedis!=null){
                jedis.close();
            }
        }
        return res;
    }

    @Override
    public long len(String crawlerName) {
        long len = 0;
        Jedis jedis = null;
        try {
            jedis = getWClient();
            len = jedis.llen(quueName+crawlerName);
        }catch (Exception e){
            log.warn(e.getMessage());
        }finally {
            if (jedis!=null){
                jedis.close();
            }
        }
        return len;
    }

    @Override
    public int getStatus(Task task){
        return 1;
    }

    @Override
    public void addStatus(Task task,TaskStatus status) {
        log.info("");
    }

    @Override
    public long totalCrawled(String crawlerName) {
        return -1;
    }

    /**
     * Pipeline异步提交，大幅度提升task数量很多时处理的速度
     * @param tasks
     * @return
     */
    public boolean pushWithPipeline(List<Task> tasks){
        //
        return true;
    }

    /**
     * Scan简单遍历，适用任务无差别
     * @param crawlerName 爬虫名
     * @param cursor Scan 返回的数字，第一次为0
     * @param limit 一次返回的数量，并不保证相等
     * @return
     */
    public List<Task> popWithScan(String crawlerName,String cursor,int limit){
        //
        return null;
    }

//    public String getHost() {
//        return host;
//    }
//
//    public void setHost(String host) {
//        this.host = host;
//    }
//
//    public int getPort() {
//        return port;
//    }
//
//    public void setPort(int port) {
//        this.port = port;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getQuueName() {
//        return quueName;
//    }
//
//    public void setQuueName(String quueName) {
//        this.quueName = quueName;
//    }
}
