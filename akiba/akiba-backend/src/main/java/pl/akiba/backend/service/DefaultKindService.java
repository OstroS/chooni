package pl.akiba.backend.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import pl.akiba.backend.dao.KindDao;
import pl.akiba.model.entities.Kind;
import pl.akiba.model.exception.EntityIsNotValidException;

/**
 * 
 * @author sobczakt
 */
@Service
public class DefaultKindService implements KindService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultKindService.class);

    @Autowired
    private KindDao kindDao;

    @Override
    public Kind get(int userId, int kindId) throws EmptyResultDataAccessException {
        Kind kind = null;

        try {
            kind = kindDao.get(userId, kindId);
        } catch (EmptyResultDataAccessException e) { //spring forced this exception
            LOGGER.warn("Kind [userId:" + userId + ", kindId:" + kindId + "] doesn't exist!");
            return null;
        }

        return kind;
    }

    @Override
    public List<Kind> getAll(int userId) {
        return kindDao.getAll(userId);
    }

    @Override
    public Kind create(int userId, Kind kind) {
        if (StringUtils.isBlank(kind.getName())) {
            throw new EntityIsNotValidException("Kind entity is not valid!");
        }

        return kindDao.create(userId, kind);
    }

    @Override
    public void update(int userId, Kind kind) {
        //        if (StringUtils.isBlank(kind.getName())) {
        //            throw new EntityIsNotValidException("Kind entity is not valid!");
        //        }

        //TODO isValidate ???

        kindDao.update(userId, kind);
    }

    @Override
    public void delete(int userId, int kindId) {
        kindDao.delete(userId, kindId);
    }

}
