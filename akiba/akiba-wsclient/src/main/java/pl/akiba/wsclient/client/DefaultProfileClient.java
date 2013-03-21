package pl.akiba.wsclient.client;

import java.io.IOException;
import java.util.List;

import org.eclipse.jetty.client.ContentExchange;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.HttpExchange;

import pl.akiba.model.entities.Profile;
import pl.akiba.model.entities.User;
import pl.akiba.model.exception.MethodFailureStatusException;
import pl.akiba.model.exception.NotFoundStatusException;
import pl.akiba.model.exception.StatusException;
import pl.akiba.model.service.ProfileService;

/**
 * 
 * @author sobczakt
 */
public class DefaultProfileClient extends DefaultClient implements ProfileService {

    protected DefaultProfileClient(String address, HttpClient httpClient) {
        super(address, httpClient);
    }

    @Override
    public List<Profile> getAll(String authCode) throws StatusException, IOException, InterruptedException {
        StringBuilder urlBuilder = prepareBasicUrl(authCode).append("/profile");

        ContentExchange exchange = sendExchange(HttpMethod.GET, urlBuilder.toString(), null);
        int exchangeStatus = exchange.waitForDone();

        if (exchangeStatus == HttpExchange.STATUS_COMPLETED) {
            int responseStatus = exchange.getResponseStatus();
            switch (responseStatus) {
                case 200:
                    return mapJsonToEntities(exchange.getResponseContent(), Profile.class);
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
    public Profile getDefault(String authCode) throws StatusException, IOException, InterruptedException {
        StringBuilder urlBuilder = prepareBasicUrl(authCode).append("/profile/default");

        ContentExchange exchange = sendExchange(HttpMethod.GET, urlBuilder.toString(), null);
        int exchangeStatus = exchange.waitForDone();

        if (exchangeStatus == HttpExchange.STATUS_COMPLETED) {
            int responseStatus = exchange.getResponseStatus();
            switch (responseStatus) {
                case 200:
                    return mapper.readValue(exchange.getResponseContent(), Profile.class);
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
    public Profile create(String authCode, Profile profile) throws StatusException, IOException, InterruptedException {
        StringBuilder urlBuilder = prepareBasicUrl(authCode).append("/profile");

        ContentExchange exchange = sendExchange(HttpMethod.POST, urlBuilder.toString(), profile);
        int exchangeStatus = exchange.waitForDone();

        if (exchangeStatus == HttpExchange.STATUS_COMPLETED) {
            int responseStatus = exchange.getResponseStatus();
            switch (responseStatus) {
                case 201:
                    return mapper.readValue(exchange.getResponseContent(), Profile.class);
                case 420:
                    throw new MethodFailureStatusException("Http response returns METHOD FAILURE (420) status");
                default:
                    throw new StatusException("Http response returns unknown (" + responseStatus + ") status");
            }
        }

        throw new StatusException("Http exchange returns status: " + getExchangeStatusName(exchangeStatus));
    }

    @Override
    public void update(String authCode, Profile profile) throws StatusException, IOException, InterruptedException {
        StringBuilder urlBuilder = prepareBasicUrl(authCode).append("/profile");

        ContentExchange exchange = sendExchange(HttpMethod.PUT, urlBuilder.toString(), profile);
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
    public void delete(String authCode, int profileId) throws StatusException, IOException, InterruptedException {
        StringBuilder urlBuilder = prepareBasicUrl(authCode).append("/profile/").append(profileId);

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
