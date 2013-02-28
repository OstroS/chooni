package pl.akiba.wsclient.client;

import org.codehaus.jackson.map.ObjectMapper;
import org.eclipse.jetty.client.ContentExchange;
import org.eclipse.jetty.client.HttpClient;

/**
 * 
 * @author sobczakt
 */
public class DefaultClient {

    protected final HttpClient httpClient;
    protected final String address;
    protected final ObjectMapper mapper;

    /**
     * @param address
     *            http://host:port/app-context/
     */
    protected DefaultClient(String address, HttpClient httpClient) {
        this.address = address;
        this.httpClient = httpClient;
        this.mapper = new ObjectMapper();
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
