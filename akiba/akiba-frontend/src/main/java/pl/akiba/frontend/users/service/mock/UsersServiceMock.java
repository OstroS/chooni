package pl.akiba.frontend.users.service.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import pl.akiba.frontend.users.service.UsersService;
import pl.akiba.model.entities.FacebookUser;
import pl.akiba.model.entities.User;
import pl.akiba.wsclient.api.AkibaApi;

/**
 * 
 * @author kostrows
 */
@Component("usersServiceMock")
public class UsersServiceMock implements UsersService {

    private static final String ROLE_USER = "ROLE_USER";

    @Autowired
    @Qualifier(value = "akibaApiMock")
    AkibaApi akibaApi;

    private static final Logger logger = Logger.getLogger(UsersServiceMock.class.toString());

    @Override
    public FacebookUser getByFacebookId(Long facebookId) {
        logger.info("GetByFacebookId");
        return akibaApi.getUserApi().getByFacebookId(facebookId);
    }

    @Override
    public List<GrantedAuthority> getUsersAuthorities(User user) {
        List<GrantedAuthority> grantedAutorities = new ArrayList<>();
        grantedAutorities.add(new SimpleGrantedAuthority(ROLE_USER));

        return grantedAutorities;
    }

}
