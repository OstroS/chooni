package pl.akiba.backend.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.akiba.model.entities.Expense;
import pl.akiba.model.entities.Kind;
import pl.akiba.model.entities.Profile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:appContextTest.xml" })
public class ExpenseServiceTest {

    @Autowired
    private ExpenseService expenseService;

    //        expenseService.create(userId, expense);
    //        expenseService.get(userId, expenseId);
    //        expenseService.getAll(userId, filter);
    //        expenseService.getTotal(userId, filter);
    //        expenseService.update(userId, expense);
    //        expenseService.delete(userId, expenseId);

    @Test
    public void testCreate() {
        Expense expense = new Expense();
        expense.setAmount(199.99);
        expense.setDate(new Date());
        expense.setKind(new Kind(0, "testKind"));
        expense.setProfile(new Profile(0, "testProfile", true, true));

        Expense createdExpense = expenseService.create(0, expense);

        assertNotNull(createdExpense);
        assertTrue(createdExpense.getId() > 0);
    }

}
