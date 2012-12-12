package pl.akiba.frontend.facebook.controller;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.akiba.frontend.expenses.controller.exceptions.InternalServerErrorException;

/**
 * Proof of Concept of authentication and authorization process using OAuth
 * protocol Facebook is OAuth service Provider
 *
 * @author Ostros
 */
@Controller
@RequestMapping("/fb")
public class FacebookTest {

    /**
     * @TODO Facebook configuration constans should be moved to properties files
     */
    private final static String CLIENT_ID = "142100775940517";
    private final static String CLIENT_SECRET = "c7b1cef987324e69b9ad9fe6ebf05633";
    /**
     * Should be obtained dinamically from users context
     */
    private final static String REDIRECT_URI_AUTHORIZE = "http://mywebappthatusesfacebook.com:8080/akiba-frontend/fb/authorize";
    private final static String REDIRECT_URI_ACCESS = "http://mywebappthatusesfacebook.com:8080/akiba-frontend/fb/authorize";
    /**
     * @TODO Should be generated uniqly on each users session
     */
    private final static String SOME_ARBITRARY_BUT_UNIQUE_STRING = "f2bf92067a9ed2a9ed0147aa9a467cab";

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void facebookLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException {
  
        // build uri to redirect user when he will be logged in
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").
                setHost("www.facebook.com").
                setPath("/dialog/oauth").
                addParameter("client_id", CLIENT_ID).
                addParameter("redirect_uri", REDIRECT_URI_AUTHORIZE).
                addParameter("state", SOME_ARBITRARY_BUT_UNIQUE_STRING);

        String location = uriBuilder.build().toString();
        
        // redirect
        response.sendRedirect(location);
    }

    @RequestMapping(value = "/authorize", method = RequestMethod.GET)
    public ModelAndView facebookAuthorize(HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException {
        ModelAndView mav = new ModelAndView();
        
        // user should be redirected here from facebook with *code* parameter
        if (request.getParameter("code") != null) {
            
            String facebookCode = request.getParameter("code");

            // build uri to verify *code*
            URIBuilder uriBuilder = new URIBuilder();
            uriBuilder.setScheme("https").
                    setHost("graph.facebook.com").
                    setPath("/oauth/access_token").
                    addParameter("client_id", CLIENT_ID).
                    addParameter("redirect_uri", REDIRECT_URI_ACCESS).
                    addParameter("client_secret", CLIENT_SECRET).
                    addParameter("code", facebookCode);

            // verify *code*
            HttpGet httpget = new HttpGet(uriBuilder.build());
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse fbResponse = httpclient.execute(httpget);

            // obtain content from response and then access_token from content 
            InputStream is = fbResponse.getEntity().getContent();
            String content = CharStreams.toString(new InputStreamReader(is, Charsets.UTF_8));
            String[] parameters = content.split("&");
            String accessToken = "";
            for (String p : parameters) {
                if (p.contains("access_token")) {
                    accessToken = p.split("=")[1].trim();
                }
            }

            // connect to facebook using current access token
            Facebook facebook = new FacebookTemplate(accessToken);
            
            // fetch profile of logged user
            FacebookProfile userProfile = facebook.userOperations().getUserProfile();

            // display users profile
            mav.addObject("facebookProfile", userProfile);
            mav.setViewName("fb");
            return mav;



        } else {
            throw new InternalServerErrorException();
        }

    }
}
