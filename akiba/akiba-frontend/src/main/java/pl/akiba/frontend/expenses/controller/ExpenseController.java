package pl.akiba.frontend.expenses.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import pl.akiba.frontend.expenses.service.ExpensesService;
import pl.akiba.frontend.expenses.service.KindsService;
import pl.akiba.frontend.expenses.service.ProfilesService;
import pl.akiba.model.entities.Expense;
import pl.akiba.model.entities.User;

/**
 * Controller that implements adding new expense logic
 * 
 * @author OstroS
 */
@Controller
@RequestMapping("/expense")
@SessionAttributes
public class ExpenseController {

    @Autowired
    private ExpensesService es;
    @Autowired
    private KindsService kindsService;
    @Autowired
    private ProfilesService profilesService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showExpenses() {
        ModelAndView model = new ModelAndView();
        List<Expense> allExpenses = es.getAllExpenses(getCurrentUser());
        model.addObject("expenses", allExpenses);
        model.setViewName("expenses/expenseList");
        return model;
    }
  

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addExpenseForm() {
        ModelAndView model = prepareModelAndView();
        return model;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView handleAddExpenseForm(@ModelAttribute("expense") Expense expense, BindingResult result) {

        es.addExpense(expense, getCurrentUser());

        ModelAndView model = prepareModelAndView();

        return model;
    }

    private ModelAndView prepareModelAndView() {
        ModelAndView model = new ModelAndView();
        model.setViewName("/expenses/addExpense");

        model.addObject("kinds", kindsService.prepareKindsforUser(getCurrentUser()));
        model.addObject("profiles", profilesService.prepareProfilesForUser(getCurrentUser()));

        model.addObject("command", new Expense());

        return model;
    }
    
    private User getCurrentUser() {
        User user = new User();
        user.setId(0L);
        user.setName("Użyszkodnik testowy");
        return user;
    }
}
