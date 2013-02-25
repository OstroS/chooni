package pl.akiba.backend.dao;

import pl.akiba.model.entities.FacebookUser;

/**
 * 
 * @author sobczakt
 */
public interface UserDao {

    public FacebookUser getFacebookUser(long facebookId);

    public FacebookUser createFacebookUser(long facebookId);

    //    public GoogleUser getGoogleUser(long googleId);

    //    public GoogleUser createGoogleUser(long googleId);

}
