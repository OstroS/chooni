package pl.akiba.frontend.expenses.controller;

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
 * @author OstroS
 */
@Controller
@RequestMapping("/expence/add")
@SessionAttributes
public class addExpenseController {
    
    @RequestMapping(method= RequestMethod.GET)
    public ModelAndView showExpenses() {
        ModelAndView model = prepareModelAndView();
        return model;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView addExpenses(@ModelAttribute("expense") Expense expense, BindingResult result) {
        
        ExpensesService es = new ExpensesService();
        es.addExpense(expense, new User());
        
        ModelAndView model = prepareModelAndView();
         
        return model;
    }

    private ModelAndView prepareModelAndView() {
        ModelAndView model = new ModelAndView();
        model.setViewName("/expenses/addExpense");
        
        KindsService ks = new KindsService();
        model.addObject("kinds", ks.prepareKindsforUser(new User()));
        
        ProfilesService ps = new ProfilesService();
        model.addObject("profiles", ps.prepareProfilesForUser(new User()));
        
        model.addObject("command", new Expense());
        
        return model;
    }



}
