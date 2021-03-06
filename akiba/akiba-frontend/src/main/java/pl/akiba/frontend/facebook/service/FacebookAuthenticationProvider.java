package pl.akiba.frontend.facebook.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Component;

import pl.akiba.frontend.facebook.FacebookUserDTO;
import pl.akiba.frontend.users.service.UsersService;
import pl.akiba.model.entities.FacebookUser;

/**
 * Class implement authentication process. <br />
 * According to Wikipedia authentication is the act of confirming the truth of an attribute of a datum or entity. This
 * might involve confirming the identity of a person or software program, tracing the origins of an artifact, or
 * ensuring that a product is what its packaging and labeling claims to be. Authentication often involves verifying the
 * validity of at least one form of identification.
 * 
 * @author OstroS
 */
@Component("FacebookAuthenticationProvider")
public class FacebookAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    @Qualifier(value = "usersService")
    private UsersService usersService;

    private static final Logger logger = Logger.getLogger(FacebookAuthenticationProvider.class.toString());

    @Override
    public Authentication authenticate(Authentication a) throws AuthenticationException {
        FacebookUserDTO fud = (FacebookUserDTO) a.getPrincipal();
        String credentials = (String) a.getCredentials();

        // fetch user from our DB
        FacebookUser user = usersService.getByFacebookId(fud.getFacebookProfileId());

        // checking according to spring security documentation
        if (user.isDisabled()) {
            logger.info("Account disabled: " + user);
            throw new DisabledException("Konto wyłączone");
        }
        if (user.isLocked()) {
            logger.info("Account locked: " + user);
            throw new LockedException("Konto zablokowane");
        }

        // if user is allowed to access - allow him :)
        List<GrantedAuthority> authorities = usersService.getUsersAuthorities(user);
        logger.info("User granted authorities=" + authorities);

        // fetch profile of logged user and fill information from his profile
        Facebook facebook = new FacebookTemplate(fud.getAccessToken());
        FacebookProfile facebookProfile = facebook.userOperations().getUserProfile();
        fillUserData(user, facebookProfile);
        user.setAccessToken(fud.getAccessToken());

        Authentication auth = new UsernamePasswordAuthenticationToken(user, credentials, authorities);
        logger.info("Authentication completed: " + auth);
        return auth;

    }

    /**
     * Method rewrites temporary facebook profile information to FacebookUser object
     * 
     * @param user
     * @param facebookProfile
     */
    private void fillUserData(final FacebookUser user, final FacebookProfile facebookProfile) {
        user.setFacebookEmail(facebookProfile.getEmail());
        user.setFacebookFirstName(facebookProfile.getFirstName());
        user.setFacebookGener(facebookProfile.getGender());
        user.setFacebookId(Long.parseLong(facebookProfile.getId()));
        user.setFacebookLastName(facebookProfile.getLastName());
        user.setFacebookUsername(facebookProfile.getUsername());

    }

    /**
     * Verifies if given authentication Class can be handled by this authentication provider
     * 
     * @param authentication
     * @return True if so.
     */
    @Override
    public boolean supports(Class authentication) {

        return true;
    }
}
