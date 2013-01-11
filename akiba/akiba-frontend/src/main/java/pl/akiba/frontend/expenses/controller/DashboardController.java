package pl.akiba.frontend.expenses.controller;



import java.util.List;

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
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showExpenses() {
        ModelAndView model = new ModelAndView();
        
        User currentUser = us.getCurrentUser();
        model.addObject("user", currentUser);
        
        List<Expense> expenses = es.getLastExpenses(currentUser);
        model.addObject("expenses", expenses);
        
        model.setViewName("dashboard/index");
        return model;
    }
}
