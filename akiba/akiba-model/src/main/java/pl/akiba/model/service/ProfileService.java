package pl.akiba.model.service;

import java.io.IOException;
import java.util.List;

import pl.akiba.model.entities.Profile;
import pl.akiba.model.exception.EmptyResultException;
import pl.akiba.model.exception.EntityIsNotValidException;
import pl.akiba.model.exception.StatusException;

/**
 * 
 * @author sobczakt
 */
public interface ProfileService {

    public List<Profile> getAll(int userId) throws StatusException, IOException, InterruptedException;

    public Profile getDefault(int userId) throws EmptyResultException, StatusException, IOException,
            InterruptedException;

    public Profile create(int userId, Profile profile) throws EntityIsNotValidException, StatusException, IOException,
            InterruptedException;

    public void update(int userId, Profile profile) throws EntityIsNotValidException, StatusException, IOException,
            InterruptedException;

    public void delete(int userId, int profileId) throws StatusException, IOException, InterruptedException;

}
