package pl.akiba.frontend.facebook;

/**
 * Constants used in communication with facebook.
 * @author kostrows
 *
 */
public class FacebookConsts {

    public static final String URI_SCHEME = "https";
    public static final String PARAM_CODE = "code";
    public static final String PARAM_CLIENT_SECRET = "client_secret";
    public static final String PARAM_REDIRECT_URI = "redirect_uri";
    public static final String PARAM_CLIENT_ID = "client_id";
    public static final String TOKEN_STORED_IN_SESSION_ATTR_NAME = "uniqueFacebookLoginToken";
    public static final String PARAM_STATE = "state";
    public static final String PARAM_ACCESS_TOKEN = "access_token";
    public static final String LOGOUT_PATH = "/me/permissions";
    public static final String GRAPH_HOST = "graph.facebook.com";

}
