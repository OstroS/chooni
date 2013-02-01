package pl.akiba.wsclient.api;

import pl.akiba.frontend.model.entities.FacebookUser;

public interface UserApi {
    public FacebookUser getByFacebookId(Long facebookId);
}
