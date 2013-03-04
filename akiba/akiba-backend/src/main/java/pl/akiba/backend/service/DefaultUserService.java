package pl.akiba.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import pl.akiba.backend.dao.UserDao;
import pl.akiba.model.entities.FacebookUser;
import pl.akiba.model.service.UserService;

/**
 * 
 * @author sobczakt
 */
@Service
public class DefaultUserService implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public FacebookUser getFacebookUser(long facebookId) {
        FacebookUser facebookUser = null;

        try {
            facebookUser = userDao.getFacebookUser(facebookId);
        } catch (EmptyResultDataAccessException e) {
            facebookUser = userDao.createFacebookUser(facebookId);
        }

        return facebookUser;
    }

}
