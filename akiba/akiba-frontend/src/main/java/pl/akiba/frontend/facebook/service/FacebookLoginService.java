package pl.akiba.frontend.facebook.service;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Service class that should be used while performing login process based on facebook
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
    
    /**
     * @TODO @FIXME
     */
    private final static String SOME_ARBITRARY_BUT_UNIQUE_STRING = "f2bf92067a9ed2a9ed0147aa9a467cab";

    /**
     * Method should be called to begin login process based on facebook OAuth
     * model. <br /> User should be redirected (using http 302 moved
     * temporarily) to location given as a returned value.
     *
     * @return Location where user should be redirected
     * @throws URISyntaxException
     */
    public String beginLoginProcess() throws URISyntaxException {

        // build uri to redirect user when he will be logged in
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").
                setHost("www.facebook.com").
                setPath("/dialog/oauth").
                addParameter("client_id", CLIENT_ID).
                addParameter("redirect_uri", REDIRECT_URI).
                addParameter("state", SOME_ARBITRARY_BUT_UNIQUE_STRING);

        return uriBuilder.build().toString();
    }

    /**
     * Method should be called to finalize login process.
     * @param facebookCode Code obtained as a result value from method beginLoginProcess()
     * @return Access Token that should be used in further facebook account operation
     */
    public String endLoginProcess(String facebookCode) {

        // build uri to verify *code*
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").
                setHost("graph.facebook.com").
                setPath("/oauth/access_token").
                addParameter("client_id", CLIENT_ID).
                addParameter("redirect_uri", REDIRECT_URI).
                addParameter("client_secret", CLIENT_SECRET).
                addParameter("code", facebookCode);

        try {
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

            return accessToken;
        } catch (URISyntaxException | IOException | IllegalStateException ex) {
            // @FIXME
            ex.printStackTrace();
        }
        // @FIXME        
        return "";

    }
}
