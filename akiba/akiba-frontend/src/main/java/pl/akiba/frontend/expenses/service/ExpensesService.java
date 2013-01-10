package pl.akiba.frontend.expenses.service;

import java.util.List;
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
        akibaApi.getExpenseApi().add(expense, user);
    }

    public List<Expense> getAllExpenses(User user) {
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
        CriteriaBuilder cb = new CriteriaBuilder();
        Criteria criteria = cb.create().withAmountOfResults(amountOfLastExpenses)
                .withSortOrder(CriteriaBuilder.SORT_ASCENDING_ORDER).build();

        return this.akibaApi.getExpenseApi().get(user, criteria);

    }
}
