package pl.akiba.frontend.users.service;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import pl.akiba.model.entities.FacebookUser;
import pl.akiba.model.entities.User;

/**
 * 
 * @author kostrows
 */
public interface UsersService {

    /**
     * Method should return object that represent user logged via facebook login module
     * 
     * @param facebookId
     * @return
     */
    FacebookUser getByFacebookId(Long facebookId);

    /**
     * Method should return list of authorities granted for given user. <br />
     * Access control in whole system in based on authorities.
     * 
     * @param user
     * @return
     */
    List<GrantedAuthority> getUsersAuthorities(User user);

}
