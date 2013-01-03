package pl.akiba.frontend.expenses.service;

import java.util.List;

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
@Component("profilesService")
public class ProfilesService {

    @Autowired
    @Qualifier(value="akibaApiMock")
    AkibaApi akibaApi;

    /**
     * TODO Mock method
     * 
     * @param user
     *            Current user
     * @return List of profiles for given user
     */
    public List<Profile> prepareProfilesForUser(User user) {
        return akibaApi.getProfileApi().getAll(user);
    }

    Profile getProfile(long profileId) {
        return akibaApi.getProfileApi().get(profileId);
    }
}
