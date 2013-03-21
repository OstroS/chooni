package pl.akiba.wsclient.client;

import java.io.IOException;
import java.util.List;

import org.eclipse.jetty.client.ContentExchange;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.HttpExchange;

import pl.akiba.model.entities.Expense;
import pl.akiba.model.entities.Filter;
import pl.akiba.model.entities.User;
import pl.akiba.model.exception.MethodFailureStatusException;
import pl.akiba.model.exception.NotFoundStatusException;
import pl.akiba.model.exception.StatusException;
import pl.akiba.model.service.ExpenseService;

/**
 * TODO spring messages in exceptions!
 * 
 * @author sobczakt
 */
public class DefaultExpenseClient extends DefaultClient implements ExpenseService {

    public DefaultExpenseClient(String address, HttpClient httpClient) {
        super(address, httpClient); 
    }

    @Override
    public Expense get(User user, int expenseId) throws StatusException, IOException, InterruptedException {
        StringBuilder urlBuilder = prepareBasicUrl(user.getAuthenticationCode()).append("/expense/").append(expenseId);

        ContentExchange exchange = sendExchange(HttpMethod.GET, urlBuilder.toString(), null);
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
    public List<Expense> getAll(User user, Filter filter) throws StatusException, IOException, InterruptedException {
        StringBuilder urlBuilder = prepareBasicUrl(user.getAuthenticationCode()).append("/expense");
        if (filter != null) {
            urlBuilder.append(filter.getFilterString());
        }

        ContentExchange exchange = sendExchange(HttpMethod.GET, urlBuilder.toString(), null);
        int exchangeStatus = exchange.waitForDone();

        if (exchangeStatus == HttpExchange.STATUS_COMPLETED) {
            int responseStatus = exchange.getResponseStatus();
            switch (responseStatus) {
                case 200:
                    return mapJsonToEntities(exchange.getResponseContent(), Expense.class);
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
    public double getTotal(User user, Filter filter) throws StatusException, IOException, InterruptedException {
        StringBuilder urlBuilder = prepareBasicUrl(user.getAuthenticationCode()).append("/expense/total");
        if (filter != null) {
            urlBuilder.append(filter.getFilterString());
        }

        ContentExchange exchange = sendExchange(HttpMethod.GET, urlBuilder.toString(), null);
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
    public Expense create(User user, Expense expense) throws StatusException, IOException, InterruptedException {
        StringBuilder urlBuilder = prepareBasicUrl(user.getAuthenticationCode()).append("/expense");

        ContentExchange exchange = sendExchange(HttpMethod.POST, urlBuilder.toString(), expense);
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
    public Expense update(User user, Expense expense) throws StatusException, IOException, InterruptedException {
        StringBuilder urlBuilder = prepareBasicUrl(user.getAuthenticationCode()).append("/expense");

        ContentExchange exchange = sendExchange(HttpMethod.PUT, urlBuilder.toString(), expense);
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
    public void delete(User user, int expenseId) throws StatusException, IOException, InterruptedException {
        StringBuilder urlBuilder = prepareBasicUrl(user.getAuthenticationCode()).append("/expense/").append(expenseId);

        ContentExchange exchange = sendExchange(HttpMethod.DELETE, urlBuilder.toString(), null);
        int exchangeStatus = exchange.waitForDone();

        if (exchangeStatus == HttpExchange.STATUS_COMPLETED) {
            int responseStatus = exchange.getResponseStatus();
            switch (responseStatus) {
                case 420:
                    throw new MethodFailureStatusException("Http response returns METHOD FAILURE (420) status");
                default:
                    throw new StatusException("Http response returns unknown (" + responseStatus + ") status");
            }
        }

        throw new StatusException("Http exchange returns status: " + getExchangeStatusName(exchangeStatus));
    }

}
