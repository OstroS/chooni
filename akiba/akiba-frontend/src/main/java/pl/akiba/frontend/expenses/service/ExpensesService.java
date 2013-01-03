package pl.akiba.frontend.expenses.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.akiba.model.entities.Expense;
import pl.akiba.model.entities.User;
import pl.akiba.wsclient.api.AkibaApi;

/**
 * 
 * @author OstroS
 */
@Component("expensesService")
public class ExpensesService {

    @Autowired
    AkibaApi akibaApi;

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

}
