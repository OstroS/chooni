package pl.akiba.wsclient.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.jetty.client.ContentExchange;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.HttpExchange;
import org.eclipse.jetty.io.ByteArrayBuffer;

import pl.akiba.model.entities.Expense;
import pl.akiba.model.entities.Filter;
import pl.akiba.model.entities.Kind;
import pl.akiba.model.entities.Profile;
import pl.akiba.model.exception.MethodFailureStatusException;
import pl.akiba.model.exception.NotFoundStatusException;
import pl.akiba.model.exception.StatusException;
import pl.akiba.model.service.ExpenseService;

/**
 * 
 * @author sobczakt
 */
public class DefaultExpenseClient extends DefaultClient implements ExpenseService {

    public DefaultExpenseClient(String address, HttpClient httpClient) {
        super(address, httpClient);
    }

    @Override
    public Expense get(int userId, int expenseId) throws StatusException, IOException, InterruptedException {
        StringBuilder urlBuilder = new StringBuilder(address);
        urlBuilder.append("/").append(userId).append("/expense/").append(expenseId);

        ContentExchange exchange = prepareExchange(HttpMethod.GET, urlBuilder.toString());
        httpClient.send(exchange);
        int exchangeStatus = exchange.waitForDone();

        if (exchangeStatus == HttpExchange.STATUS_COMPLETED) {
            int responseStatus = exchange.getResponseStatus();
            switch (responseStatus) {
                case 200:
                    return mapper.readValue(exchange.getResponseContent(), Expense.class);
                case 404:
                    throw new NotFoundStatusException("Http response returns NOT FOUND (404) status");
                case 420:
                    throw new MethodFailureStatusException("Http response returns METHOD FAILURE (420) status");
                default:
                    throw new StatusException("Http response returns unknown (" + responseStatus + ") status");
            }
        }

        throw new StatusException("Http exchange returns status: " + getExchangeStatusName(exchangeStatus));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Expense> getAll(int userId, Filter filter) throws StatusException, IOException, InterruptedException {
        StringBuilder urlBuilder = new StringBuilder(address);
        urlBuilder.append("/").append(userId).append("/expense");
        if (filter != null) {
            urlBuilder.append(filter.getFilterString());
        }

        ContentExchange exchange = prepareExchange(HttpMethod.GET, urlBuilder.toString());
        httpClient.send(exchange);
        int exchangeStatus = exchange.waitForDone();

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
                    throw new StatusException("Http response returns unknown (" + responseStatus + ") status");
            }
        }

        throw new StatusException("Http exchange returns status: " + getExchangeStatusName(exchangeStatus));
    }

    @Override
    public double getTotal(int userId, Filter filter) throws StatusException, IOException, InterruptedException {
        StringBuilder urlBuilder = new StringBuilder(address);
        urlBuilder.append("/").append(userId).append("/expense/total");
        if (filter != null) {
            urlBuilder.append(filter.getFilterString());
        }

        ContentExchange exchange = prepareExchange(HttpMethod.GET, urlBuilder.toString());
        httpClient.send(exchange);
        int exchangeStatus = exchange.waitForDone();

        if (exchangeStatus == HttpExchange.STATUS_COMPLETED) {
            int responseStatus = exchange.getResponseStatus();
            switch (responseStatus) {
                case 200:
                    return Double.parseDouble(exchange.getResponseContent());
                case 420:
                    throw new MethodFailureStatusException("Http response returns METHOD FAILURE (420) status");
                default:
                    throw new StatusException("Http response returns unknown (" + responseStatus + ") status");
            }
        }

        throw new StatusException("Http exchange returns status: " + getExchangeStatusName(exchangeStatus));
    }

    @Override
    public Expense create(int userId, Expense expense) throws StatusException, IOException, InterruptedException {
        StringBuilder urlBuilder = new StringBuilder(address);
        urlBuilder.append("/").append(userId).append("/expense");

        ContentExchange exchange = prepareExchange(HttpMethod.POST, urlBuilder.toString());
        exchange.setRequestContent(new ByteArrayBuffer(mapper.writeValueAsString(expense).getBytes("UTF-8")));

        httpClient.send(exchange);
        int exchangeStatus = exchange.waitForDone();

        if (exchangeStatus == HttpExchange.STATUS_COMPLETED) {
            int responseStatus = exchange.getResponseStatus();
            switch (responseStatus) {
                case 201:
                    return mapper.readValue(exchange.getResponseContent(), Expense.class);
                case 420:
                    throw new MethodFailureStatusException("Http response returns METHOD FAILURE (420) status");
                default:
                    throw new StatusException("Http response returns unknown (" + responseStatus + ") status");
            }
        }

        throw new StatusException("Http exchange returns status: " + getExchangeStatusName(exchangeStatus));
    }

    @Override
    public Expense update(int userId, Expense expense) throws StatusException, IOException, InterruptedException {
        StringBuilder urlBuilder = new StringBuilder(address);
        urlBuilder.append("/").append(userId).append("/expense");

        ContentExchange exchange = prepareExchange(HttpMethod.PUT, urlBuilder.toString());
        exchange.setRequestContent(new ByteArrayBuffer(mapper.writeValueAsString(expense).getBytes("UTF-8")));

        httpClient.send(exchange);
        int exchangeStatus = exchange.waitForDone();

        if (exchangeStatus == HttpExchange.STATUS_COMPLETED) {
            int responseStatus = exchange.getResponseStatus();
            switch (responseStatus) {
                case 201:
                    return mapper.readValue(exchange.getResponseContent(), Expense.class);
                case 420:
                    throw new MethodFailureStatusException("Http response returns METHOD FAILURE (420) status");
                default:
                    throw new StatusException("Http response returns unknown (" + responseStatus + ") status");
            }
        }

        throw new StatusException("Http exchange returns status: " + getExchangeStatusName(exchangeStatus));
    }

    @Override
    public void delete(int userId, int expenseId) throws StatusException, IOException, InterruptedException {
        StringBuilder urlBuilder = new StringBuilder(address);
        urlBuilder.append("/").append(userId).append("/expense/").append(expenseId);

        ContentExchange exchange = prepareExchange(HttpMethod.DELETE, urlBuilder.toString());
        httpClient.send(exchange);
        int exchangeStatus = exchange.waitForDone();

        if (exchangeStatus == HttpExchange.STATUS_COMPLETED) {
            int responseStatus = exchange.getResponseStatus();
            switch (responseStatus) {
                case 202:
                case 420:
                    throw new MethodFailureStatusException("Http response returns METHOD FAILURE (420) status");
                default:
                    throw new StatusException("Http response returns unknown (" + responseStatus + ") status");
            }
        }

        throw new StatusException("Http exchange returns status: " + getExchangeStatusName(exchangeStatus));
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
