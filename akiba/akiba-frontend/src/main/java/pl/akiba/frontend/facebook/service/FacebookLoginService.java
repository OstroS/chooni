package pl.akiba.frontend.facebook.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import pl.akiba.frontend.facebook.FacebookConsts;
import pl.akiba.helper.StringHelper;
import pl.akiba.model.entities.FacebookUser;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;

/**
 * Service class that should be used while performing login process based on facebook
 * 
 * @author kostrows
 */
@Component("FacebookLoginService")
public class FacebookLoginService {

    @Value("${conf.facebook.clientId}")
    private String CLIENT_ID;

    @Value("${conf.facebook.clientSecret}")
    private String CLIENT_SECRET;

    @Value("${conf.facebook.redirectUri}")
    private String REDIRECT_URI;

    private static final Logger logger = Logger.getLogger(FacebookLoginService.class.toString());

    /**
     * Method should be called to begin login process based on facebook OAuth model. <br />
     * User should be redirected (using http 302 moved temporarily) to location given as a returned value.
     * 
     * @return Location where user should be redirected
     * @throws URISyntaxException
     */
    public String beginLoginProcess(String uniqueTokenForCurrentUser) throws URISyntaxException {

        // build uri to redirect user when he will be logged in
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme(FacebookConsts.URI_SCHEME).setHost("www.facebook.com").setPath("/dialog/oauth")
                .addParameter(FacebookConsts.PARAM_CLIENT_ID, CLIENT_ID)
                .addParameter(FacebookConsts.PARAM_REDIRECT_URI, REDIRECT_URI)
                .addParameter(FB_PARAM_STATE(), uniqueTokenForCurrentUser);

        return uriBuilder.build().toString();
    }

    /**
     * Method should be called to finalize login process.
     * 
     * @param facebookCode
     *            Code obtained as a result value from method beginLoginProcess()
     * @return Access Token that should be used in further facebook account operation
     */
    public String endLoginProcess(String facebookCode) {

        // build uri to verify *code*
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme(FacebookConsts.URI_SCHEME).setHost(FacebookConsts.GRAPH_HOST)
                .setPath("/oauth/access_token").addParameter(FacebookConsts.PARAM_CLIENT_ID, CLIENT_ID)
                .addParameter(FacebookConsts.PARAM_REDIRECT_URI, REDIRECT_URI)
                .addParameter(FacebookConsts.PARAM_CLIENT_SECRET, CLIENT_SECRET)
                .addParameter(FacebookConsts.PARAM_CODE, facebookCode);

        try {
            // verify *code*
            HttpGet httpget = new HttpGet(uriBuilder.build());

            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse fbResponse = httpclient.execute(httpget);

            String content = getContentString(fbResponse);

            return getAccessTokenFromKeyValueString(content);
        } catch (URISyntaxException | IOException | IllegalStateException ex) {
            // @FIXME
            ex.printStackTrace();
        }
        // @FIXME        
        return "";

    }

    /**
     * Method send logout requst directly to facebook <br />
     * hen using the server-side Login flow, this process will involve revoking Permissions for a user from that app and
     * removing any stored session information. <br />
     * More information on facebook logout process:
     * https://developers.facebook.com/docs/howtos/login/server-side-logout/
     * 
     * @param user
     *            Users to be logged out
     * @return
     */
    public int logoutFacebookUser(FacebookUser user) {
        logger.info("Logout facebook user: " + user);
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme(FacebookConsts.URI_SCHEME).setHost(FacebookConsts.GRAPH_HOST)
                .setPath(FacebookConsts.LOGOUT_PATH).setParameter("method", "delete")
                .setParameter(FacebookConsts.PARAM_ACCESS_TOKEN, user.getAccessToken());

        try {
            URI uri = uriBuilder.build();
            HttpDelete httpdelte = new HttpDelete(uri);

            logger.info("Invoking... " + uri);
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse fbResponse = httpclient.execute(httpdelte);

            logger.info("FB response: " + fbResponse.getStatusLine());

        } catch (URISyntaxException | IOException | IllegalStateException ex) {
            // @FIXME
            ex.printStackTrace();
        }

        return 0;
    }

    private String getAccessTokenFromKeyValueString(String content) {
        StringHelper stringHelper = new StringHelper();
        return stringHelper.getValue(content, FacebookConsts.PARAM_ACCESS_TOKEN);
    }

    private String FB_PARAM_STATE() {
        return "state";
    }

    private String getContentString(HttpResponse fbResponse) throws IOException, IllegalStateException {
        // obtain content from response and then access_token from content
        InputStream is = fbResponse.getEntity().getContent();
        String content = CharStreams.toString(new InputStreamReader(is, Charsets.UTF_8));
        return content;
    }
}
