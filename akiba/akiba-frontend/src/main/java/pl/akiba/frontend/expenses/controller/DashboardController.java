package pl.akiba.frontend.expenses.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

@Controller
@RequestMapping("/dashboard")
@SessionAttributes
public class DashboardController {

    @Autowired
    private ExpensesService es;
    
    @Autowired
    private KindsService ks;
    
    @Autowired
    private ProfilesService ps;
    

    @Autowired
    private UserHelper userHelper;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showExpenses(Principal principal) {
        ModelAndView model = new ModelAndView();

        User user = userHelper.getCurrentUser(principal);

        model.addObject("user", user);

        List<Expense> expenses = es.getLastExpenses(user);
        model.addObject("expenses", expenses);
        
        model.addObject("kinds", ks.getAll(user));
        model.addObject("profiles", ps.getAll(user));
        
        model.setViewName("dashboard/index");
        return model;
    }
}
