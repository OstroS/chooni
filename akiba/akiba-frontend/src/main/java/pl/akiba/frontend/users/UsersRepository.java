package pl.akiba.frontend.users;

import pl.akiba.frontend.model.entities.FacebookUser;

/**
 *
 * @author kostrows
 */
public interface UsersRepository {

    FacebookUser getByFacebookId(String facebookId);
    
}
