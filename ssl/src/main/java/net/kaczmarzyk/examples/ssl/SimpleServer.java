package net.kaczmarzyk.examples.ssl;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.ssl.SslSelectChannelConnector;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.ssl.SslContextFactory;

public class SimpleServer {

	private static final String KEYSTORE_FILENAME = "/keystore";
	private static final String KEYSTORE_PASS = "123456";

	public static void main(String... args) throws Exception {
		Log.getRootLogger().setDebugEnabled(true);
		
		Server server = new Server();

		SslContextFactory sslCtxFactory = new SslContextFactory();
		KeyStore keystore = loadKeyStore(KEYSTORE_FILENAME, KEYSTORE_PASS);
		sslCtxFactory.setKeyStore(keystore);
		sslCtxFactory.setKeyStorePassword(KEYSTORE_PASS);
		sslCtxFactory.setKeyManagerPassword(KEYSTORE_PASS);
		sslCtxFactory.setTrustStore(keystore);
		sslCtxFactory.setTrustStorePassword(KEYSTORE_PASS);
		sslCtxFactory.setIncludeCipherSuites("TLS_DHE_DSS_WITH_AES_128_CBC_SHA",
				"TLS_DHE_RSA_WITH_AES_128_CBC_SHA", "TLS_RSA_WITH_AES_128_CBC_SHA",
				"TLS_DHE_DSS_WITH_AES_256_CBC_SHA", "TLS_DHE_RSA_WITH_AES_256_CBC_SHA",
				"TLS_RSA_WITH_AES_256_CBC_SHA");
		
		SslSelectChannelConnector connector = new SslSelectChannelConnector(sslCtxFactory);
		connector.setPort(8443);
		connector.setMaxIdleTime(30000);

		server.setConnectors(new Connector[] { connector });
		server.setHandler(new HelloHandler());
		
		server.start();
		server.join();
	}

	private static KeyStore loadKeyStore(String filename, String password) throws Exception {
		KeyStore keyStore = KeyStore.getInstance("JKS");
		InputStream ksResource = SimpleServer.class.getResourceAsStream(filename);
		if (ksResource == null) {
			throw new IllegalStateException("could not load the keystore!");
		}
		keyStore.load(ksResource, password.toCharArray());
		return keyStore;
	}

	public static class HelloHandler extends AbstractHandler {
		public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServletException {

			response.setContentType("text/html;charset=utf-8");
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().println("Hello, World!");

			baseRequest.setHandled(true);
		}
	}
}
