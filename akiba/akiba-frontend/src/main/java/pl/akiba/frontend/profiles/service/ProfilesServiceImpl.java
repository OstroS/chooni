/**
 * 
 */
package pl.akiba.frontend.profiles.service;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.akiba.model.entities.Profile;
import pl.akiba.model.entities.User;
import pl.akiba.model.exception.StatusException;
import pl.akiba.wsclient.client.DefaultProfileClient;

/**
 * @author kostrows
 *
 */
@Component("profilesService")
public class ProfilesServiceImpl implements ProfilesService {

    @Autowired
    private DefaultProfileClient profileClient;
    
    private static final Logger logger = Logger.getLogger(ProfilesServiceImpl.class.toString());
    
    
    /* (non-Javadoc)
     * @see pl.akiba.frontend.expenses.service.ProfilesService#getAll(pl.akiba.model.entities.User)
     */
    @Override
    public List<Profile> getAll(User user) {
        try {
            return profileClient.getAll(user.getId(), user.getAuthenticationCode());
        } catch (StatusException | IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            logger.severe(e.toString());
        }
        return null;
    }

    /* (non-Javadoc)
     * @see pl.akiba.frontend.expenses.service.ProfilesService#getProfile(long)
     */
    @Override
    public Profile get(User user, long profileId) {
        return profileClient.get(user.getId(), user.getAuthenticationCode(), profileId);
    }

}
