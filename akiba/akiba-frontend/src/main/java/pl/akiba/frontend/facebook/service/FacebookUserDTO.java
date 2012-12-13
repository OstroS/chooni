package pl.akiba.frontend.facebook.service;

/**
 *
 * @author kostrows
 */
public class FacebookUserDTO {

    private String facebookProfileId;
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    
    public void setFacebookProfileId(String id) {
        this.facebookProfileId = id;
    }
    
    public String getFacebookProfileId() {
        return facebookProfileId;
    }
    
}
