package pl.akiba.wsclient.client;

import org.eclipse.jetty.client.HttpClient;

import pl.akiba.model.entities.FacebookUser;

/**
 * 
 * @author sobczakt
 */
public class DefaultUserClient extends DefaultClient implements UserClient {

    public DefaultUserClient(String address, HttpClient httpClient) {
        super(address, httpClient);
    }

    public DefaultUserClient(String address) {
        super(address);
    }

    @Override
    public FacebookUser getUser(long facebookId) {
        // TODO Auto-generated method stub
        return null;
    }

}
