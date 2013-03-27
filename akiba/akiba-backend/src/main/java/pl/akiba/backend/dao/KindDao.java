package pl.akiba.backend.dao;

import java.util.List;

import pl.akiba.model.entities.Kind;

/**
 * 
 * @author sobczakt
 */
public interface KindDao {

    public Kind get(long userId, String authCode, int kindId);

    public List<Kind> getAll(long userId, String authCode);

    /**
     * @return created kind
     */
    public Kind create(long userId, String authCode, Kind kind);

    public void update(long userId, String authCode, Kind kind);

    public void delete(long userId, String authCode, int kindId);

}
