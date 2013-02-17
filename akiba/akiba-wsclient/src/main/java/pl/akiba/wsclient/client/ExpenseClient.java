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
     * @throws Exception
     */
    public Expense get(int userId, int expenseId) throws StatusException, IOException, InterruptedException, Exception;

    /**
     * @param userId
     * @param filter
     * @return {@link Expense}s collection
     * @throws StatusException
     * @throws IOException
     * @throws InterruptedException
     * @throws Exception
     */
    public List<Expense> getAll(int userId, Filter filter) throws StatusException, IOException, InterruptedException,
            Exception;

    public double getTotal(int userId, Filter filter);

    public Expense create(int userId, Expense expense);

    public void update(int userId, Expense expense);

    public void delete(int userId, int expenseId);

}
