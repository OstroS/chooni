package pl.akiba.wsclient.client;

import java.util.Random;

import org.eclipse.jetty.client.HttpClient;

import pl.akiba.wsclient.client.factory.JettyHttpClientConf.Builder;
import pl.akiba.wsclient.client.factory.JettyHttpClientFactory;

public class CommonClientTest {

    protected final JettyHttpClientFactory httpClientFactory;
    protected final Random random = new Random();
    protected final int userId = 0;

    public CommonClientTest() {
        //@formatter:off
        Builder httpClientConfBuilder = new Builder()
            .withConnectorType(HttpClient.CONNECTOR_SELECT_CHANNEL)
            .withMaxConnectionsPerAddress(100)
            .withConnectBlocking(false)
            .withIdleTimeout(1500)
            .withMaxThreads(100)
            .withTimeout(1500)
            .withMaxRetries(5);
        //@formatter:on

        httpClientFactory = new JettyHttpClientFactory(httpClientConfBuilder.build());
    }

}
