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

    public List<Profile> getAll(long userId, String authCode) throws StatusException, IOException, InterruptedException;

    public Profile getDefault(long userId, String authCode) throws EmptyResultException, StatusException, IOException,
            InterruptedException;

    public Profile create(long userId, String authCode, Profile profile) throws EntityIsNotValidException,
            StatusException, IOException, InterruptedException;

    public void update(long userId, String authCode, Profile profile) throws EntityIsNotValidException,
            StatusException, IOException, InterruptedException;

    public void delete(long userId, String authCode, int profileId) throws StatusException, IOException,
            InterruptedException;

}
