/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.akiba.frontend.expenses.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import pl.akiba.frontend.expenses.service.ExpensesService;
import pl.akiba.model.entities.Expense;
import pl.akiba.model.entities.User;

/**
 *
 * @author kostrows
 */
@Controller
@RequestMapping("/expense")
@SessionAttributes
public class showExpensesController {
        
    @Autowired
    private ExpensesService es;
    
    @RequestMapping(method= RequestMethod.GET)
    public ModelAndView showExpenses() {
        ModelAndView model = new ModelAndView();
        List<Expense> allExpenses = es.getAllExpenses(new User());
        model.addObject("expenses", allExpenses);
        model.setViewName("expenses/expenseList");
        return model;
    }
}
