package pl.akiba.model.service;

import java.io.IOException;
import java.util.List;

import pl.akiba.model.entities.Kind;
import pl.akiba.model.entities.User;
import pl.akiba.model.exception.EmptyResultException;
import pl.akiba.model.exception.EntityIsNotValidException;
import pl.akiba.model.exception.StatusException;

/**
 * 
 * @author sobczakt
 */
public interface KindService {

    public Kind get(User user, int kindId) throws EmptyResultException, StatusException, IOException,
            InterruptedException;

    public List<Kind> getAll(User user) throws StatusException, IOException, InterruptedException;;

    /**
     * @return created kind
     */
    public Kind create(User user, Kind kind) throws EntityIsNotValidException, StatusException, IOException,
            InterruptedException;

    public void update(User user, Kind kind) throws EntityIsNotValidException, StatusException, IOException,
            InterruptedException;

    public void delete(User user, int kindId) throws StatusException, IOException, InterruptedException;;

}
