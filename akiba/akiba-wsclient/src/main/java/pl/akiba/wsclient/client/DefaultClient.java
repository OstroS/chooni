package pl.akiba.wsclient.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.eclipse.jetty.client.ContentExchange;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.io.ByteArrayBuffer;

import pl.akiba.model.entities.AkibaEntity;
import pl.akiba.model.entities.Expense;
import pl.akiba.model.entities.Kind;
import pl.akiba.model.entities.Profile;

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

    protected StringBuilder prepareBasicUrl(long userId) {
        return new StringBuilder(address).append("/").append(userId);
    }

    protected String getExchangeStatusName(int state) {
        return HttpExchangeStatus.getName(state);
    }

    /**
     * 
     * @param method
     * @param url
     * @param entity
     */
    protected ContentExchange sendExchange(HttpMethod method, String url, AkibaEntity entity) throws IOException,
            InterruptedException {
        ContentExchange exchange = new ContentExchange();
        exchange.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        exchange.setMethod(method.name());
        exchange.setURL(url);
        if (entity != null) {
            exchange.setRequestContent(new ByteArrayBuffer(mapper.writeValueAsString(entity).getBytes("UTF-8")));
        }

        httpClient.send(exchange);

        return exchange;
    }

    protected enum HttpMethod {
        GET,
        POST,
        PUT,
        DELETE
    }

    @SuppressWarnings("unchecked")
    protected <T> List<T> mapJsonToEntities(String responseContent, Class<T> clazz) throws IOException,
            JsonParseException, JsonMappingException, UnsupportedEncodingException {

        List<Map<String, Object>> rawEntities = mapper.readValue(responseContent, ArrayList.class);
        List<T> entities = new ArrayList<T>(rawEntities.size());

        for (int i = 0; i < rawEntities.size(); i++) {
            Map<String, Object> rawEntity = (Map<String, Object>) rawEntities.get(i);

            //FIXME (T) w .add()
            if (clazz == Expense.class) {
                entities.add((T) buildExpenseEntity(rawEntity));
            } else if (clazz == Kind.class) {
                entities.add((T) new Kind((int) rawEntity.get("id"), (String) rawEntity.get("name")));
            } else if (clazz == Profile.class) {
                entities.add((T) new Profile((int) rawEntity.get("id"), (String) rawEntity.get("name"),
                        (boolean) rawEntity.get("def"), (boolean) rawEntity.get("active")));
            }
        }

        return entities;
    }

    @SuppressWarnings("unchecked")
    private Expense buildExpenseEntity(Map<String, Object> expenseRawFields) {
        Map<String, Object> kindRawFields = (Map<String, Object>) expenseRawFields.get("kind");
        Kind kindTmp = new Kind((int) kindRawFields.get("id"), (String) kindRawFields.get("name"));

        Map<String, Object> profileRawFields = (Map<String, Object>) expenseRawFields.get("profile");
        Profile profileTmp = new Profile((int) profileRawFields.get("id"), (String) profileRawFields.get("name"),
                (boolean) profileRawFields.get("def"), (boolean) profileRawFields.get("active"));

        Expense expenseTmp = new Expense((int) expenseRawFields.get("id"), (double) expenseRawFields.get("amount"),
                kindTmp, profileTmp, new Date((long) expenseRawFields.get("date")));

        return expenseTmp;
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
