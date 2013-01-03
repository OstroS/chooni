package pl.akiba.frontend.expenses.service;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import pl.akiba.model.entities.Profile;

/**
 * 
 * @author OstroS
 */
@Component("profileFormatter")
public class ProfileFormatter implements Formatter<Profile> {

    @Autowired
    ProfilesService profilesService;

    @Override
    public String print(Profile t, Locale locale) {
        return t.toString();
    }

    @Override
    public Profile parse(String profileId, Locale locale) throws ParseException {
        return profilesService.getProfile(Integer.parseInt(profileId));
    }

    public void setProfilesService(ProfilesService ps) {
        this.profilesService = ps;
    }

}
