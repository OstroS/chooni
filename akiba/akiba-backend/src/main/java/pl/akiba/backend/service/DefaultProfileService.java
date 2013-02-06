package pl.akiba.backend.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import pl.akiba.backend.dao.ProfileDao;
import pl.akiba.model.entities.Profile;
import pl.akiba.model.exception.EntityIsNotValidException;

@Service
public class DefaultProfileService implements ProfileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultProfileService.class);

    @Autowired
    private ProfileDao profileDao;

    @Override
    public List<Profile> getAll(int userId) {
        return profileDao.getAll(userId);
    }

    @Override
    public Profile getDefault(int userId) {
        Profile profile = null;

        try {
            profile = profileDao.getDefault(userId);
        } catch (EmptyResultDataAccessException e) { //spring forced this exception
            LOGGER.warn("Default profile [userId:" + userId + "] doesn't exist!");
            return null;
        }

        return profile;
    }

    @Override
    public Profile create(int userId, Profile profile) {
        if (StringUtils.isBlank(profile.getName())) {
            throw new EntityIsNotValidException("Profile entity is not valid!");
        }

        return profileDao.create(userId, profile);
    }

    @Override
    public void update(int userId, Profile profile) {
        if (StringUtils.isBlank(profile.getName())) {
            throw new EntityIsNotValidException("Profile entity is not valid!");
        }

        profileDao.update(userId, profile);
    }

    @Override
    public void delete(int userId, int profileId) {
        profileDao.delete(userId, profileId);
    }

}
