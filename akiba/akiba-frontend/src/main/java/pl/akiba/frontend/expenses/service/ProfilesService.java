package pl.akiba.frontend.expenses.service;

import com.google.common.collect.Lists;
import java.util.List;
import pl.akiba.model.entities.Profile;
import pl.akiba.model.entities.User;

/**
 *
 * @author OstroS
 */
public class ProfilesService {
     /**
     * TODO Mock method
     * 
     * @param user Current user
     * @return List of profiles for given user
     */
    public List<Profile> prepareProfilesForUser(User user) {
        return Lists.newArrayList(new Profile(0, "Prywanty"),
                                  new Profile(1, "Biznes"),
                                  new Profile(2, "Rodzinny"));
    }
}
