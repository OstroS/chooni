package pl.akiba.wsclient.client;

import java.io.IOException;

import pl.akiba.model.entities.FacebookUser;
import pl.akiba.model.exception.StatusException;

/**
 * 
 * @author sobczakt
 */
public interface UserClient {

    /**
     * @param facebookId
     * @return {@link FacebookUser}
     * @throws StatusException
     * @throws IOException
     * @throws InterruptedException
     */
    public FacebookUser getFacebookUser(long facebookId) throws StatusException, IOException, InterruptedException;

}
