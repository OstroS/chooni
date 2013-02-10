package pl.akiba.backend.service;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import pl.akiba.model.entities.Expense;
import pl.akiba.model.entities.Filter;
import pl.akiba.model.exception.EntityIsNotValidException;

/**
 * 
 * @author sobczakt
 */
public interface ExpenseService {

    /**
     * @throws EmptyResultDataAccessException
     */
    public Expense get(int userId, int expenseId) throws EmptyResultDataAccessException;

    public List<Expense> getAll(int userId, Filter filter);

    public double getTotal(int userId, Filter filter);

    /**
     * @throws EntityIsNotValidException
     * @return created expense
     */
    public Expense create(int userId, Expense expense) throws EntityIsNotValidException;

    public void update(int userId, Expense expense) throws EntityIsNotValidException;

    public void delete(int userId, int expenseId);

}
