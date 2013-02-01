package pl.akiba.frontend.model.entities;

import pl.akiba.model.entities.User;


/**
 *
 * @author kostrows
 */
public class FacebookUser extends User {
    String facebookId;
    String accessToken;

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

}
