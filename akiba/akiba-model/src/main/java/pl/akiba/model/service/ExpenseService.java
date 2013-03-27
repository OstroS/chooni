package pl.akiba.model.service;

import java.io.IOException;
import java.util.List;

import pl.akiba.model.entities.Expense;
import pl.akiba.model.entities.Filter;
import pl.akiba.model.exception.EmptyResultException;
import pl.akiba.model.exception.EntityIsNotValidException;
import pl.akiba.model.exception.StatusException;

/**
 * 
 * @author sobczakt
 */
public interface ExpenseService {

    /**
     * @throws EmptyResultDataAccessException
     */
    public Expense get(long userId, String authCode, int expenseId) throws EmptyResultException, StatusException,
            IOException, InterruptedException;

    public List<Expense> getAll(long userId, String authCode, Filter filter) throws StatusException, IOException,
            InterruptedException;

    public double getTotal(long userId, String authCode, Filter filter) throws StatusException, IOException,
            InterruptedException;

    /**
     * @throws EntityIsNotValidException
     * @return created expense
     */
    public Expense create(long userId, String authCode, Expense expense) throws EntityIsNotValidException,
            StatusException, IOException, InterruptedException;

    public Expense update(long userId, String authCode, Expense expense) throws EntityIsNotValidException,
            StatusException, IOException, InterruptedException;

    public void delete(long userId, String authCode, int expenseId) throws StatusException, IOException,
            InterruptedException;

}
