package pl.akiba.backend.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import pl.akiba.backend.dao.ExpenseDao;
import pl.akiba.model.entities.Expense;

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
    public Expense get(int userId, int expenseId) {
        Expense expense = null;

        try {
            expense = expenseDao.get(userId, expenseId);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.warn("Expense [userId:" + userId + ", expenseId:" + expenseId + "] doesn't exist!");
            return null;
        }

        return expense;
    }

    @Override
    public List<Expense> getAll(int userId) {
        return expenseDao.getAll(userId);
    }

    @Override
    public void create(int userId, Expense expense) {
        expenseDao.create(userId, expense);
    }

    @Override
    public void update(int userId, Expense expense) {
        expenseDao.update(userId, expense);
    }

    @Override
    public void delete(int userId, int expenseId) {
        expenseDao.delete(userId, expenseId);
    }

}
