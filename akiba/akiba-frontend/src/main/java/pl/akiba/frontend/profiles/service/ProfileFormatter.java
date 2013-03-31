package pl.akiba.frontend.profiles.service;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import pl.akiba.frontend.expenses.service.UserHelper;
import pl.akiba.model.entities.Profile;
import pl.akiba.model.entities.User;

/**
 * 
 * @author OstroS
 */
@Component("profileFormatter")
public class ProfileFormatter implements Formatter<Profile> {

    @Autowired
    ProfilesService profilesService;
    
    @Autowired
    UserHelper userHelper;

    @Override
    public String print(Profile t, Locale locale) {
        return t.toString();
    }

    @Override
    public Profile parse(String profileId, Locale locale) throws ParseException {
        // FIXME Possibly may cause error in multithreding enviroment
        User currentUser = userHelper.getCurrentUser(SecurityContextHolder.getContext().getAuthentication());
        return profilesService.get(currentUser, Integer.parseInt(profileId));
    }

    public void setProfilesService(ProfilesService ps) {
        this.profilesService = ps;
    }

}
