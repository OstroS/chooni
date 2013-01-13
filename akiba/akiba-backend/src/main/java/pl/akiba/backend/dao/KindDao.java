package pl.akiba.backend.dao;

import java.util.List;

import pl.akiba.model.entities.Kind;

/**
 * 
 * @author sobczakt
 */
public interface KindDao {

    public Kind get(int userId, int kindId);

    public List<Kind> getAll(int userId);

    /**
     * @return created kind
     */
    public Kind create(int userId, Kind kind);

    public void update(int userId, Kind kind);

    public void delete(int userId, int kindId);

}
