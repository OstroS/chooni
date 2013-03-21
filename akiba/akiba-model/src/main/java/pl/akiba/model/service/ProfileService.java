package pl.akiba.model.service;

import java.io.IOException;
import java.util.List;

import pl.akiba.model.entities.Profile;
import pl.akiba.model.entities.User;
import pl.akiba.model.exception.EmptyResultException;
import pl.akiba.model.exception.EntityIsNotValidException;
import pl.akiba.model.exception.StatusException;

/**
 * 
 * @author sobczakt
 */
public interface ProfileService {

    public List<Profile> getAll(User user) throws StatusException, IOException, InterruptedException;

    public Profile getDefault(User user) throws EmptyResultException, StatusException, IOException,
            InterruptedException;

    public Profile create(User user, Profile profile) throws EntityIsNotValidException, StatusException, IOException,
            InterruptedException;

    public void update(User user, Profile profile) throws EntityIsNotValidException, StatusException, IOException,
            InterruptedException;

    public void delete(User user, int profileId) throws StatusException, IOException, InterruptedException;

}
