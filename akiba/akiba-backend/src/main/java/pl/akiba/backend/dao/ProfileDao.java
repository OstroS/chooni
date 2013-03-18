package pl.akiba.backend.dao;

import java.util.List;

import pl.akiba.model.entities.Profile;

public interface ProfileDao {

    public List<Profile> getAll(long userId);

    public Profile getDefault(long userId);

    public Profile create(long userId, Profile profile);

    public void update(long userId, Profile profile);

    public void delete(long userId, int profileId);

}
