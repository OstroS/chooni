package pl.akiba.frontend.facebook.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

    private FacebookLoginService facebookLoginService;

    public FacebookLoginService getFacebookLoginService() {
        return facebookLoginService;
    }

    public void setFacebookLoginService(FacebookLoginService fbl) {
        this.facebookLoginService = fbl;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("FacebookAuthenticationFilter: attemptAuthentication");

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
            throw new AuthenticationException("Cannot find _code_ parameter") {
            };
        }

    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        System.out.println("FacebookAuthenticationFilter: obtainPassword");
        return super.obtainPassword(request);
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        System.out.println("FacebookAuthenticationFilter: obtainUsername");
        return super.obtainUsername(request);
    }

    @Override
    protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
        System.out.println("FacebookAuthenticationFilter: setDetails");
        super.setDetails(request, authRequest);
    }

    @Override
    public void setUsernameParameter(String usernameParameter) {
        System.out.println("FacebookAuthenticationFilter: setUsernameParameter");
        super.setUsernameParameter(usernameParameter);
    }

    @Override
    public void setPasswordParameter(String passwordParameter) {
        System.out.println("FacebookAuthenticationFilter: setPasswordParameter");
        super.setPasswordParameter(passwordParameter);
    }

    @Override
    public void setPostOnly(boolean postOnly) {
        System.out.println("FacebookAuthenticationFilter: postOnly");
        super.setPostOnly(postOnly);
    }
}
