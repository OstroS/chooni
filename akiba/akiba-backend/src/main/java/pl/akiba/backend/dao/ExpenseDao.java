package pl.akiba.backend.dao;

import java.util.List;

import pl.akiba.model.entities.Expense;

/**
 * 
 * @author sobczakt
 */
public interface ExpenseDao {

	public Expense get(String userId, int expenseId);

	public List<Expense> getAll(String userId);

	public void create(String userId, Expense expense);

	public void update(String userId, Expense expense);

	public void delete(String userId, int expenseId);

}
