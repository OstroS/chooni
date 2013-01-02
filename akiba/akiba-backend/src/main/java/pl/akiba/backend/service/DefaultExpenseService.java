package pl.akiba.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.akiba.backend.dao.ExpenseDao;
import pl.akiba.model.entities.Expense;

/**
 * 
 * @author sobczakt
 */
@Service
public class DefaultExpenseService implements ExpenseService {

    @Autowired
    private ExpenseDao expenseDao;

    @Override
    public Expense get(String userId, int expenseId) {
        return expenseDao.get(userId, expenseId);
    }

    @Override
    public List<Expense> getAll(String userId) {
        //walidacja userId

        return expenseDao.getAll(userId);
    }

    @Override
    public void create(String userId, Expense expense) {
        expenseDao.create(userId, expense);
    }

    @Override
    public void update(String userId, Expense expense) {
        expenseDao.update(userId, expense);
    }

    @Override
    public void delete(String userId, int expenseId) {
        expenseDao.delete(userId, expenseId);
    }

}
