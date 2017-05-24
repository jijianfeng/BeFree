# BeFree
大概就是建立一个分布式平台爬取YouTube（目前）上的一些热门内容到一些大陆能访问的网站，初步应该就是微博+bilibili+公众号吧

第一次设计系统，一步步来吧，目前打算用java写

运行环境为 单核1G内存*2 + 随时可能开关机的我的笔记本

1.实现在境内centos环境下的翻墙,毕竟境外服务器价格....，
  
2.对youtube真实视频地址，视频评级、关键词等信息的解析

3.合适的“url仓库”,特征: ①支持优先级出库 ②.数据唯一且能过滤已爬取url（布隆过滤器Bloom Filter算法）
③入库速度远大于出库速度，支持落盘④线程安全⑤轻量级  Redis吧....

4.制作基于种子的宽度优先的爬虫，目标支持 1.单个人物视频列表 2.播放列表

5.制作带偏好的学习型爬虫，目标：热门视频

6.内容的发布，还得多注册几个微博啊 ╮(╯_╰)╭

7.使用zookeeper来监控我的爬虫，允许slave宕机重连，（这样就可以拿我的笔记本和宿舍宽带来缓解下载上传的压力）

8.维护的web端

感谢下面的开源项目
https://github.com/XX-net/XX-Net

https://github.com/code4craft/webmagic

https://github.com/zhegexiaohuozi/SeimiCrawler