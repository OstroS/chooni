package pl.akiba.backend.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import pl.akiba.backend.dao.ExpenseDao;
import pl.akiba.model.entities.Expense;
import pl.akiba.model.entities.Filter;
import pl.akiba.model.exception.EntityIsNotValidException;

/**
 * 
 * @author sobczakt
 */
@Service
public class DefaultExpenseService implements ExpenseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultExpenseService.class);

    @Autowired
    private ExpenseDao expenseDao;

    @Override
    public Expense get(int userId, int expenseId) throws EmptyResultDataAccessException {
        Expense expense = null;

        try {
            expense = expenseDao.get(userId, expenseId);
        } catch (EmptyResultDataAccessException e) { //spring forced this exception
            LOGGER.warn("Expense [userId:" + userId + ", expenseId:" + expenseId + "] doesn't exist!");
            return null;
        }

        return expense;
    }

    @Override
    public List<Expense> getAll(int userId, Filter filter) {
        //filter validating?
        return expenseDao.getAll(userId, filter);
    }

    @Override
    public Expense create(int userId, Expense expense) throws EntityIsNotValidException {
        //        if (expense.getProfile() == null || expense.getProfile().getId() < 1) {
        //            throw new EntityIsNotValidException("Profile entity is not valid!");
        //        }
        //        if (expense.getKind() == null || expense.getKind().getId() < 1) {
        //            throw new EntityIsNotValidException("Kind entity is not valid!");
        //        }

        //FIXME expense.isvalid?

        return expenseDao.create(userId, expense);
    }

    @Override
    public void update(int userId, final Expense expense) throws EntityIsNotValidException {
        //        if (expense.getProfile() == null || expense.getProfile().getId() < 1) {
        //            throw new EntityIsNotValidException("Profile entity is not valid!");
        //        }
        //        if (expense.getKind() == null || expense.getKind().getId() < 1) {
        //            throw new EntityIsNotValidException("Kind entity is not valid!");
        //        }

        //FIXME expense.isvalid?

        expenseDao.update(userId, expense);
    }

    @Override
    public void delete(int userId, int expenseId) {
        expenseDao.delete(userId, expenseId);
    }

}
