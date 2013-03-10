package pl.akiba.frontend.expenses.service;

import java.util.List;

import pl.akiba.model.entities.Expense;
import pl.akiba.model.entities.User;

public interface ExpensesService {

    /**
     * Method adds particular expense to user
     * @param expense Expense to be added
     * @param user User
     */
    public abstract void addExpense(Expense expense, User user);

    public abstract List<Expense> getAllExpenses(User user);

    /**
     * Method return list of last expenses of currently logged in user
     * 
     * @param user current user
     * @return list of last expenses
     */
    public abstract List<Expense> getLastExpenses(User user);

}