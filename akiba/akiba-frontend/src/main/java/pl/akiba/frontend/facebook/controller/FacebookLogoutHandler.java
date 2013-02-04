/**
 * 
 */
package pl.akiba.frontend.facebook.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import pl.akiba.frontend.facebook.service.FacebookLoginService;
import pl.akiba.frontend.model.entities.FacebookUser;

/**
 * @author OstroS
 *
 */
public class FacebookLogoutHandler implements LogoutHandler {

    private static final Logger logger = Logger.getLogger(FacebookAuthenticationFilter.class.toString());
    
    @Autowired
    private FacebookLoginService fls;
    
    /* (non-Javadoc)
     * @see org.springframework.security.web.authentication.logout.LogoutHandler#logout(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
     */
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        logger.info("logout user");
        Object principal = authentication.getPrincipal();
        if(principal instanceof FacebookUser) {
            fls.logoutFacebookUser((FacebookUser)principal);
        }
    }

}
