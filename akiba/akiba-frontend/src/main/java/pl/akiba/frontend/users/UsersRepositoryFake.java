package pl.akiba.frontend.users;

import java.util.Random;
import org.springframework.stereotype.Component;

import pl.akiba.frontend.model.entities.FacebookUser;

/**
 *
 * @author kostrows
 */
@Component("UsersRepositoryFake")
public class UsersRepositoryFake implements UsersRepository {

    @Override
    public FacebookUser getByFacebookId(String facebookId) {
        return createDefaultFacebookUser(facebookId);
        
    }

    private FacebookUser createDefaultFacebookUser(String facebookId) {
        FacebookUser fUser = new FacebookUser();
        fUser.setFacebookId(facebookId);
        fUser.setId(new Random().nextLong());
        return fUser;
    }
 

}
