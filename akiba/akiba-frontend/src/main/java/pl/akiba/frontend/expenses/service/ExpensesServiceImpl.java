package pl.akiba.frontend.expenses.service;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import pl.akiba.model.entities.Expense;
import pl.akiba.model.entities.User;
import pl.akiba.model.exception.StatusException;
import pl.akiba.wsclient.api.AkibaApi;
import pl.akiba.wsclient.api.Criteria;
import pl.akiba.wsclient.api.CriteriaBuilder;
import pl.akiba.wsclient.api.impl.WsClientFactory;
import pl.akiba.wsclient.client.DefaultExpenseClient;

/**
 * 
 * @author OstroS
 */
@Component("expensesService")
public class ExpensesServiceImpl implements ExpensesService {

    @Autowired
    private WsClientFactory wsClientFactory;
    
    private DefaultExpenseClient defaultExpensesClient;
    
    private static final Logger logger = Logger.getLogger(ExpensesServiceImpl.class.toString());

    /**
     * Amount of last expenses of user which are apparent on main site
     */
    private final Long amountOfLastExpenses = 10L;

    /* (non-Javadoc)
     * @see pl.akiba.frontend.expenses.service.ExpensesService#addExpense(pl.akiba.model.entities.Expense, pl.akiba.model.entities.User)
     */
    @Override
    public void addExpense(Expense expense, User user) {
        logger.info("Expense add, " + expense + " , " + user);
        
        // FIXME THATS F*** HORRIBLE :D:D:D
        
        int userId = (int)(long)user.getId();
        try {
            wsClientFactory.createDefaultExpenseClient().create(userId, expense);
        } catch (StatusException | IOException | InterruptedException e) {
            logger.severe(e.toString());
        }
    }

    /* (non-Javadoc)
     * @see pl.akiba.frontend.expenses.service.ExpensesService#getAllExpenses(pl.akiba.model.entities.User)
     */
    @Override
    public List<Expense> getAllExpenses(User user) {
        logger.info("Get all expenses, " + user);
        return akibaApi.getExpenseApi().getAll(user);
    }

    /* (non-Javadoc)
     * @see pl.akiba.frontend.expenses.service.ExpensesService#getLastExpenses(pl.akiba.model.entities.User)
     */
    @Override
    public List<Expense> getLastExpenses(User user) {
        logger.info("Get last expenses");
        CriteriaBuilder cb = new CriteriaBuilder();
        Criteria criteria = cb.create().withAmountOfResults(amountOfLastExpenses)
                .withSortOrder(CriteriaBuilder.SORT_ASCENDING_ORDER).build();

        return this.akibaApi.getExpenseApi().get(user, criteria);

    }
}
