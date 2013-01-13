package pl.akiba.backend.dao;

import java.util.List;

import pl.akiba.model.entities.Expense;
import pl.akiba.model.entities.Filter;

/**
 * 
 * @author sobczakt
 */
public interface ExpenseDao {

    public Expense get(int userId, int expenseId);

    public List<Expense> getAll(int userId, Filter filter);

    /**
     * @return created expense
     */
    public Expense create(int userId, Expense expense);

    public void update(int userId, Expense expense);

    public void delete(int userId, int expenseId);

}
