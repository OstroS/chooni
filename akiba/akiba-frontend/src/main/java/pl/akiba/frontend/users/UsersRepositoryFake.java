package pl.akiba.frontend.users;

import java.util.Random;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;

import pl.akiba.frontend.model.entities.FacebookUser;

/**
 *
 * @author kostrows
 */
@Component("UsersRepositoryFake")
public class UsersRepositoryFake implements UsersRepository {

    private static final Logger logger = Logger.getLogger(UsersRepositoryFake.class.toString());
    @Override
    public FacebookUser getByFacebookId(String facebookId) {
        logger.info("GetByFacebookId");
        return createDefaultFacebookUser(facebookId);
        
    }

    private FacebookUser createDefaultFacebookUser(String facebookId) {
        logger.info("CreateDefaultFacebookUser");
        FacebookUser fUser = new FacebookUser();
        fUser.setFacebookId(facebookId);
        fUser.setId(new Random().nextLong());
        return fUser;
    }
 

}
