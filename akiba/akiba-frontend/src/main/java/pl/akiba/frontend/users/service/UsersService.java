package pl.akiba.frontend.users.service;

import pl.akiba.frontend.model.entities.FacebookUser;

/**
 *
 * @author kostrows
 */
public interface UsersService {

    FacebookUser getByFacebookId(Long facebookId);
    
}
