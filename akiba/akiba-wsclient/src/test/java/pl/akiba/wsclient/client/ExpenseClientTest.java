package pl.akiba.wsclient.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import pl.akiba.model.entities.Expense;
import pl.akiba.model.exception.StatusException;

public class ExpenseClientTest {

    private final ExpenseClient expenseClient;

    public ExpenseClientTest() {
        this.expenseClient = new DefaultExpenseClient("http://localhost:8080/akiba-backend/");
    }

    @Test
    public void testGet() {
        int expenseId = 10;

        Expense expense = null;

        try {
            expense = expenseClient.get(1, expenseId);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(expense.getId(), expenseId);
    }

    @Test
    public void testGetAll() {
        int userId = 1;
        List<Expense> expenses = null;
        try {
            expenses = expenseClient.getAll(userId, null);
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

}
