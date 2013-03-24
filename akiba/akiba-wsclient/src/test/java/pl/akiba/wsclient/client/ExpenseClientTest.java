package pl.akiba.wsclient.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import pl.akiba.model.entities.Expense;
import pl.akiba.model.entities.Kind;
import pl.akiba.model.entities.Profile;
import pl.akiba.model.exception.StatusException;
import pl.akiba.model.service.ExpenseService;

/**
 * 
 * @author sobczakt
 */
public class ExpenseClientTest extends CommonClientTest {

    private final ExpenseService expenseClient;

    public ExpenseClientTest() throws Exception {
        this.expenseClient = new DefaultExpenseClient("http://localhost:8080/akiba-backend",
                httpClientFactory.getHttpClient());
    }

    @Test
    public void testGetAll() {
        List<Expense> expenses = null;
        try {
            expenses = expenseClient.getAll(userId, authenticationCode, null);
        } catch (StatusException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(expenses.size() > 0);
    }

    @Test
    public void testGetTotal() {
        double total = 0;
        try {
            total = expenseClient.getTotal(userId, authenticationCode, null);
        } catch (StatusException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertTrue(total > 0.0);
    }

    @Test
    public void testCUD() {
        Expense expense = new Expense();
        expense.setAmount(123.00);
        expense.setDate(new Date());
        expense.setKind(new Kind(1, "typ test"));
        expense.setProfile(new Profile(1, "profil test"));

        Expense newExpense = null;
        try {
            newExpense = expenseClient.create(userId, authenticationCode, expense);
            assertNotNull(newExpense);
            assertTrue(newExpense.getId() > 0);

            int expenseId = newExpense.getId();

            double newAmount = 5555.0;
            newExpense.setAmount(newAmount);
            expenseClient.update(userId, authenticationCode, newExpense);

            Expense updatedExpense = expenseClient.get(userId, authenticationCode, expenseId);
            assertEquals(newAmount, updatedExpense.getAmount(), 1e-8);

            expenseClient.delete(userId, authenticationCode, expenseId);
            Expense deletedExpense = expenseClient.get(userId,authenticationCode,  expenseId);
            assertNull(deletedExpense);
        } catch (StatusException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
