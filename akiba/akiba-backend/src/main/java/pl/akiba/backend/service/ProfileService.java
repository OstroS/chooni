package pl.akiba.backend.service;

import java.util.List;

import pl.akiba.model.entities.Profile;

public interface ProfileService {

    public List<Profile> getAll(int userId);

    public Profile getDefault(int userId);

    public Profile create(int userId, Profile profile);

    public void update(int userId, Profile profile);

    public void delete(int userId, int profileId);

}
