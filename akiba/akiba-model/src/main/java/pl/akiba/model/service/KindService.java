package pl.akiba.model.service;

import java.io.IOException;
import java.util.List;

import pl.akiba.model.entities.Kind;
import pl.akiba.model.exception.EmptyResultException;
import pl.akiba.model.exception.EntityIsNotValidException;
import pl.akiba.model.exception.StatusException;

/**
 * 
 * @author sobczakt
 */
public interface KindService {

    public Kind get(long userId, int kindId) throws EmptyResultException, StatusException, IOException,
            InterruptedException;

    public List<Kind> getAll(long userId) throws StatusException, IOException, InterruptedException;;

    /**
     * @return created kind
     */
    public Kind create(long userId, Kind kind) throws EntityIsNotValidException, StatusException, IOException,
            InterruptedException;

    public void update(long userId, Kind kind) throws EntityIsNotValidException, StatusException, IOException,
            InterruptedException;

    public void delete(long userId, int kindId) throws StatusException, IOException, InterruptedException;;

}
