package pl.akiba.frontend.facebook;

/**
 *
 * @author kostrows
 */
public class FacebookUserDTO {

    private Long facebookProfileId;
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    
    public void setFacebookProfileId(Long id) {
        this.facebookProfileId = id;
    }
    
    public Long getFacebookProfileId() {
        return facebookProfileId;
    }
    
}
