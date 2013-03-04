package pl.akiba.model.entities;

/**
 * 
 * @author kostrows
 */
public class FacebookUser extends User {

    private static final long serialVersionUID = 9010189152910021921L;

    private Long facebookId;
    private String accessToken;
    private String facebookEmail;
    private String facebookFirstName;
    private String facebookLastName;
    private String facebookGener;
    private String facebookUsername;
    private Status status;

    public FacebookUser(long facebookId, long id, ROLE authority) {
        super(id, authority);
        this.facebookId = facebookId;
    }

    public FacebookUser() {
    }

    public Long getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(Long facebookId) {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {
        CREATED,
        EXISTING
    }

    @Override
    public String toString() {
        return "FacebookUser [facebookId=" + facebookId + ", accessToken=" + accessToken + ", facebookEmail="
                + facebookEmail + ", facebookFirstName=" + facebookFirstName + ", facebookLastName=" + facebookLastName
                + ", facebookGener=" + facebookGener + ", facebookUsername=" + facebookUsername + ", id=" + id
                + ", authorities=" + authorities + "]";
    }

}
