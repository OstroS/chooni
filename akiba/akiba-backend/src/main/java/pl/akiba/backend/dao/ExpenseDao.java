package pl.akiba.backend.dao;

import java.util.List;

import pl.akiba.model.entities.Expense;

/**
 * 
 * @author sobczakt
 */
public interface ExpenseDao {

	public Expense get(int userId, int expenseId);

	public List<Expense> getAll(int userId);

	public void create(int userId, Expense expense);

	public void update(int userId, Expense expense);

	public void delete(int userId, int expenseId);

}
