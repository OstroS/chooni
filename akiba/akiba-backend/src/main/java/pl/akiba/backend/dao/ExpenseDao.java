package pl.akiba.backend.dao;

import java.util.List;

import pl.akiba.model.entities.Expense;
import pl.akiba.model.entities.Filter;

/**
 * 
 * @author sobczakt
 */
public interface ExpenseDao {

    public Expense get(long userId, String authCode, int expenseId);

    public List<Expense> getAll(long userId, String authCode, Filter filter);

    /**
     * 
     */
    public double getTotal(long userId, String authCode, Filter filter);

    /**
     * @return created expense
     */
    public Expense create(long userId, String authCode, Expense expense);

    public void update(long userId, String authCode, Expense expense);

    public void delete(long userId, String authCode, int expenseId);

}
