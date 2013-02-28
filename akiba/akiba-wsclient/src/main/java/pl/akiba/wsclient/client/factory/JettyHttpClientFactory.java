package pl.akiba.wsclient.client.factory;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.akiba.wsclient.client.DefaultClient;

/**
 * 
 * @author sobczakt
 */
public class JettyHttpClientFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultClient.class);

    private final HttpClient httpClient;

    public JettyHttpClientFactory(JettyHttpClientConf httpClientConf) {
        httpClient = new HttpClient();
        setProperties(httpClientConf);
    }

    public HttpClient getHttpClient() {
        try {
            if (!httpClient.isStarted()) {
                httpClient.start();
            }
        } catch (Exception e) {
            LOGGER.error("Exception caught during starting httpClient", e);
        }

        return httpClient;
    }

    private void setProperties(JettyHttpClientConf httpClientConf) {
        if (httpClientConf.getMaxRetries() != null) {
            httpClient.setMaxRetries(httpClientConf.getMaxRetries());
        }
        if (httpClientConf.getTimeout() != null) {
            httpClient.setTimeout(httpClientConf.getTimeout());
        }
        if (httpClientConf.getIdleTimeout() != null) {
            httpClient.setIdleTimeout(httpClientConf.getIdleTimeout());
        }
        if (httpClientConf.getConnectBlocking() != null) {
            httpClient.setConnectBlocking(httpClientConf.getConnectBlocking());
        }
        if (httpClientConf.getMaxConnectionsPerAddress() != null) {
            httpClient.setMaxConnectionsPerAddress(httpClientConf.getMaxConnectionsPerAddress());
        }
        if (httpClientConf.getMaxThreads() != null) {
            httpClient.setThreadPool(new QueuedThreadPool(httpClientConf.getMaxThreads()));
        }
        if (httpClientConf.getConnectorType() != null) {
            httpClient.setConnectorType(httpClientConf.getConnectorType());
        }
    }

}
