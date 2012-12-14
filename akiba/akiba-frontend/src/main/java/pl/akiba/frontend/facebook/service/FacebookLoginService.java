package pl.akiba.frontend.facebook.service;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
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
import pl.akiba.helper.StringHelper;

/**
 * Service class that should be used while performing login process based on facebook
 * @author kostrows
 */
@Component("FacebookLoginService")
public class FacebookLoginService {
    public static final String FB_URI_SCHEME = "https";
    public static final String FB_PARAM_CLIENT_ID = "client_id";
    public static final String FB_PARAM_REDIRECT_URI = "redirect_uri";
    public static final String FB_PARAM_CLIENT_SECRET = "client_secret";
    public static final String FB_PARAM_CODE = "code";

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
        uriBuilder.setScheme(FB_URI_SCHEME).
                setHost("www.facebook.com").
                setPath("/dialog/oauth").
                addParameter(FB_PARAM_CLIENT_ID, CLIENT_ID).
                addParameter(FB_PARAM_REDIRECT_URI, REDIRECT_URI).
                addParameter(FB_PARAM_STATE(), SOME_ARBITRARY_BUT_UNIQUE_STRING);

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
        uriBuilder.setScheme(FB_URI_SCHEME).
                setHost("graph.facebook.com").
                setPath("/oauth/access_token").
                addParameter(FB_PARAM_CLIENT_ID, CLIENT_ID).
                addParameter(FB_PARAM_REDIRECT_URI, REDIRECT_URI).
                addParameter(FB_PARAM_CLIENT_SECRET, CLIENT_SECRET).
                addParameter(FB_PARAM_CODE, facebookCode);

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

    private String getAccessTokenFromKeyValueString(String content) {
        StringHelper stringHelper = new StringHelper();
        return stringHelper.getValue(content, "access_token");
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
