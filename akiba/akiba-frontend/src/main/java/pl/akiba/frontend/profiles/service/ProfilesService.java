package pl.akiba.frontend.profiles.service;

import java.util.List;

import pl.akiba.model.entities.Profile;
import pl.akiba.model.entities.User;

public interface ProfilesService {

    /**
     * Get all profiles connected with given user
     * @param user User
     * @return List of profiles
     */
    public abstract List<Profile> getAll(User user);

    /**
     * Get profile by given id
     * @param profileId Profiles id
     * @return Profile
     */
    public abstract Profile get(User user, long profileId);

    /**
     * Add profile for given user
     * @param user
     * @param profile
     */
    public abstract void add(User user, Profile profile);
    
}