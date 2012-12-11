package pl.akiba.frontend.expenses.service;

import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import pl.akiba.model.entities.Profile;

/**
 *
 * @author OstroS
 */
@Component("profileFormatter")
public class ProfileFormatter implements Formatter<Profile> {

    @Override
    public String print(Profile t, Locale locale) {
        return t.toString();
    }

    @Override
    public Profile parse(String profileId, Locale locale) throws ParseException {
        ProfilesService ps = new ProfilesService();
        return ps.getProfile(Integer.parseInt(profileId));
    }
    
}
