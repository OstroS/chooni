package pl.akiba.backend.service;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import pl.akiba.model.entities.Kind;

/**
 * 
 * @author sobczakt
 */
public interface KindService {

    public Kind get(int userId, int kindId) throws EmptyResultDataAccessException;

    public List<Kind> getAll(int userId);

    /**
     * @return created kind
     */
    public Kind create(int userId, Kind kind);

    public void update(int userId, Kind kind);

    public void delete(int userId, int kindId);

}
