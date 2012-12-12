package pl.akiba.frontend.facebook.service;

import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.stereotype.Component;

/**
 * Formats user profile from Facebook
 * @author OstroS
 */
@Component("facebookProfileFormatter")
public class FacebookProfileFormatter implements Formatter <FacebookProfile> {

    @Override
    public String print(FacebookProfile facebookProfile, Locale locale) {
        StringBuilder profile = new StringBuilder();
        profile.append("Facebook profile = {");
        profile.append("First name=").append(facebookProfile.getFirstName());
        profile.append(", Last name=").append(facebookProfile.getLastName());
        profile.append(", Gender=").append(facebookProfile.getGender());
        profile.append(", Email=").append(facebookProfile.getEmail());
        profile.append(", Birthday=").append(facebookProfile.getBirthday());
        profile.append(", Hometown=").append(facebookProfile.getHometown().getName());
        profile.append(", Location=").append(facebookProfile.getLocation().getName());
        profile.append("}");
        
        return profile.toString();
    }
    
    @Override
    public FacebookProfile parse(String string, Locale locale) throws ParseException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
