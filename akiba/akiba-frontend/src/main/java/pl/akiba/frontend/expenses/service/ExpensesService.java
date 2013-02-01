package pl.akiba.frontend.expenses.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import pl.akiba.model.entities.Expense;
import pl.akiba.model.entities.User;
import pl.akiba.wsclient.api.AkibaApi;
import pl.akiba.wsclient.api.Criteria;
import pl.akiba.wsclient.api.CriteriaBuilder;

/**
 * 
 * @author OstroS
 */
@Component("expensesService")
public class ExpensesService {

    @Autowired
    @Qualifier(value = "akibaApiMock")
    AkibaApi akibaApi;
    
    private static final Logger logger = Logger.getLogger(ExpensesService.class.toString());

    /**
     * Amount of last expenses of user which are apparent on main site
     */
    private final Long amountOfLastExpenses = 10L;

    /**
     * @TODO
     * @param expense
     * @param user
     */
    public void addExpense(Expense expense, User user) {
        logger.info("Expense add, " + expense + " , " + user);
        akibaApi.getExpenseApi().add(expense, user);
    }

    public List<Expense> getAllExpenses(User user) {
        logger.info("Get all expenses, " + user);
        return akibaApi.getExpenseApi().getAll(user);
    }

    public AkibaApi getAkibaApi() {
        return akibaApi;
    }

    public void setAkibaApi(AkibaApi akibaApi) {
        this.akibaApi = akibaApi;
    }

    /**
     * Method return list of last expenses of currently logged in user
     * 
     * @param user current user
     * @return list of last expenses
     */
    public List<Expense> getLastExpenses(User user) {
        logger.info("Get last expenses");
        CriteriaBuilder cb = new CriteriaBuilder();
        Criteria criteria = cb.create().withAmountOfResults(amountOfLastExpenses)
                .withSortOrder(CriteriaBuilder.SORT_ASCENDING_ORDER).build();

        return this.akibaApi.getExpenseApi().get(user, criteria);

    }
}
