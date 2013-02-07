package pl.akiba.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import pl.akiba.backend.dao.KindDao;
import pl.akiba.model.entities.AkibaEntity.OperationType;
import pl.akiba.model.entities.Kind;
import pl.akiba.model.exception.EntityIsNotValidException;

/**
 * 
 * @author sobczakt
 */
@Service
public class DefaultKindService implements KindService {

    @Autowired
    private KindDao kindDao;

    @Override
    public Kind get(int userId, int kindId) throws EmptyResultDataAccessException {
        Kind kind = null;

        try {
            kind = kindDao.get(userId, kindId);
        } catch (EmptyResultDataAccessException e) { //spring forced this exception
            throw e;
        }

        return kind;
    }

    @Override
    public List<Kind> getAll(int userId) {
        return kindDao.getAll(userId);
    }

    @Override
    public Kind create(int userId, Kind kind) throws EntityIsNotValidException {
        if (!kind.isValid(OperationType.CREATE)) {
            throw new EntityIsNotValidException("Kind entity is not valid!");
        }

        return kindDao.create(userId, kind);
    }

    @Override
    public void update(int userId, Kind kind) throws EntityIsNotValidException {
        if (!kind.isValid(OperationType.UPDATE)) {
            throw new EntityIsNotValidException("Kind entity is not valid!");
        }

        kindDao.update(userId, kind);
    }

    @Override
    public void delete(int userId, int kindId) {
        kindDao.delete(userId, kindId);
    }

}
