package pl.akiba.backend.service;

import pl.akiba.model.entities.FacebookUser;

public interface UserService {

    public FacebookUser getUser(long facebookId);
    
}
