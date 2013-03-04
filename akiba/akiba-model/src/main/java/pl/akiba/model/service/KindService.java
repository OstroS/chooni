package pl.akiba.model.service;

import java.util.List;

import pl.akiba.model.entities.Kind;
import pl.akiba.model.exception.EmptyResultException;
import pl.akiba.model.exception.EntityIsNotValidException;

/**
 * 
 * @author sobczakt
 */
public interface KindService {

    public Kind get(int userId, int kindId) throws EmptyResultException;

    public List<Kind> getAll(int userId);

    /**
     * @return created kind
     */
    public Kind create(int userId, Kind kind) throws EntityIsNotValidException;

    public void update(int userId, Kind kind) throws EntityIsNotValidException;

    public void delete(int userId, int kindId);

}
