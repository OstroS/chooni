package pl.akiba.wsclient.client;

import java.io.IOException;
import java.util.List;

import pl.akiba.model.entities.Expense;
import pl.akiba.model.entities.Filter;
import pl.akiba.model.exception.StatusException;

public interface ExpenseClient {

    /**
     * @param userId
     * @param expenseId
     * @return {@link Expense}
     * @throws StatusException
     * @throws IOException
     * @throws InterruptedException
     */
    public Expense get(int userId, int expenseId) throws StatusException, IOException, InterruptedException;

    /**
     * @param userId
     * @param filter
     * @return {@link Expense}s collection
     * @throws StatusException
     * @throws IOException
     * @throws InterruptedException
     */
    public List<Expense> getAll(int userId, Filter filter) throws StatusException, IOException, InterruptedException;

    /**
     * @throws StatusException
     * @throws IOException
     * @throws InterruptedException
     */
    public double getTotal(int userId, Filter filter) throws StatusException, IOException, InterruptedException;

    /**
     * @throws StatusException
     * @throws IOException
     * @throws InterruptedException
     */
    public Expense create(int userId, Expense expense) throws StatusException, IOException, InterruptedException;

    /**
     * @throws StatusException
     * @throws IOException
     * @throws InterruptedException
     */
    public Expense update(int userId, Expense expense) throws StatusException, IOException, InterruptedException;

    /**
     * @throws StatusException
     * @throws IOException
     * @throws InterruptedException
     */
    public void delete(int userId, int expenseId) throws StatusException, IOException, InterruptedException;

}
