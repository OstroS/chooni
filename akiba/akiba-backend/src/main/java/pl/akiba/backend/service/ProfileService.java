package pl.akiba.backend.service;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import pl.akiba.model.entities.Profile;
import pl.akiba.model.exception.EntityIsNotValidException;

public interface ProfileService {

    public List<Profile> getAll(int userId);

    public Profile getDefault(int userId) throws EmptyResultDataAccessException;;

    public Profile create(int userId, Profile profile) throws EntityIsNotValidException;

    public void update(int userId, Profile profile) throws EntityIsNotValidException;

    public void delete(int userId, int profileId);

}
