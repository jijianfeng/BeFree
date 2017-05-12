package com.jjf.befree.crawler;

import javax.net.ssl.*;
import java.io.*;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * 无视Https证书是否正确的Java Http Client  
 * 
 * 
 * @author huangxuebin
 * 
 * @create 2012.8.17
 * @version 1.0
 */
public class HttpsUtil {

	/**
	 * 忽视证书HostName
	 */
	private static HostnameVerifier ignoreHostnameVerifier = new HostnameVerifier() {
		public boolean verify(String s, SSLSession sslsession) {
			// System.out.println("WARNING: Hostname is not matched for cert.");
			return true;
		}
	};

	/**
	 * Ignore Certification
	 */
	private static TrustManager ignoreCertificationTrustManger = new X509TrustManager() {

		private X509Certificate[] certificates;

		public void checkClientTrusted(X509Certificate certificates[],
				String authType) throws CertificateException {
			if (this.certificates == null) {
				this.certificates = certificates;
				// System.out.println("init at checkClientTrusted");
			}
		}

		public void checkServerTrusted(X509Certificate[] ax509certificate,
				String s) throws CertificateException {
			if (this.certificates == null) {
				this.certificates = ax509certificate;
				// System.out.println("init at checkServerTrusted");
			}
			// for (int c = 0; c < certificates.length; c++) {
			// X509Certificate cert = certificates[c];
			// System.out.println(" Server certificate " + (c + 1) + ":");
			// System.out.println("  Subject DN: " + cert.getSubjectDN());
			// System.out.println("  Signature Algorithm: "
			// + cert.getSigAlgName());
			// System.out.println("  Valid from: " + cert.getNotBefore());
			// System.out.println("  Valid until: " + cert.getNotAfter());
			// System.out.println("  Issuer: " + cert.getIssuerDN());
			// }

		}

		public X509Certificate[] getAcceptedIssuers() {
			// TODO Auto-generated method stub
			return null;
		}
	};

	public static String getMethod(String urlString) {

		ByteArrayOutputStream buffer = new ByteArrayOutputStream(512);
		try {
			URL url = new URL(urlString);
			/*
			 * use ignore host name verifier
			 */
			HttpsURLConnection
					.setDefaultHostnameVerifier(ignoreHostnameVerifier);
			HttpsURLConnection connection = (HttpsURLConnection) url
					.openConnection();

			// Prepare SSL Context
			TrustManager[] tm = { ignoreCertificationTrustManger };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			connection.setSSLSocketFactory(ssf);
			InputStream reader = connection.getInputStream();
			byte[] bytes = new byte[512];
			int length = reader.read(bytes);
			do {
				buffer.write(bytes, 0, length);
				length = reader.read(bytes);
			} 
			while (length > 0);
			reader.close();

			connection.disconnect();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
		String repString = new String(buffer.toByteArray());
		return repString;
	}

	/** (公共方法)把String写到本地文件 */
	public static void writeString(String s, String savePath) throws IOException {
		File f = new File(savePath);
		FileWriter fw = new FileWriter(f);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(s);
		bw.close();
	}
	
	public static void main(String[] args) {
		String urlString = "https://www.haosou.com/s?pn=1&q=杭州电子科技大学";
		String output = new String(HttpsUtil.getMethod(urlString));
		System.out.println(output);
	}
}