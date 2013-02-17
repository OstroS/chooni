package pl.akiba.wsclient.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.eclipse.jetty.client.ContentExchange;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.HttpExchange;

import pl.akiba.model.entities.Expense;
import pl.akiba.model.entities.Filter;
import pl.akiba.model.entities.Kind;
import pl.akiba.model.entities.Profile;
import pl.akiba.model.exception.MethodFailureStatusException;
import pl.akiba.model.exception.NotFoundStatusException;
import pl.akiba.model.exception.StatusException;

/**
 * 
 * @author sobczakt
 */
public class DefaultExpenseClient extends DefaultClient implements ExpenseClient {

    private final HttpClient httpClient;
    private final String address;
    private final ObjectMapper mapper;

    public DefaultExpenseClient(String address) {
        this.httpClient = getConfiguredHttpClient();
        this.address = address;
        this.mapper = new ObjectMapper();
    }

    public DefaultExpenseClient(HttpClient httpClient, String address) {
        this.address = address;
        this.mapper = new ObjectMapper();
        this.httpClient = httpClient;
    }

    @Override
    public Expense get(int userId, int expenseId) throws StatusException, IOException, InterruptedException, Exception {
        StringBuilder urlBuilder = new StringBuilder(address);
        urlBuilder.append(userId).append("/expense/").append(expenseId);

        ContentExchange exchange = new ContentExchange();
        exchange.setURL(urlBuilder.toString());
        exchange.setMethod("GET");

        httpClient.start();
        httpClient.send(exchange);
        int exchangeStatus = exchange.waitForDone();
        httpClient.stop();

        if (exchangeStatus == HttpExchange.STATUS_COMPLETED) {
            int responseStatus = exchange.getResponseStatus();
            switch (responseStatus) {
                case 200:
                    String json = exchange.getResponseContent();
                    return mapper.readValue(json, Expense.class);
                case 404:
                    throw new NotFoundStatusException("Http response returns NOT FOUND (404) status");
                case 420:
                    throw new MethodFailureStatusException("Http response returns METHOD FAILURE (420) status");
                default:
                    throw new StatusException("Http response returns unknown status");
            }
        }

        throw new StatusException("Http exchange returns status: " + getExchangeStatusName(exchangeStatus));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Expense> getAll(int userId, Filter filter) throws StatusException, IOException, InterruptedException,
            Exception {

        StringBuilder urlBuilder = new StringBuilder(address);
        urlBuilder.append(userId).append("/expense");
        if (filter != null) {
            urlBuilder.append(filter.getFilterString());
        }

        ContentExchange exchange = new ContentExchange();
        exchange.setURL(urlBuilder.toString());
        exchange.setMethod("GET");

        httpClient.start();
        httpClient.send(exchange);
        int exchangeStatus = exchange.waitForDone();
        httpClient.stop();

        if (exchangeStatus == HttpExchange.STATUS_COMPLETED) {
            int responseStatus = exchange.getResponseStatus();
            switch (responseStatus) {
                case 200:
                    String json = exchange.getResponseContent();
                    List<Map<String, Object>> rawExpenses = mapper.readValue(json, ArrayList.class);

                    List<Expense> expenses = new ArrayList<Expense>(rawExpenses.size());
                    for (int i = 0; i < rawExpenses.size(); i++) {
                        Map<String, Object> expenseRawFields = (Map<String, Object>) rawExpenses.get(i);
                        expenses.add(buildExpenseEntity(expenseRawFields));
                    }

                    return expenses;
                case 404:
                    throw new NotFoundStatusException("Http response returns NOT FOUND (404) status");
                case 420:
                    throw new MethodFailureStatusException("Http response returns METHOD FAILURE (420) status");
                default:
                    throw new StatusException("Http response returns unknown status");
            }
        }

        throw new StatusException("Http exchange returns status: " + getExchangeStatusName(exchangeStatus));
    }

    @Override
    public double getTotal(int userId, Filter filter) {
        return 0;
    }

    @Override
    public Expense create(int userId, Expense expense) {
        return null;
    }

    @Override
    public void update(int userId, Expense expense) {
    }

    @Override
    public void delete(int userId, int expenseId) {
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

}
