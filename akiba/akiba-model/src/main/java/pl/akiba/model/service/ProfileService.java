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

    public List<Profile> getAll(String authCode) throws StatusException, IOException, InterruptedException;

    public Profile getDefault(String authCode) throws EmptyResultException, StatusException, IOException,
            InterruptedException;

    public Profile create(String authCode, Profile profile) throws EntityIsNotValidException, StatusException, IOException,
            InterruptedException;

    public void update(String authCode, Profile profile) throws EntityIsNotValidException, StatusException, IOException,
            InterruptedException;

    public void delete(String authCode, int profileId) throws StatusException, IOException, InterruptedException;

}
