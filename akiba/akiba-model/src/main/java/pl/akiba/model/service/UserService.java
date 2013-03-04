package pl.akiba.model.service;

import java.io.IOException;

import pl.akiba.model.entities.FacebookUser;
import pl.akiba.model.exception.StatusException;

/**
 * 
 * @author sobczakt
 */
public interface UserService {

    public FacebookUser getFacebookUser(long facebookId) throws StatusException, IOException, InterruptedException;

}
