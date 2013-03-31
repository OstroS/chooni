package pl.akiba.frontend.profiles.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import pl.akiba.model.entities.Profile;
import pl.akiba.model.entities.User;
import pl.akiba.wsclient.api.AkibaApi;

/**
 * 
 * @author OstroS
 */
@Component("profilesServiceMock")
public class ProfilesServiceMock implements ProfilesService {

    @Autowired
    @Qualifier(value = "akibaApiMock")
    AkibaApi akibaApi;

    private static final Logger logger = Logger.getLogger(ProfilesServiceMock.class.toString());

    /* (non-Javadoc)
     * @see pl.akiba.frontend.expenses.service.ProfilesService#getAll(pl.akiba.model.entities.User)
     */
    @Override
    public List<Profile> getAll(User user) {
        logger.info("Prepare profiles for user, " + user);
        return akibaApi.getProfileApi().getAll(user);
    }

    @Override
    public Profile get(User user, long profileId) {
        logger.info("Get profile by id=" + profileId);
        return akibaApi.getProfileApi().get(profileId);
    }
}
