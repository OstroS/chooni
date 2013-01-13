package pl.akiba.backend.service;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import pl.akiba.model.entities.Expense;
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

    public List<Expense> getAll(int userId);

    /**
     * @throws EntityIsNotValidException
     * @return created expense
     */
    public Expense create(int userId, Expense expense) throws EntityIsNotValidException;

    public void update(int userId, Expense expense) throws EntityIsNotValidException;

    public void delete(int userId, int expenseId);

}
