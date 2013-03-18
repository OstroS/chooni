package pl.akiba.backend.dao;

import java.util.List;

import pl.akiba.model.entities.Expense;
import pl.akiba.model.entities.Filter;

/**
 * 
 * @author sobczakt
 */
public interface ExpenseDao {

    public Expense get(long userId, int expenseId);

    public List<Expense> getAll(long userId, Filter filter);

    /**
     * 
     */
    public double getTotal(long userId, Filter filter);

    /**
     * @return created expense
     */
    public Expense create(long userId, Expense expense);

    public void update(long userId, Expense expense);

    public void delete(long userId, int expenseId);

}
