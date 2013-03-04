package pl.akiba.model.service;

import java.util.List;

import pl.akiba.model.entities.Profile;
import pl.akiba.model.exception.EmptyResultException;
import pl.akiba.model.exception.EntityIsNotValidException;

/**
 * 
 * @author sobczakt
 */
public interface ProfileService {

    public List<Profile> getAll(int userId);

    public Profile getDefault(int userId) throws EmptyResultException;;

    public Profile create(int userId, Profile profile) throws EntityIsNotValidException;

    public void update(int userId, Profile profile) throws EntityIsNotValidException;

    public void delete(int userId, int profileId);

}
