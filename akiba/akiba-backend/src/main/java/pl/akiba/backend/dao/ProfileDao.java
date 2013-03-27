package pl.akiba.backend.dao;

import java.util.List;

import pl.akiba.model.entities.Profile;

public interface ProfileDao {

    public List<Profile> getAll(long userId, String authCode);

    public Profile getDefault(long userId, String authCode);

    public Profile create(long userId, String authCode, Profile profile);

    public void update(long userId, String authCode, Profile profile);

    public void delete(long userId, String authCode, int profileId);

}
