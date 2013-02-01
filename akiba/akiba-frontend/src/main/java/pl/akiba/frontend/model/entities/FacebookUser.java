package pl.akiba.frontend.model.entities;

import pl.akiba.model.entities.User;

/**
 * 
 * @author kostrows
 */
public class FacebookUser extends User {

    private String facebookId;
    private String accessToken;
    private String facebookEmail;
    private String facebookFirstName;
    private String facebookLastName;
    private String facebookGener;
    private String facebookUsername;

    
    
    @Override
    public String toString() {
        return "FacebookUser [facebookId=" + facebookId + ", accessToken=" + accessToken + ", facebookEmail="
                + facebookEmail + ", facebookFirstName=" + facebookFirstName + ", facebookLastName=" + facebookLastName
                + ", facebookGener=" + facebookGener + ", facebookUsername=" + facebookUsername + ", id=" + id
                + ", name=" + name + "]";
    }

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

    public String getFacebookEmail() {
        return facebookEmail;
    }

    public void setFacebookEmail(String facebookEmail) {
        this.facebookEmail = facebookEmail;
    }

    public String getFacebookFirstName() {
        return facebookFirstName;
    }

    public void setFacebookFirstName(String facebookFirstName) {
        this.facebookFirstName = facebookFirstName;
    }

    public String getFacebookLastName() {
        return facebookLastName;
    }

    public void setFacebookLastName(String facebookLastName) {
        this.facebookLastName = facebookLastName;
    }

    public String getFacebookGener() {
        return facebookGener;
    }

    public void setFacebookGener(String facebookGener) {
        this.facebookGener = facebookGener;
    }

    public String getFacebookUsername() {
        return facebookUsername;
    }

    public void setFacebookUsername(String facebookUsername) {
        this.facebookUsername = facebookUsername;
    }

}
