package pl.akiba.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import pl.akiba.backend.dao.ProfileDao;
import pl.akiba.model.entities.AkibaEntity.OperationType;
import pl.akiba.model.entities.Profile;
import pl.akiba.model.exception.EmptyResultException;
import pl.akiba.model.exception.EntityIsNotValidException;
import pl.akiba.model.service.ProfileService;

/**
 * 
 * @author sobczakt
 */
@Service
public class DefaultProfileService implements ProfileService {

    @Autowired
    private ProfileDao profileDao;

    @Override
    public List<Profile> getAll(long userId, String authCode) {
        return profileDao.getAll(userId, authCode);
    }

    @Override
    public Profile getDefault(long userId, String authCode) throws EmptyResultException {
        Profile profile = null;

        try {
            profile = profileDao.getDefault(userId, authCode);
        } catch (EmptyResultDataAccessException e) { //spring forced this exception
            throw new EmptyResultException(e.getMessage());
        }

        return profile;
    }

    @Override
    public Profile create(long userId, String authCode, Profile profile) throws EntityIsNotValidException {
        if (!profile.isValid(OperationType.CREATE)) {
            throw new EntityIsNotValidException("Profile entity is not valid!");
        }

        return profileDao.create(userId, authCode, profile);
    }

    @Override
    public void update(long userId, String authCode, Profile profile) throws EntityIsNotValidException {
        if (!profile.isValid(OperationType.UPDATE)) {
            throw new EntityIsNotValidException("Profile entity is not valid!");
        }

        profileDao.update(userId, authCode, profile);
    }

    @Override
    public void delete(long userId, String authCode, int profileId) {
        profileDao.delete(userId, authCode, profileId);
    }
}
