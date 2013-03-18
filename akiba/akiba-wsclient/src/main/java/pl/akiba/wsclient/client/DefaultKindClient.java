package pl.akiba.wsclient.client;

import java.io.IOException;
import java.util.List;

import org.eclipse.jetty.client.ContentExchange;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.HttpExchange;

import pl.akiba.model.entities.Kind;
import pl.akiba.model.exception.MethodFailureStatusException;
import pl.akiba.model.exception.NotFoundStatusException;
import pl.akiba.model.exception.StatusException;
import pl.akiba.model.service.KindService;

/**
 * 
 * @author sobczakt
 */
public class DefaultKindClient extends DefaultClient implements KindService {

    public DefaultKindClient(String address, HttpClient httpClient) {
        super(address, httpClient);
    }

    @Override
    public Kind get(long userId, int kindId) throws StatusException, IOException, InterruptedException {
        StringBuilder urlBuilder = prepareBasicUrl(userId).append("/kind/").append(kindId);

        ContentExchange exchange = sendExchange(HttpMethod.GET, urlBuilder.toString(), null);
        int exchangeStatus = exchange.waitForDone();

        if (exchangeStatus == HttpExchange.STATUS_COMPLETED) {
            int responseStatus = exchange.getResponseStatus();
            switch (responseStatus) {
                case 200:
                    return mapper.readValue(exchange.getResponseContent(), Kind.class);
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
    public List<Kind> getAll(long userId) throws StatusException, IOException, InterruptedException {
        StringBuilder urlBuilder = prepareBasicUrl(userId).append("/kind");

        ContentExchange exchange = sendExchange(HttpMethod.GET, urlBuilder.toString(), null);
        int exchangeStatus = exchange.waitForDone();

        if (exchangeStatus == HttpExchange.STATUS_COMPLETED) {
            int responseStatus = exchange.getResponseStatus();
            switch (responseStatus) {
                case 200:
                    return mapJsonToEntities(exchange.getResponseContent(), Kind.class);
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
    public Kind create(long userId, Kind kind) throws StatusException, IOException, InterruptedException {
        StringBuilder urlBuilder = prepareBasicUrl(userId).append("/kind");

        ContentExchange exchange = sendExchange(HttpMethod.POST, urlBuilder.toString(), kind);
        int exchangeStatus = exchange.waitForDone();

        if (exchangeStatus == HttpExchange.STATUS_COMPLETED) {
            int responseStatus = exchange.getResponseStatus();
            switch (responseStatus) {
                case 201:
                    return mapper.readValue(exchange.getResponseContent(), Kind.class);
                case 420:
                    throw new MethodFailureStatusException("Http response returns METHOD FAILURE (420) status");
                default:
                    throw new StatusException("Http response returns unknown (" + responseStatus + ") status");
            }
        }

        throw new StatusException("Http exchange returns status: " + getExchangeStatusName(exchangeStatus));
    }

    @Override
    public void update(long userId, Kind kind) throws StatusException, IOException, InterruptedException {
        StringBuilder urlBuilder = prepareBasicUrl(userId).append("/kind");

        ContentExchange exchange = sendExchange(HttpMethod.PUT, urlBuilder.toString(), kind);
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

    @Override
    public void delete(long userId, int kindId) throws StatusException, IOException, InterruptedException {
        StringBuilder urlBuilder = prepareBasicUrl(userId).append("/kind/").append(kindId);

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
