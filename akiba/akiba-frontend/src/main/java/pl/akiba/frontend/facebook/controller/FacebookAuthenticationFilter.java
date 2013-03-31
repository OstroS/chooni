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

import pl.akiba.frontend.facebook.FacebookUserDTO;
import pl.akiba.frontend.facebook.service.FacebookLoginService;

/**
 * Filter that perform action pointed by j_spring_security
 * 
 * @author OstroS
 */
public class FacebookAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final class AuthenticationExceptionExtension extends AuthenticationException {

        private static final long serialVersionUID = -3934092171479241851L;

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
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        logger.info("Attempting authentication");

        // check if token in session matches token achieved form facebook
        String tokenFromFacebook = request.getParameter("state");
        String tokenStoredInSession = (String)request.getSession().getAttribute("uniqueFacebookLoginToken");
        logger.info("tokenFromFacebook=" + tokenFromFacebook + ", tokenStoredInSession=" + tokenStoredInSession);
        if((tokenFromFacebook != null) && (tokenStoredInSession != null) && tokenStoredInSession.equals(tokenFromFacebook)) {
            logger.info("Token matches; tokenFromFacebook=" + tokenFromFacebook + ", tokenStoredInSession=" + tokenStoredInSession);
        }
        else {
            throw new AuthenticationExceptionExtension("Token stored in session doesn't match token from facebook");
        }
        
        // user should be redirected here from facebook with *code* parameter
        if (request.getParameter("code") != null) {
            String facebookCode = request.getParameter("code");
            String facebookAccessToken = facebookLoginService.endLoginProcess(facebookCode);

            // connect to facebook using current access token
            Facebook facebook = new FacebookTemplate(facebookAccessToken);

            // fetch profile of logged user
            FacebookProfile userProfile = facebook.userOperations().getUserProfile();

            FacebookUserDTO fud = new FacebookUserDTO();
            fud.setFacebookProfileId(Long.parseLong(userProfile.getId()));
            fud.setAccessToken(facebookAccessToken);

            logger.info("Authentication attempt: " + fud);
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
