package pl.akiba.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import pl.akiba.backend.dao.KindDao;
import pl.akiba.model.entities.AkibaEntity.OperationType;
import pl.akiba.model.entities.Kind;
import pl.akiba.model.exception.EmptyResultException;
import pl.akiba.model.exception.EntityIsNotValidException;
import pl.akiba.model.service.KindService;

/**
 * 
 * @author sobczakt
 */
@Service
public class DefaultKindService implements KindService {

    @Autowired
    private KindDao kindDao;

    @Override
    public Kind get(long userId, String authCode, int kindId) throws EmptyResultException {
        Kind kind = null;

        try {
            kind = kindDao.get(userId, authCode, kindId);
        } catch (EmptyResultDataAccessException e) { //spring forced this exception
            throw new EmptyResultException(e.getMessage());
        }

        return kind;
    }

    @Override
    public List<Kind> getAll(long userId, String authCode) {
        return kindDao.getAll(userId, authCode);
    }

    @Override
    public Kind create(long userId, String authCode, Kind kind) throws EntityIsNotValidException {
        if (!kind.isValid(OperationType.CREATE)) {
            throw new EntityIsNotValidException("Kind entity is not valid!");
        }

        return kindDao.create(userId, authCode, kind);
    }

    @Override
    public void update(long userId, String authCode, Kind kind) throws EntityIsNotValidException {
        if (!kind.isValid(OperationType.UPDATE)) {
            throw new EntityIsNotValidException("Kind entity is not valid!");
        }

        kindDao.update(userId, authCode, kind);
    }

    @Override
    public void delete(long userId, String authCode, int kindId) {
        kindDao.delete(userId, authCode, kindId);
    }

}
