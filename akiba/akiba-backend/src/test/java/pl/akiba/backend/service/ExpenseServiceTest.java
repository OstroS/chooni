package pl.akiba.backend.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.akiba.model.entities.Expense;
import pl.akiba.model.entities.Filter;
import pl.akiba.model.entities.Kind;
import pl.akiba.model.entities.Profile;
import pl.akiba.model.exception.EmptyResultException;
import pl.akiba.model.exception.EntityIsNotValidException;
import pl.akiba.model.exception.StatusException;
import pl.akiba.model.service.ExpenseService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/pl/akiba/backend/appContextTest.xml" })
public class ExpenseServiceTest {

    @Autowired
    private ExpenseService expenseService;

    private final long userId = 0;
    private final int kindId = 1;
    private final int profileId = 1;
    private final String authCode = "AUTH_CODE";

    @Test
    public void testCRUD() throws EntityIsNotValidException, StatusException, IOException, InterruptedException {
        final Expense createdExpense = expenseService.create(userId, authCode, prepareExpense());

        assertNotNull(createdExpense);
        assertTrue(createdExpense.getId() > 0);

        final double newAmount = 299.00;
        createdExpense.setAmount(newAmount);
        expenseService.update(userId, authCode, createdExpense);

        final Expense updatedExpense = expenseService.get(userId, authCode, createdExpense.getId());
        assertEquals(null, newAmount, updatedExpense.getAmount(), 1e-8);

        expenseService.delete(userId, authCode, updatedExpense.getId());
        Expense expense = null;
        try {
            expense = expenseService.get(userId,authCode,  updatedExpense.getId());
        } catch (EmptyResultException e) {
        }

        assertTrue(expense == null);
    }

    @Test
    public void testTotalCount() throws StatusException, IOException, InterruptedException {
        double total = expenseService.getTotal(userId, authCode, prepareFilter());
        assertTrue(total > 0.0);
    }

    private Filter prepareFilter() {
        final Filter filter = new Filter();
        filter.setLimit(100);
        filter.setKindId(kindId);
        filter.setProfileId(profileId);

        Calendar calendar = new GregorianCalendar();
        Date endDate = calendar.getTime();
        calendar.add(Calendar.YEAR, -1);
        Date startDate = calendar.getTime();

        filter.setStartDate(startDate);
        filter.setEndDate(endDate);

        return filter;
    }

    private Expense prepareExpense() {
        final Expense expense = new Expense();
        expense.setAmount(199.99);
        expense.setDate(new Date());
        expense.setKind(new Kind(kindId, "typ test"));
        expense.setProfile(new Profile(profileId, "profil test", true, true));

        return expense;
    }

}
