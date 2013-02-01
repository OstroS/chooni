package pl.akiba.frontend.facebook.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Component;

import pl.akiba.frontend.model.entities.FacebookUser;
import pl.akiba.frontend.users.UsersRepository;

/**
 *
 * @author OstroS
 */
@Component("FacebookAuthenticationProvider")
public class FacebookAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Authentication authenticate(Authentication a) throws AuthenticationException {
        FacebookUserDTO fud = (FacebookUserDTO) a.getPrincipal();
        String credentials = (String) a.getCredentials();

        // fetch user from our DB
        FacebookUser user = usersRepository.getByFacebookId(fud.getFacebookProfileId());
                
        // checking according to spring security documentation
        if (user.isDisabled()) {
            throw new DisabledException("Konto wyłączone");
        }
        if (user.isLocked()) {
            throw new LockedException("Konto zablokowane");
        }

        // if user is allowed to access - allow him :)
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        // fetch profile of logged user and fill information from his profile
        Facebook facebook = new FacebookTemplate(fud.getAccessToken());
        FacebookProfile facebookProfile = facebook.userOperations().getUserProfile();
        fillUserData(user, facebookProfile);
        
        Authentication auth = new UsernamePasswordAuthenticationToken(user, credentials, authorities);
        return auth;

    }

    /**
     * Method rewrites temporary facebook profile information to FacebookUser object
     * @param user 
     * @param facebookProfile
     */
    private void fillUserData(final FacebookUser user, final FacebookProfile facebookProfile) {
        user.setFacebookEmail(facebookProfile.getEmail());
        user.setFacebookFirstName(facebookProfile.getFirstName());
        user.setFacebookGener(facebookProfile.getGender());
        user.setFacebookId(facebookProfile.getId());
        user.setFacebookLastName(facebookProfile.getLastName());
        user.setFacebookUsername(facebookProfile.getUsername());
      
    }

    /**
     * Verifies if given authentication Class can be handled by this authentication provider
     * @param authentication
     * @return True if so.
     */
    @Override
    public boolean supports(Class authentication) {

        return true;
    }
}
