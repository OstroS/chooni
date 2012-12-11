package pl.akiba.frontend.expenses.service;

import org.springframework.stereotype.Component;
import pl.akiba.model.entities.Expense;
import pl.akiba.model.entities.User;


/**
 *
 * @author OstroS
 */
@Component("expensesService")
public class ExpensesService {
    
    /**
     * @TODO
     * @param expense
     * @param user 
     */
    public void addExpense(Expense expense, User user) {
        System.out.println("Expense added;" + expense );
    }
}
