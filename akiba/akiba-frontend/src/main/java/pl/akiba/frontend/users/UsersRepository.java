package pl.akiba.frontend.users;

import org.springframework.stereotype.Component;
import pl.akiba.model.entities.FacebookUser;

/**
 *
 * @author kostrows
 */
@Component
public class UsersRepository {

    public FacebookUser getByFacebookId(String facebookId) {
        return new FacebookUser();
    }
 

}
