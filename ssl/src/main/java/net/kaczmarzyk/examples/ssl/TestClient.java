package net.kaczmarzyk.examples.ssl;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;

public class TestClient {

	public static void main(String... args) throws Exception {
//		System.setProperty("javax.net.debug", "ssl");
		
		HttpClient client = getClient();
		
		HttpResponse resp = client.execute(new HttpGet("https://localhost:8443/"));
		
		System.out.println(IOUtils.readLines(resp.getEntity().getContent()));
	}

	private static HttpClient getClient() throws Exception {
		Scheme http = new Scheme("http", 80, PlainSocketFactory.getSocketFactory());

		SSLSocketFactory sf = new SSLSocketFactory(SimpleServer.loadKeyStore());
//		SSLSocketFactory sf = new SSLSocketFactory(SSLContext.getDefault()); // this will work only with certs signed by root CAs
		
		Scheme https = new Scheme("https", 443, sf);

		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getConnectionManager().getSchemeRegistry().register(https);
		return httpclient;
	}
}
