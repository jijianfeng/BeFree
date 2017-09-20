package com.jjf.befree.core.utils;

import com.jjf.befree.core.exception.FormateResponseException;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

/**
 * Created by jjf_lenovo on 2017/5/23.
 */
public class FormatResponse {

  private static final int ERROR_CODE = 1;

  /**
   * 格式化请求结果
   */
  public static String formatResponse(HttpResponse response, String encoding)
      throws FormateResponseException {

    ByteArrayInputStream bis = null;
    try {
      Header contentEncoding = response.getFirstHeader("Content-Encoding");

      if (contentEncoding == null) {
        return EntityUtils.toString(response.getEntity(), encoding);
      } else {

        String charset = "utf-8";
        Header contentType = response.getFirstHeader("Content-Type");

        if (contentType != null) {
          String contentTypeStr = contentType.getValue();
          if (contentTypeStr != null && !"".equals(contentTypeStr)) {
            charset =
                contentTypeStr.substring(contentTypeStr.indexOf("=") + 1, contentTypeStr.length());

          }
        }

        String contentEncodingType = contentEncoding.getValue();
        if (contentEncodingType.equalsIgnoreCase("gzip")) {
          if (response.toString().contains("soufun")) {
            charset = "gb2312";
          }
          byte[] bytes = IOUtils.toByteArray(response.getEntity().getContent());
          bis = new ByteArrayInputStream(bytes);
          return uncompress(bis, charset);
        }

      }

    } catch (IOException e) {
      throw new FormateResponseException(ERROR_CODE + e.getMessage());
    } finally {
      if (bis != null) {
        try {
          bis.close();
        } catch (IOException e) {
          throw new FormateResponseException(ERROR_CODE + e.getMessage());
        }
      }
    }
    return null;
  }

  /**
   * GZIP解压
   */
  private static String uncompress(ByteArrayInputStream in, String charset) throws IOException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    try {
      GZIPInputStream gunzip = new GZIPInputStream(in);
      byte[] buffer = new byte[256];
      int n;
      while ((n = gunzip.read(buffer)) >= 0) {
        out.write(buffer, 0, n);
      }
      return out.toString(charset);

    } finally {
      out.close();
    }
  }
}
