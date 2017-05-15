package com.jjf.befree.crawler.utils;

/**
 * Created by jjf_lenovo on 2017/5/13.
 */
// https://dzone.com/articles/url-encoding-and-decoding-using-java
//对URL信息的解密
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class URLEncodeDecode {
    public static void main(String[] args) {
        String url = "quality=hd720\\u0026type=video%2Fmp4%3B+codecs%3D%22avc1.64001F%2C+mp4a.40.2%22\\u0026url=https%3A%2F%2Fr2---sn-i3b7kn7r.googlevideo.com%2Fvideoplayback%3Fmm%3D31%26sparams%3Ddur%252Cei%252Cid%252Cip%252Cipbits%252Citag%252Clmt%252Cmime%252Cmm%252Cmn%252Cms%252Cmv%252Cpl%252Cratebypass%252Crequiressl%252Csource%252Cupn%252Cexpire%26mn%3Dsn-i3b7kn7r%26ipbits%3D0%26ratebypass%3Dyes%26signature%3DD44BDBF9840CE8A8D4A7DFD299CB5D66CFF69567.9D2F356E80D1238EC4002006BE9A19822A85C2EC%26requiressl%3Dyes%26mt%3D1494763584%26upn%3DrAp9x8Nv6jE%26mv%3Du%26ei%3DvkgYWZy6KY3i4QKEuIHQDQ%26ms%3Dau%26key%3Dyt6%26ip%3D112.10.181.235%26lmt%3D1494758898664014%26dur%3D2865.853%26pl%3D24%26itag%3D22%26expire%3D1494785310%26mime%3Dvideo%252Fmp4%26id%3Do-ALSopL4r31jAENfxCYvlluOh0o4pMpVl1RwQT5wwc7lL%26source%3Dyoutube\\u0026itag=22,quality=medium\\u0026type=video%2Fmp4%3B+codecs%3D%22avc1.42001E%2C+mp4a.40.2%22\\u0026url=https%3A%2F%2Fr2---sn-i3b7kn7r.googlevideo.com%2Fvideoplayback%3Fsparams%3Dclen%252Cdur%252Cei%252Cgir%252Cid%252Cip%252Cipbits%252Citag%252Clmt%252Cmime%252Cmm%252Cmn%252Cms%252Cmv%252Cpl%252Cratebypass%252Crequiressl%252Csource%252Cupn%252Cexpire%26clen%3D149708916%26ipbits%3D0%26signature%3D40AB56BB09E845CEE803F1305CF0D5948E4185F6.3B4616D82E4C20BCDD409592008B555392B348B8%26upn%3DrAp9x8Nv6jE%26key%3Dyt6%26ip%3D112.10.181.235%26lmt%3D1494758930247403%26dur%3D2865.853%26expire%3D1494785310%26id%3Do-ALSopL4r31jAENfxCYvlluOh0o4pMpVl1RwQT5wwc7lL%26source%3Dyoutube%26mm%3D31%26mn%3Dsn-i3b7kn7r%26gir%3Dyes%26ratebypass%3Dyes%26requiressl%3Dyes%26mt%3D1494763584%26mv%3Du%26ei%3DvkgYWZy6KY3i4QKEuIHQDQ%26ms%3Dau%26pl%3D24%26itag%3D18%26mime%3Dvideo%252Fmp4\\u0026itag=18,quality=small\\u0026type=video%2F3gpp%3B+codecs%3D%22mp4v.20.3%2C+mp4a.40.2%22\\u0026url=https%3A%2F%2Fr2---sn-i3b7kn7r.googlevideo.com%2Fvideoplayback%3Fsparams%3Dclen%252Cdur%252Cei%252Cgir%252Cid%252Cip%252Cipbits%252Citag%252Clmt%252Cmime%252Cmm%252Cmn%252Cms%252Cmv%252Cpl%252Crequiressl%252Csource%252Cupn%252Cexpire%26clen%3D96655131%26ipbits%3D0%26signature%3D13FB1C0E5DC7F8639A4F1A178B795D0C0E3EB980.195B8D4D4B60A8C30199FCFCC60279468CDB4DB5%26upn%3DrAp9x8Nv6jE%26key%3Dyt6%26ip%3D112.10.181.235%26lmt%3D1494758887516637%26dur%3D2865.899%26expire%3D1494785310%26id%3Do-ALSopL4r31jAENfxCYvlluOh0o4pMpVl1RwQT5wwc7lL%26source%3Dyoutube%26mm%3D31%26mn%3Dsn-i3b7kn7r%26gir%3Dyes%26requiressl%3Dyes%26mt%3D1494763584%26mv%3Du%26ei%3DvkgYWZy6KY3i4QKEuIHQDQ%26ms%3Dau%26pl%3D24%26itag%3D36%26mime%3Dvideo%252F3gpp\\u0026itag=36,quality=small\\u0026type=video%2F3gpp%3B+codecs%3D%22mp4v.20.3%2C+mp4a.40.2%22\\u0026url=https%3A%2F%2Fr2---sn-i3b7kn7r.googlevideo.com%2Fvideoplayback%3Fsparams%3Dclen%252Cdur%252Cei%252Cgir%252Cid%252Cip%252Cipbits%252Citag%252Clmt%252Cmime%252Cmm%252Cmn%252Cms%252Cmv%252Cpl%252Crequiressl%252Csource%252Cupn%252Cexpire%26clen%3D29888846%26ipbits%3D0%26signature%3D8EC6ADE92A558273C5DEE4773254AA78A7B61DFF.2145CC62CBC5D9CA56812471CD21F5EBBBFED7B5%26upn%3DrAp9x8Nv6jE%26key%3Dyt6%26ip%3D112.10.181.235%26lmt%3D1494758876581100%26dur%3D2865.899%26expire%3D1494785310%26id%3Do-ALSopL4r31jAENfxCYvlluOh0o4pMpVl1RwQT5wwc7lL%26source%3Dyoutube%26mm%3D31%26mn%3Dsn-i3b7kn7r%26gir%3Dyes%26requiressl%3Dyes%26mt%3D1494763584%26mv%3Du%26ei%3DvkgYWZy6KY3i4QKEuIHQDQ%26ms%3Dau%26pl%3D24%26itag%3D17%26mime%3Dvideo%252F3gpp\\u0026itag=17";
        String url2 = ";";
        String decodeURL = decode(url);
        System.out.println("Decoded URL: "+decodeURL);
        String encodeURL = encode(url2);
        System.out.println(encodeURL);
    }
    public static String decode(String url)
    {
        try {
            String prevURL="";
            String decodeURL=url;
            while(!prevURL.equals(decodeURL))
            {
                prevURL=decodeURL;
                decodeURL=URLDecoder.decode( decodeURL, "UTF-8" );
            }
            return decodeURL;
        } catch (UnsupportedEncodingException e) {
            return "Issue while decoding" +e.getMessage();
        }
    }
    public static String encode(String url)
    {
        try {
            String encodeURL=URLEncoder.encode( url, "UTF-8" );
            return encodeURL;
        } catch (UnsupportedEncodingException e) {
            return "Issue while encoding" +e.getMessage();
        }
    }
}