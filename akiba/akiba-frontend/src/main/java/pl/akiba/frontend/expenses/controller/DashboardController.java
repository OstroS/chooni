package pl.akiba.frontend.expenses.controller;

import java.security.Principal;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import pl.akiba.frontend.expenses.service.ExpensesService;
import pl.akiba.frontend.expenses.service.UsersService;
import pl.akiba.model.entities.Expense;
import pl.akiba.model.entities.User;

@Controller
@RequestMapping("/dashboard")
@SessionAttributes
public class DashboardController {

    @Autowired
    private ExpensesService es;

    @Autowired
    private UsersService us;

    private static final Logger logger = Logger.getLogger(DashboardController.class.toString());

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showExpenses(Principal principal) {
        ModelAndView model = new ModelAndView();

        User user = us.getCurrentUser(principal);

        model.addObject("user", user);

        List<Expense> expenses = es.getLastExpenses(user);
        model.addObject("expenses", expenses);

        model.setViewName("dashboard/index");
        return model;
    }
}
