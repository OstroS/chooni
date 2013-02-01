package pl.akiba.frontend.users.service.mock;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import pl.akiba.frontend.model.entities.FacebookUser;
import pl.akiba.frontend.users.service.UsersService;
import pl.akiba.wsclient.api.AkibaApi;

/**
 * 
 * @author kostrows
 */
@Component("usersServiceMock")
public class UsersServiceMock implements UsersService {

    @Autowired
    @Qualifier(value = "akibaApiMock")
    AkibaApi akibaApi;

    private static final Logger logger = Logger.getLogger(UsersServiceMock.class.toString());

    @Override
    public FacebookUser getByFacebookId(Long facebookId) {
        logger.info("GetByFacebookId");
        return akibaApi.getUserApi().getByFacebookId(facebookId);
    }

}
