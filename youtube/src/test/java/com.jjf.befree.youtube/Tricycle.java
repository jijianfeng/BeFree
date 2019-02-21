package com.jjf.befree.youtube;

import com.jjf.befree.core.client.HttpClientManager;
import com.jjf.befree.core.client.HttpClientRequest;
import com.jjf.befree.core.utils.XPathUtil;
import org.apache.http.client.HttpClient;
import org.jsoup.nodes.Element;

import java.io.*;
import java.net.URL;
import java.util.List;

/**
 * 爬取工信部发布的允许上牌的电动三轮车
 */
public class Tricycle {

    private static String dormain = "http://data.miit.gov.cn";

    private static String URL = "http://data.miit.gov.cn/resultSearch?searchType=advancedSearch&categoryTreeId=1128" +
            "&scol_Cpmc=%E7%94%B5%E5%8A%A8%E6%AD%A3%E4%B8%89%E8%BD%AE%E6%91%A9%E6%89%98%E8%BD%A6" +
            "&scol_Cpxh=&scol_Cpsb=&scol_Qymc=&pagenow=";

    //电动车列表
    private static String TABLE_XPATH = "//*[@id=\"page-wrapper\"]/div[2]/table";

    //表格start
    private static String TABLE_START = "//*[@id=\"page-wrapper\"]/div[2]/table/tbody/tr[";
    //公司名称
    private static String COMPANY_NAME_END = "]/td[2]/a";
    //产品型号
    private static String SIZE_NAME_END = "]/td[5]/a";
    //图片
    private static String PIC_URL = "//*[@id=\"w_table\"]/tr[4]";

    public static void main(String[] args) throws Exception {
        for (int page = 13; page <= 14; page++) {
            String target = URL + page;
            HttpClient client = HttpClientManager.getHttpClient();
            String html = HttpClientRequest.doGet(client, target, "gb2312").html();
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            List<Element> table = XPathUtil.getXPathElementResult(html, TABLE_XPATH);
            long count = table.get(0).getElementsByTag("tr").size();//行数
            System.out.println("本页有" + (--count));
            for (int i = 1; i <= count; i++) {
                Element companyNameElement = XPathUtil.getXPathElementResult(html, TABLE_START + i + COMPANY_NAME_END).get(0);
                String companyName = companyNameElement.html();
                String sizeName = XPathUtil.getXPathElementResult(html, TABLE_START + i + SIZE_NAME_END).get(0).html();
                String href = dormain + companyNameElement.attr("href");
                System.out.println(i + " " + companyName + " " + sizeName + " " + href);
                //文件夹
                File dir = new File("/Users/Downloads/temp/" + companyName);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                //获取详情页面
                String detailHtml = HttpClientRequest.doGet(client, href, "gb2312").html();
                String picUrl = dormain + XPathUtil.getXPathElementResult(detailHtml, "//*[@class=\"w_table\"]").get(0).getElementsByTag("img").attr("src");
                downloadPic("/Users/Downloads/temp/" + companyName + "/" + sizeName + ".jpg", picUrl);
            }
        }
    }

    private static void downloadPic(String fileName, String imageUrl) throws Exception {
        OutputStream os = null;
        InputStream is = null;
        try {
            File file = new File(fileName);
            os = new FileOutputStream(file);
            java.net.URL url = new URL(imageUrl);
            is = url.openStream();
            byte[] buff = new byte[1024];
            while (true) {
                int readed = is.read(buff);
                if (readed == -1) {
                    break;
                }
                byte[] temp = new byte[readed];
                System.arraycopy(buff, 0, temp, 0, readed);
                os.write(temp);
            }
        } finally {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }
        }
    }
}
