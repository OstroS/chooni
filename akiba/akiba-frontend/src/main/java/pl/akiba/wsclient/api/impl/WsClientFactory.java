package pl.akiba.wsclient.api.impl;

import java.util.logging.Logger;

import org.eclipse.jetty.client.HttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import pl.akiba.wsclient.client.DefaultExpenseClient;
import pl.akiba.wsclient.client.DefaultUserClient;
import pl.akiba.wsclient.client.factory.JettyHttpClientFactory;
import pl.akiba.wsclient.client.factory.JettyHttpClientConf.Builder;

@Component("wsClientFactory")
public class WsClientFactory {

    private static final int MAX_RETRIES = 5;
    private static final int TIMEOUT = 1500;
    private static final int MAX_THREADS = 100;
    private static final int IDLE_TIMEOUT = 1500;
    private static final boolean CONNECT_BLOCKING = false;
    private static final int MAX_CONNECTIONS_PER_ADDRESS = 100;
    private static final int CONNECTOR_TYPE = HttpClient.CONNECTOR_SELECT_CHANNEL;

    @Value("${webservice.endpoint.addr}")
    private String WS_ENDPOINT;

    private static final Logger logger = Logger.getLogger(WsClientFactory.class.toString());
    
    //@formatter:off
    private static final Builder httpClientConfBuilder = new Builder().
            withConnectorType(CONNECTOR_TYPE)
            .withMaxConnectionsPerAddress(MAX_CONNECTIONS_PER_ADDRESS).
            withConnectBlocking(CONNECT_BLOCKING)
            .withIdleTimeout(IDLE_TIMEOUT).
            withMaxThreads(MAX_THREADS).
            withTimeout(TIMEOUT).
            withMaxRetries(MAX_RETRIES);
    //@formatter:on

    /**
     * Creates new Default User Client with predefined parameters
     * 
     * @return
     */
    public DefaultUserClient createDefaultUserClient() {
        JettyHttpClientFactory httpClientFactory = new JettyHttpClientFactory(httpClientConfBuilder.build());
        HttpClient httpClient = httpClientFactory.getHttpClient();
        return new DefaultUserClient(WS_ENDPOINT, httpClient);
    }
    
    /**
     * Creates new Default Expense Client with predefined parameters
     * @return
     */
    public DefaultExpenseClient createDefaultExpenseClient() {
        JettyHttpClientFactory httpClientFactory = new JettyHttpClientFactory(httpClientConfBuilder.build());
        HttpClient httpClient = httpClientFactory.getHttpClient();
        return new DefaultExpenseClient(WS_ENDPOINT, httpClient);
        
    }

}
