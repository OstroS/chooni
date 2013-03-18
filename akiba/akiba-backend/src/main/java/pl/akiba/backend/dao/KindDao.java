package pl.akiba.backend.dao;

import java.util.List;

import pl.akiba.model.entities.Kind;

/**
 * 
 * @author sobczakt
 */
public interface KindDao {

    public Kind get(long userId, int kindId);

    public List<Kind> getAll(long userId);

    /**
     * @return created kind
     */
    public Kind create(long userId, Kind kind);

    public void update(long userId, Kind kind);

    public void delete(long userId, int kindId);

}
