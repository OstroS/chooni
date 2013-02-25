package pl.akiba.backend.service;

import pl.akiba.model.entities.FacebookUser;

/**
 * 
 * @author sobczakt
 */
public interface UserService {

    public FacebookUser getUser(long facebookId);

}
