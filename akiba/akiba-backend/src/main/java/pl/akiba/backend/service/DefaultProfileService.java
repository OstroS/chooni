package pl.akiba.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import pl.akiba.backend.dao.ProfileDao;
import pl.akiba.model.entities.AkibaEntity.OperationType;
import pl.akiba.model.entities.Profile;
import pl.akiba.model.exception.EntityIsNotValidException;

/**
 * 
 * @author sobczakt
 */
@Service
public class DefaultProfileService implements ProfileService {

    @Autowired
    private ProfileDao profileDao;

    @Override
    public List<Profile> getAll(int userId) {
        return profileDao.getAll(userId);
    }

    @Override
    public Profile getDefault(int userId) throws EmptyResultDataAccessException {
        Profile profile = null;

        try {
            profile = profileDao.getDefault(userId);
        } catch (EmptyResultDataAccessException e) { //spring forced this exception
            throw e;
        }

        return profile;
    }

    @Override
    public Profile create(int userId, Profile profile) throws EntityIsNotValidException {
        if (!profile.isValid(OperationType.CREATE)) {
            throw new EntityIsNotValidException("Profile entity is not valid!");
        }

        return profileDao.create(userId, profile);
    }

    @Override
    public void update(int userId, Profile profile) throws EntityIsNotValidException {
        if (!profile.isValid(OperationType.UPDATE)) {
            throw new EntityIsNotValidException("Profile entity is not valid!");
        }

        profileDao.update(userId, profile);
    }

    @Override
    public void delete(int userId, int profileId) {
        profileDao.delete(userId, profileId);
    }

}
