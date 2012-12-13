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

/**
 * Filter that perform action pointed by j_spring_security
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
            System.out.println("User logged in: " + userProfile.getName());

            /** FiXME - przekazac prawidziwego uzytkownika!!! **/
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            
            Authentication a;
            a = new UsernamePasswordAuthenticationToken("password", "username", authorities);

            return a;
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
