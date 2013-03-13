package pl.akiba.wsclient.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.eclipse.jetty.client.ContentExchange;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.HttpExchange;

import pl.akiba.model.entities.FacebookUser;
import pl.akiba.model.exception.MethodFailureStatusException;
import pl.akiba.model.exception.StatusException;
import pl.akiba.model.service.UserService;

/**
 * 
 * @author sobczakt
 */
public class DefaultUserClient extends DefaultClient implements UserService {

    public DefaultUserClient(String address, HttpClient httpClient) {
        super(address, httpClient);
    }

    @Override
    public FacebookUser getFacebookUser(long facebookId) throws StatusException, IOException, InterruptedException {
        StringBuilder urlBuilder = new StringBuilder(address);
        urlBuilder.append("/user/fb/").append(facebookId);

        ContentExchange exchange = sendExchange(HttpMethod.GET, urlBuilder.toString(), null);
        int exchangeStatus = exchange.waitForDone();

        if (exchangeStatus == HttpExchange.STATUS_COMPLETED) {
            int responseStatus = exchange.getResponseStatus();
            switch (responseStatus) {
                case 200:
                    return returnFacebookUser(exchange);
                case 201:
                    return returnFacebookUser(exchange);
                case 420:
                    throw new MethodFailureStatusException("Http response returns METHOD FAILURE (420) status");
                default:
                    throw new StatusException("Http response returns unknown (" + responseStatus + ") status");
            }
        }

        throw new StatusException("Http exchange returns status: " + getExchangeStatusName(exchangeStatus));
    }

    private FacebookUser returnFacebookUser(ContentExchange exchange) throws IOException, JsonParseException,
            JsonMappingException, UnsupportedEncodingException {
        return mapper.readValue(exchange.getResponseContent(), FacebookUser.class);
    }

}
