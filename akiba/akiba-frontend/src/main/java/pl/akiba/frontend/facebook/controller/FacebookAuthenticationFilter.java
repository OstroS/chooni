package pl.akiba.frontend.facebook.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import pl.akiba.frontend.facebook.service.FacebookLoginService;
import pl.akiba.frontend.facebook.service.FacebookUserDTO;

/**
 * Filter that perform action pointed by j_spring_security
 *
 * @author OstroS
 */
public class FacebookAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final class AuthenticationExceptionExtension extends AuthenticationException {
        private AuthenticationExceptionExtension(String msg) {
            super(msg);
        }
    }

    private FacebookLoginService facebookLoginService;
    
    private static final Logger logger = Logger.getLogger(FacebookAuthenticationFilter.class.toString());

    public FacebookLoginService getFacebookLoginService() {
        return facebookLoginService;
    }

    public void setFacebookLoginService(FacebookLoginService fbl) {
        this.facebookLoginService = fbl;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        logger.info("FacebookAuthenticationFilter: attemptAuthentication");

        // user should be redirected here from facebook with *code* parameter
        if (request.getParameter("code") != null) {
            String facebookCode = request.getParameter("code");
            String facebookAccessToken = facebookLoginService.endLoginProcess(facebookCode);

            // connect to facebook using current access token
            Facebook facebook = new FacebookTemplate(facebookAccessToken);

            // fetch profile of logged user
            FacebookProfile userProfile = facebook.userOperations().getUserProfile();

            FacebookUserDTO fud = new FacebookUserDTO();
            fud.setFacebookProfileId(userProfile.getId());
            fud.setAccessToken(facebookAccessToken);

            return new UsernamePasswordAuthenticationToken(fud, "cred");

        } else {
            throw new AuthenticationExceptionExtension("Cannot find _code_ parameter");
        }

    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        logger.info("FacebookAuthenticationFilter: obtainPassword");
        return super.obtainPassword(request);
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        logger.info("FacebookAuthenticationFilter: obtainUsername");
        return super.obtainUsername(request);
    }

    @Override
    protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
        logger.info("FacebookAuthenticationFilter: setDetails");
        super.setDetails(request, authRequest);
    }

    @Override
    public void setUsernameParameter(String usernameParameter) {
        logger.info("FacebookAuthenticationFilter: setUsernameParameter");
        super.setUsernameParameter(usernameParameter);
    }

    @Override
    public void setPasswordParameter(String passwordParameter) {
        logger.info("FacebookAuthenticationFilter: setPasswordParameter");
        super.setPasswordParameter(passwordParameter);
    }

    @Override
    public void setPostOnly(boolean postOnly) {
        logger.info("FacebookAuthenticationFilter: postOnly");
        super.setPostOnly(postOnly);
    }
}