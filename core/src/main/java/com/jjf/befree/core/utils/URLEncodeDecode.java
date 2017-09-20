package com.jjf.befree.core.utils;

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
    String
        url =
        "adaptive_fmts\": \"type=video%2Fmp4%3B+codecs%3D%22avc1.4d401e%22&lmt=1491024171732779&quality_label=480p&index=715-1874&size=854x468&clen=4259414&init=0-714&itag=135&fps=25&projection_type=1&xtags=&url=https%3A%2F%2Fr1---sn-i3b7knl6.googlevideo.com%2Fvideoplayback%3Fupn%3DpKR3GPF2ZPU%26lmt%3D1491024171732779%26source%3Dyoutube%26clen%3D4259414%26key%3Dyt6%26ip%3D112.10.181.235%26keepalive%3Dyes%26mm%3D31%26mn%3Dsn-i3b7knl6%26mt%3D1494851290%26pl%3D24%26mv%3Du%26id%3Do-AFaPANl8Z--gvQhK5oOC24vdsf-5Iobat-yMTGqFHa0X%26ms%3Dau%26signature%3DB0204745D768865E3B8B06CB3C06AAD260BD148C.817A77B05EB6A8FD233649510ACD277F464700CD%26ipbits%3D0%26sparams%3Dclen%252Cdur%252Cei%252Cgir%252Cid%252Cip%252Cipbits%252Citag%252Ckeepalive%252Clmt%252Cmime%252Cmm%252Cmn%252Cms%252Cmv%252Cpl%252Crequiressl%252Csource%252Cupn%252Cexpire%26requiressl%3Dyes%26ei%3DPJ8ZWf7OGoayoQPizoPQAQ%26dur%3D477.720%26gir%3Dyes%26mime%3Dvideo%252Fmp4%26itag%3D135%26expire%3D1494872988&bitrate=209606,type=video%2Fwebm%3B+codecs%3D%22vp9%22&lmt=1491014540090871&quality_label=480p&index=243-1828&size=854x468&clen=3288613&init=0-242&itag=244&fps=25&projection_type=1&xtags=&url=https%3A%2F%2Fr1---sn-i3b7knl6.googlevideo.com%2Fvideoplayback%3Fupn%3DpKR3GPF2ZPU%26lmt%3D1491014540090871%26source%3Dyoutube%26clen%3D3288613%26key%3Dyt6%26ip%3D112.10.181.235%26keepalive%3Dyes%26mm%3D31%26mn%3Dsn-i3b7knl6%26mt%3D1494851290%26pl%3D24%26mv%3Du%26id%3Do-AFaPANl8Z--gvQhK5oOC24vdsf-5Iobat-yMTGqFHa0X%26ms%3Dau%26signature%3D7C075343A8BD671F50F5A86FA9B59CC5870953E7.C8EDA585E1F728FBB7F4C8D0CA6AA9093492442F%26ipbits%3D0%26sparams%3Dclen%252Cdur%252Cei%252Cgir%252Cid%252Cip%252Cipbits%252Citag%252Ckeepalive%252Clmt%252Cmime%252Cmm%252Cmn%252Cms%252Cmv%252Cpl%252Crequiressl%252Csource%252Cupn%252Cexpire%26requiressl%3Dyes%26ei%3DPJ8ZWf7OGoayoQPizoPQAQ%26dur%3D477.680%26gir%3Dyes%26mime%3Dvideo%252Fwebm%26itag%3D244%26expire%3D1494872988&bitrate=157582,type=video%2Fmp4%3B+codecs%3D%22avc1.4d401e%22&lmt=1491024171635961&quality_label=360p&index=715-1874&size=640x350&clen=2738785&init=0-714&itag=134&fps=25&projection_type=1&xtags=&url=https%3A%2F%2Fr1---sn-i3b7knl6.googlevideo.com%2Fvideoplayback%3Fupn%3DpKR3GPF2ZPU%26lmt%3D1491024171635961%26source%3Dyoutube%26clen%3D2738785%26key%3Dyt6%26ip%3D112.10.181.235%26keepalive%3Dyes%26mm%3D31%26mn%3Dsn-i3b7knl6%26mt%3D1494851290%26pl%3D24%26mv%3Du%26id%3Do-AFaPANl8Z--gvQhK5oOC24vdsf-5Iobat-yMTGqFHa0X%26ms%3Dau%26signature%3D47455CF46CB02975101BBB3906E0C073CB0FDD98.9A1F8A38DEEAFADA62D854187264CD7808EE18C8%26ipbits%3D0%26sparams%3Dclen%252Cdur%252Cei%252Cgir%252Cid%252Cip%252Cipbits%252Citag%252Ckeepalive%252Clmt%252Cmime%252Cmm%252Cmn%252Cms%252Cmv%252Cpl%252Crequiressl%252Csource%252Cupn%252Cexpire%26requiressl%3Dyes%26ei%3DPJ8ZWf7OGoayoQPizoPQAQ%26dur%3D477.720%26gir%3Dyes%26mime%3Dvideo%252Fmp4%26itag%3D134%26expire%3D1494872988&bitrate=129134,type=video%2Fwebm%3B+codecs%3D%22vp9%22&lmt=1491014540738105&quality_label=360p&index=243-1827&size=640x350&clen=2160783&init=0-242&itag=243&fps=25&projection_type=1&xtags=&url=https%3A%2F%2Fr1---sn-i3b7knl6.googlevideo.com%2Fvideoplayback%3Fupn%3DpKR3GPF2ZPU%26lmt%3D1491014540738105%26source%3Dyoutube%26clen%3D2160783%26key%3Dyt6%26ip%3D112.10.181.235%26keepalive%3Dyes%26mm%3D31%26mn%3Dsn-i3b7knl6%26mt%3D1494851290%26pl%3D24%26mv%3Du%26id%3Do-AFaPANl8Z--gvQhK5oOC24vdsf-5Iobat-yMTGqFHa0X%26ms%3Dau%26signature%3D7090C7613AFAF31DC840CA10A35EE65C76C44841.77DA87C379E6EE112C56F58F2BB315C7DB4D037E%26ipbits%3D0%26sparams%3Dclen%252Cdur%252Cei%252Cgir%252Cid%252Cip%252Cipbits%252Citag%252Ckeepalive%252Clmt%252Cmime%252Cmm%252Cmn%252Cms%252Cmv%252Cpl%252Crequiressl%252Csource%252Cupn%252Cexpire%26requiressl%3Dyes%26ei%3DPJ8ZWf7OGoayoQPizoPQAQ%26dur%3D477.680%26gir%3Dyes%26mime%3Dvideo%252Fwebm%26itag%3D243%26expire%3D1494872988&bitrate=102509,type=video%2Fmp4%3B+codecs%3D%22avc1.4d4015%22&lmt=1491024171639604&quality_label=240p&index=715-1874&size=426x234&clen=6009851&init=0-714&itag=133&fps=25&projection_type=1&xtags=&url=https%3A%2F%2Fr1---sn-i3b7knl6.googlevideo.com%2Fvideoplayback%3Fupn%3DpKR3GPF2ZPU%26lmt%3D1491024171639604%26source%3Dyoutube%26clen%3D6009851%26key%3Dyt6%26ip%3D112.10.181.235%26keepalive%3Dyes%26mm%3D31%26mn%3Dsn-i3b7knl6%26mt%3D1494851290%26pl%3D24%26mv%3Du%26id%3Do-AFaPANl8Z--gvQhK5oOC24vdsf-5Iobat-yMTGqFHa0X%26ms%3Dau%26signature%3D249C09FFB02BFC6D8F28236160FDCCB19D09BA74.25EEA28A4024861AC6D8F688A741CC6C3F4DCE8C%26ipbits%3D0%26sparams%3Dclen%252Cdur%252Cei%252Cgir%252Cid%252Cip%252Cipbits%252Citag%252Ckeepalive%252Clmt%252Cmime%252Cmm%252Cmn%252Cms%252Cmv%252Cpl%252Crequiressl%252Csource%252Cupn%252Cexpire%26requiressl%3Dyes%26ei%3DPJ8ZWf7OGoayoQPizoPQAQ%26dur%3D477.720%26gir%3Dyes%26mime%3Dvideo%252Fmp4%26itag%3D133%26expire%3D1494872988&bitrate=253118,type=video%2Fwebm%3B+codecs%3D%22vp9%22&lmt=1491014540108431&quality_label=240p&index=242-1823&size=426x234&clen=1282275&init=0-241&itag=242&fps=25&projection_type=1&xtags=&url=https%3A%2F%2Fr1---sn-i3b7knl6.googlevideo.com%2Fvideoplayback%3Fupn%3DpKR3GPF2ZPU%26lmt%3D1491014540108431%26source%3Dyoutube%26clen%3D1282275%26key%3Dyt6%26ip%3D112.10.181.235%26keepalive%3Dyes%26mm%3D31%26mn%3Dsn-i3b7knl6%26mt%3D1494851290%26pl%3D24%26mv%3Du%26id%3Do-AFaPANl8Z--gvQhK5oOC24vdsf-5Iobat-yMTGqFHa0X%26ms%3Dau%26signature%3D98C8ED2CB40A10B0AA60D6722278D1D7D61F591D.B6CA09F88EEF08C9F1ED65889EEDF7977A5A2DE9%26ipbits%3D0%26sparams%3Dclen%252Cdur%252Cei%252Cgir%252Cid%252Cip%252Cipbits%252Citag%252Ckeepalive%252Clmt%252Cmime%252Cmm%252Cmn%252Cms%252Cmv%252Cpl%252Crequiressl%252Csource%252Cupn%252Cexpire%26requiressl%3Dyes%26ei%3DPJ8ZWf7OGoayoQPizoPQAQ%26dur%3D477.680%26gir%3Dyes%26mime%3Dvideo%252Fwebm%26itag%3D242%26expire%3D1494872988&bitrate=57104,type=video%2Fmp4%3B+codecs%3D%22avc1.4d400c%22&lmt=1491024171527253&quality_label=144p&index=714-1873&size=256x140&clen=731757&init=0-713&itag=160&fps=25&projection_type=1&xtags=&url=https%3A%2F%2Fr1---sn-i3b7knl6.googlevideo.com%2Fvideoplayback%3Fupn%3DpKR3GPF2ZPU%26lmt%3D1491024171527253%26source%3Dyoutube%26clen%3D731757%26key%3Dyt6%26ip%3D112.10.181.235%26keepalive%3Dyes%26mm%3D31%26mn%3Dsn-i3b7knl6%26mt%3D1494851290%26pl%3D24%26mv%3Du%26id%3Do-AFaPANl8Z--gvQhK5oOC24vdsf-5Iobat-yMTGqFHa0X%26ms%3Dau%26signature%3D514931EAF85EEA6F018C27E290984E3D33F922BE.5BA9496D076BDC15303252A7C98FA2C35C66C679%26ipbits%3D0%26sparams%3Dclen%252Cdur%252Cei%252Cgir%252Cid%252Cip%252Cipbits%252Citag%252Ckeepalive%252Clmt%252Cmime%252Cmm%252Cmn%252Cms%252Cmv%252Cpl%252Crequiressl%252Csource%252Cupn%252Cexpire%26requiressl%3Dyes%26ei%3DPJ8ZWf7OGoayoQPizoPQAQ%26dur%3D477.720%26gir%3Dyes%26mime%3Dvideo%252Fmp4%26itag%3D160%26expire%3D1494872988&bitrate=31995,type=video%2Fwebm%3B+codecs%3D%22vp9%22&lmt=1491014540849679&quality_label=144p&index=242-1822&size=256x140&clen=1263074&init=0-241&itag=278&fps=25&projection_type=1&xtags=&url=https%3A%2F%2Fr1---sn-i3b7knl6.googlevideo.com%2Fvideoplayback%3Fupn%3DpKR3GPF2ZPU%26lmt%3D1491014540849679%26source%3Dyoutube%26clen%3D1263074%26key%3Dyt6%26ip%3D112.10.181.235%26keepalive%3Dyes%26mm%3D31%26mn%3Dsn-i3b7knl6%26mt%3D1494851290%26pl%3D24%26mv%3Du%26id%3Do-AFaPANl8Z--gvQhK5oOC24vdsf-5Iobat-yMTGqFHa0X%26ms%3Dau%26signature%3D1F17718FB4ED04B5246C0CDB813A7D384C77F0D5.1AFE2AD4CF651A3DB61D01857010E4858C6A25F9%26ipbits%3D0%26sparams%3Dclen%252Cdur%252Cei%252Cgir%252Cid%252Cip%252Cipbits%252Citag%252Ckeepalive%252Clmt%252Cmime%252Cmm%252Cmn%252Cms%252Cmv%252Cpl%252Crequiressl%252Csource%252Cupn%252Cexpire%26requiressl%3Dyes%26ei%3DPJ8ZWf7OGoayoQPizoPQAQ%26dur%3D477.680%26gir%3Dyes%26mime%3Dvideo%252Fwebm%26itag%3D278%26expire%3D1494872988&bitrate=44854,projection_type=1&index=592-1199&xtags=&init=0-591&type=audio%2Fmp4%3B+codecs%3D%22mp4a.40.2%22&url=https%3A%2F%2Fr1---sn-i3b7knl6.googlevideo.com%2Fvideoplayback%3Fupn%3DpKR3GPF2ZPU%26lmt%3D1491024166101548%26source%3Dyoutube%26clen%3D7588844%26key%3Dyt6%26ip%3D112.10.181.235%26keepalive%3Dyes%26mm%3D31%26mn%3Dsn-i3b7knl6%26mt%3D1494851290%26pl%3D24%26mv%3Du%26id%3Do-AFaPANl8Z--gvQhK5oOC24vdsf-5Iobat-yMTGqFHa0X%26ms%3Dau%26signature%3D2C2B080CD05CA2F35ABAB93AC6A4601C035B168D.2146F89608575228A91BFB6B83535D5709FEC9DE%26ipbits%3D0%26sparams%3Dclen%252Cdur%252Cei%252Cgir%252Cid%252Cip%252Cipbits%252Citag%252Ckeepalive%252Clmt%252Cmime%252Cmm%252Cmn%252Cms%252Cmv%252Cpl%252Crequiressl%252Csource%252Cupn%252Cexpire%26requiressl%3Dyes%26ei%3DPJ8ZWf7OGoayoQPizoPQAQ%26dur%3D477.773%26gir%3Dyes%26mime%3Daudio%252Fmp4%26itag%3D140%26expire%3D1494872988&itag=140&clen=7588844&lmt=1491024166101548&bitrate=128005,projection_type=1&index=4452-5264&xtags=&init=0-4451&type=audio%2Fwebm%3B+codecs%3D%22vorbis%22&url=https%3A%2F%2Fr1---sn-i3b7knl6.googlevideo.com%2Fvideoplayback%3Fupn%3DpKR3GPF2ZPU%26lmt%3D1491014433725666%26source%3Dyoutube%26clen%3D4947268%26key%3Dyt6%26ip%3D112.10.181.235%26keepalive%3Dyes%26mm%3D31%26mn%3Dsn-i3b7knl6%26mt%3D1494851290%26pl%3D24%26mv%3Du%26id%3Do-AFaPANl8Z--gvQhK5oOC24vdsf-5Iobat-yMTGqFHa0X%26ms%3Dau%26signature%3D9B0C275A2DBA3BE818154B74E74C7022E2187F7F.0E1FDD611FD36BA1A40B4A38D64E3F767A670231%26ipbits%3D0%26sparams%3Dclen%252Cdur%252Cei%252Cgir%252Cid%252Cip%252Cipbits%252Citag%252Ckeepalive%252Clmt%252Cmime%252Cmm%252Cmn%252Cms%252Cmv%252Cpl%252Crequiressl%252Csource%252Cupn%252Cexpire%26requiressl%3Dyes%26ei%3DPJ8ZWf7OGoayoQPizoPQAQ%26dur%3D477.717%26gir%3Dyes%26mime%3Daudio%252Fwebm%26itag%3D171%26expire%3D1494872988&itag=171&clen=4947268&lmt=1491014433725666&bitrate=93632,projection_type=1&index=272-1083&xtags=&init=0-271&type=audio%2Fwebm%3B+codecs%3D%22opus%22&url=https%3A%2F%2Fr1---sn-i3b7knl6.googlevideo.com%2Fvideoplayback%3Fupn%3DpKR3GPF2ZPU%26lmt%3D1491014431509675%26source%3Dyoutube%26clen%3D2740439%26key%3Dyt6%26ip%3D112.10.181.235%26keepalive%3Dyes%26mm%3D31%26mn%3Dsn-i3b7knl6%26mt%3D1494851290%26pl%3D24%26mv%3Du%26id%3Do-AFaPANl8Z--gvQhK5oOC24vdsf-5Iobat-yMTGqFHa0X%26ms%3Dau%26signature%3D115A5C958E82B079D23B92C25B63FECE3B474FFE.568D3CD03108672D2A95564DBD245029323B32D9%26ipbits%3D0%26sparams%3Dclen%252Cdur%252Cei%252Cgir%252Cid%252Cip%252Cipbits%252Citag%252Ckeepalive%252Clmt%252Cmime%252Cmm%252Cmn%252Cms%252Cmv%252Cpl%252Crequiressl%252Csource%252Cupn%252Cexpire%26requiressl%3Dyes%26ei%3DPJ8ZWf7OGoayoQPizoPQAQ%26dur%3D477.721%26gir%3Dyes%26mime%3Daudio%252Fwebm%26itag%3D249%26expire%3D1494872988&itag=249&clen=2740439&lmt=1491014431509675&bitrate=50143,projection_type=1&index=272-1083&xtags=&init=0-271&type=audio%2Fwebm%3B+codecs%3D%22opus%22&url=https%3A%2F%2Fr1---sn-i3b7knl6.googlevideo.com%2Fvideoplayback%3Fupn%3DpKR3GPF2ZPU%26lmt%3D1491014433037665%26source%3Dyoutube%26clen%3D3389512%26key%3Dyt6%26ip%3D112.10.181.235%26keepalive%3Dyes%26mm%3D31%26mn%3Dsn-i3b7knl6%26mt%3D1494851290%26pl%3D24%26mv%3Du%26id%3Do-AFaPANl8Z--gvQhK5oOC24vdsf-5Iobat-yMTGqFHa0X%26ms%3Dau%26signature%3D7226786CBBE66038335CF2DAAD1233E70955B806.0AA9A3DF15A3FDA83E3EE4787569665F19E1BAD3%26ipbits%3D0%26sparams%3Dclen%252Cdur%252Cei%252Cgir%252Cid%252Cip%252Cipbits%252Citag%252Ckeepalive%252Clmt%252Cmime%252Cmm%252Cmn%252Cms%252Cmv%252Cpl%252Crequiressl%252Csource%252Cupn%252Cexpire%26requiressl%3Dyes%26ei%3DPJ8ZWf7OGoayoQPizoPQAQ%26dur%3D477.721%26gir%3Dyes%26mime%3Daudio%252Fwebm%26itag%3D250%26expire%3D1494872988&itag=250&clen=3389512&lmt=1491014433037665&bitrate=62512,projection_type=1&index=272-1084&xtags=&init=0-271&type=audio%2Fwebm%3B+codecs%3D%22opus%22&url=https%3A%2F%2Fr1---sn-i3b7knl6.googlevideo.com%2Fvideoplayback%3Fupn%3DpKR3GPF2ZPU%26lmt%3D1491014464496134%26source%3Dyoutube%26clen%3D5983890%26key%3Dyt6%26ip%3D112.10.181.235%26keepalive%3Dyes%26mm%3D31%26mn%3Dsn-i3b7knl6%26mt%3D1494851290%26pl%3D24%26mv%3Du%26id%3Do-AFaPANl8Z--gvQhK5oOC24vdsf-5Iobat-yMTGqFHa0X%26ms%3Dau%26signature%3D37642FA16A88B62ED0E73A7AE9A29F6D53B32618.5925E6A22A90EA0574B6329ECBF1C533C3168D59%26ipbits%3D0%26sparams%3Dclen%252Cdur%252Cei%252Cgir%252Cid%252Cip%252Cipbits%252Citag%252Ckeepalive%252Clmt%252Cmime%252Cmm%252Cmn%252Cms%252Cmv%252Cpl%252Crequiressl%252Csource%252Cupn%252Cexpire%26requiressl%3Dyes%26ei%3DPJ8ZWf7OGoayoQPizoPQAQ%26dur%3D477.721%26gir%3Dyes%26mime%3Daudio%252Fwebm%26itag%3D251%26expire%3D1494872988&itag=251&clen=5983890&lmt=1491014464496134&bitrate=112419\",";
    String html = decode(url).replace("\\u0026", "&");
    System.out.println(html);
    String urls[] = html.split("https://");
    for (String ss : urls) {
      ss = "https://" + ss.substring(0, ss.indexOf(";") == -1 ? ss.length() : ss.indexOf(";"));
      System.out.println(ss);
    }
  }

  public static String decode(String url) {
    try {
      String prevURL = "";
      String decodeURL = url;
      while (!prevURL.equals(decodeURL)) {
        prevURL = decodeURL;
        decodeURL = URLDecoder.decode(decodeURL, "UTF-8");
      }
      return decodeURL;
    } catch (UnsupportedEncodingException e) {
      return "Issue while decoding" + e.getMessage();
    }
  }

  public static String encode(String url) {
    try {
      String encodeURL = URLEncoder.encode(url, "UTF-8");
      return encodeURL;
    } catch (UnsupportedEncodingException e) {
      return "Issue while encoding" + e.getMessage();
    }
  }
}