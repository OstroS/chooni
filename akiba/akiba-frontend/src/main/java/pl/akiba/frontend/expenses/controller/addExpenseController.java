package pl.akiba.frontend.expenses.controller;

import com.google.common.collect.Lists;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import pl.akiba.model.entities.Expense;
import pl.akiba.model.entities.Kind;
import pl.akiba.model.entities.Profile;
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
        ModelAndView model = new ModelAndView();
        model.setViewName("/expenses/addExpense");
        
        model.addObject("kinds", prepareKindsforUser(new User()));
        model.addObject("profiles", prepareProfilesForUser(new User()));
        
        model.addObject("command", new Expense());
        return model;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView addExpenses(@ModelAttribute("expense") Expense expense, BindingResult result) {
        ModelAndView model = new ModelAndView();
        model.setViewName("/expenses/addExpense");
        model.addObject("command", expense);
        System.out.println(expense.getAmount() + " " + expense.getKind());
         
        return model;
    }
    /**
     * TODO mock method
     * @param user Current user
     * @return List of kinds of expense for given user
     */
    private List<Kind> prepareKindsforUser(User user) {
        return Lists.newArrayList(new Kind(0, "Alkohol"),
                                  new Kind(1, "Zywnosc"),
                                  new Kind(2, "Prasa"),
                                  new Kind(3, "Ksiazki"),
                                  new Kind(4, "Squash"),
                                  new Kind(5, "Biegowki"));
    }

    /**
     * TODO Mock method
     * 
     * @param user Current user
     * @return List of profiles for given user
     */
    private List<Profile> prepareProfilesForUser(User user) {
        return Lists.newArrayList(new Profile(0, "Prywanty"),
                                  new Profile(1, "Biznes"),
                                  new Profile(2, "Rodzinny"));
    }
}
