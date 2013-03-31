package pl.akiba.wsclient.api.impl;

import org.eclipse.jetty.client.HttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import pl.akiba.wsclient.client.DefaultExpenseClient;
import pl.akiba.wsclient.client.DefaultKindClient;
import pl.akiba.wsclient.client.DefaultProfileClient;
import pl.akiba.wsclient.client.DefaultUserClient;
import pl.akiba.wsclient.client.factory.JettyHttpClientConf.Builder;
import pl.akiba.wsclient.client.factory.JettyHttpClientFactory;

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
        HttpClient httpClient = prepareHttpClient();
        return new DefaultUserClient(WS_ENDPOINT, httpClient);
    }

    /**
     * Creates new Default Expense Client with predefined parameters
     * 
     * @return
     */
    public DefaultExpenseClient createDefaultExpenseClient() {
        HttpClient httpClient = prepareHttpClient();
        return new DefaultExpenseClient(WS_ENDPOINT, httpClient);

    }

    public DefaultKindClient createDefaultKindClient() {
        HttpClient httpClient = prepareHttpClient();
        return new DefaultKindClient(WS_ENDPOINT, httpClient);
    }
    
    public DefaultProfileClient createDefaultProfileClient() {
        HttpClient httpClient = prepareHttpClient();
        return new DefaultProfileClient(WS_ENDPOINT, httpClient);
    }

    private HttpClient prepareHttpClient() {
        JettyHttpClientFactory httpClientFactory = new JettyHttpClientFactory(httpClientConfBuilder.build());
        HttpClient httpClient = httpClientFactory.getHttpClient();
        return httpClient;
    }

}
