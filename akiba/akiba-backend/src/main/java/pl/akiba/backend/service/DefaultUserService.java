package pl.akiba.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import pl.akiba.backend.dao.UserDao;
import pl.akiba.model.entities.FacebookUser;
import pl.akiba.model.entities.FacebookUser.Status;
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
        Status status = null;

        try {
            facebookUser = userDao.getFacebookUser(facebookId);
            status = Status.EXISTING;
        } catch (EmptyResultDataAccessException e) {
            facebookUser = userDao.createFacebookUser(facebookId);
            status = Status.CREATED;
        }

        facebookUser.setStatus(status);

        return facebookUser;
    }

}
