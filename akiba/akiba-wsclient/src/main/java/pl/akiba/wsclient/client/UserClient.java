package pl.akiba.wsclient.client;

import pl.akiba.model.entities.FacebookUser;

public interface UserClient {

    public FacebookUser getUser(long facebookId);

}
