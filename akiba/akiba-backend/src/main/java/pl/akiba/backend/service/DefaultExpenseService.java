package pl.akiba.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import pl.akiba.backend.dao.ExpenseDao;
import pl.akiba.model.entities.AkibaEntity.OperationType;
import pl.akiba.model.entities.Expense;
import pl.akiba.model.entities.Filter;
import pl.akiba.model.exception.EntityIsNotValidException;

/**
 * 
 * @author sobczakt
 */
@Service
public class DefaultExpenseService implements ExpenseService {

    @Autowired
    private ExpenseDao expenseDao;

    @Override
    public Expense get(int userId, int expenseId) throws EmptyResultDataAccessException {
        Expense expense = null;

        try {
            expense = expenseDao.get(userId, expenseId);
        } catch (EmptyResultDataAccessException e) { //spring forced this exception
            throw e;
        }

        return expense;
    }

    @Override
    public List<Expense> getAll(int userId, Filter filter) {
        return expenseDao.getAll(userId, filter);
    }

    @Override
    public Expense create(int userId, Expense expense) throws EntityIsNotValidException {
        if (!expense.isValid(OperationType.CREATE)) {
            throw new EntityIsNotValidException("Expense entity is not valid!");
        }

        return expenseDao.create(userId, expense);
    }

    @Override
    public void update(int userId, final Expense expense) throws EntityIsNotValidException {
        if (!expense.isValid(OperationType.UPDATE)) {
            throw new EntityIsNotValidException("Expense entity is not valid!");
        }

        expenseDao.update(userId, expense);
    }

    @Override
    public void delete(int userId, int expenseId) {
        expenseDao.delete(userId, expenseId);
    }

}
