package pl.akiba.wsclient.client;

import org.eclipse.jetty.client.ContentExchange;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

public class DefaultClient {

    protected HttpClient getConfiguredHttpClient() throws Exception {
        final HttpClient httpClient = new HttpClient();
        httpClient.setMaxRetries(5);
        httpClient.setTimeout(1500);
        httpClient.setIdleTimeout(1500);
        httpClient.setConnectBlocking(false);
        httpClient.setMaxConnectionsPerAddress(100);
        httpClient.setThreadPool(new QueuedThreadPool(100));
        httpClient.setConnectorType(HttpClient.CONNECTOR_SELECT_CHANNEL);
        httpClient.start();

        return httpClient;
    }

    protected String getExchangeStatusName(int state) {
        return HttpExchangeStatus.getName(state);
    }

    protected ContentExchange prepareExchange(HttpMethod method, String url) {
        ContentExchange exchange = new ContentExchange();
        exchange.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        exchange.setMethod(method.name());
        exchange.setURL(url);

        return exchange;
    }

    protected enum HttpMethod {
        GET,
        POST,
        PUT,
        DELETE
    }

    private enum HttpExchangeStatus {

        STATUS_START(0),
        STATUS_WAITING_FOR_CONNECTION(1),
        STATUS_WAITING_FOR_COMMIT(2),
        STATUS_SENDING_REQUEST(3),
        STATUS_WAITING_FOR_RESPONSE(4),
        STATUS_PARSING_HEADERS(5),
        STATUS_PARSING_CONTENT(6),
        STATUS_COMPLETED(7),
        STATUS_EXPIRED(8),
        STATUS_EXCEPTED(9),
        STATUS_CANCELLING(10),
        STATUS_CANCELLED(11);

        private final int statusCode;

        private HttpExchangeStatus(int statusCode) {
            this.statusCode = statusCode;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public static String getName(int statusCode) {
            HttpExchangeStatus[] statuses = HttpExchangeStatus.values();
            for (HttpExchangeStatus status : statuses) {
                if (status.getStatusCode() == statusCode) {
                    return status.name();
                }
            }

            return null;
        }

    }

}
