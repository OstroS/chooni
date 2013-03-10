package pl.akiba.frontend.expenses.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

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
import pl.akiba.frontend.expenses.service.UserHelper;
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

    @Autowired
    private UserHelper userHelper;

    private static final Logger logger = Logger.getLogger(ExpenseController.class.toString());

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showExpenses(Principal principal) {
        ModelAndView model = new ModelAndView();
        List<Expense> allExpenses = es.getAllExpenses(userHelper.getCurrentUser(principal));
        model.addObject("expenses", allExpenses);
        model.setViewName("expenses/expenseList");
        return model;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addExpenseForm(Principal principal) {
        ModelAndView model = prepareModelAndView(userHelper.getCurrentUser(principal));
        return model;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView handleAddExpenseForm(@ModelAttribute("expense") Expense expense, BindingResult result, Principal principal) {
        User currentUser = userHelper.getCurrentUser(principal);
        
        expense.setDate(new Date());
        es.addExpense(expense, currentUser);

        ModelAndView model = prepareModelAndView(currentUser);

        return model;
    }

    private ModelAndView prepareModelAndView(User currentUser) {
        ModelAndView model = new ModelAndView();
        model.setViewName("/expenses/addExpense");

        model.addObject("kinds", kindsService.prepareKindsforUser(currentUser));
        model.addObject("profiles", profilesService.prepareProfilesForUser(currentUser));

        model.addObject("command", new Expense());

        return model;
    }
}
