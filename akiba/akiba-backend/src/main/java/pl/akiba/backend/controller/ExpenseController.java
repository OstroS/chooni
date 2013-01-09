package pl.akiba.backend.controller;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.akiba.backend.service.ExpenseService;
import pl.akiba.model.entities.Expense;

/**
 * Provides REST services for CRUD operations on {@link Expense} entity.
 * 
 * @author sobczakt
 */
@Controller
@RequestMapping("/{userId}/expense")
public class ExpenseController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpenseController.class);

    @Autowired
    private ExpenseService expenseService;

    /**
     * Gets user expense by id.
     * 
     * @param userId
     * @param expenseId
     * @return expense entity
     */
    @RequestMapping(value = "/{expenseId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Expense> get(@PathVariable int userId, @PathVariable int expenseId) {
        Expense expense = null;

        try {
            expense = expenseService.get(userId, expenseId);
        } catch (Exception e) {
            LOGGER.error("Exception caught during getting user all expenses: ", e);
            return new ResponseEntity<Expense>(HttpStatus.METHOD_FAILURE);
        }

        if (expense == null) {
            return new ResponseEntity<Expense>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Expense>(expense, HttpStatus.OK);
    }

    /**
     * Gets user expenses.
     * 
     * @param userId
     * @return expense entities collection
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Expense>> getAll(@PathVariable int userId) {
        List<Expense> expenses = null;
        try {
            expenses = expenseService.getAll(userId);
        } catch (Exception e) {
            LOGGER.error("Exception caught during getting user all expenses: ", e);
            return new ResponseEntity<List<Expense>>(HttpStatus.METHOD_FAILURE);
        }

        if (CollectionUtils.isEmpty(expenses)) {
            return new ResponseEntity<List<Expense>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Expense>>(expenses, HttpStatus.OK);
    }

    /**
     * Creates new user expense.
     * 
     * @param userId
     * @param expense
     * @return created expense
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Expense> create(@PathVariable int userId, @RequestBody Expense expense) {
        return new ResponseEntity<Expense>(new Expense(expense.getId()), HttpStatus.CREATED);
    }

    /**
     * Updates user expense data.
     * 
     * @param userId
     * @param expense
     * @return updated expense
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Expense> update(@PathVariable int userId, @RequestBody Expense expense) {
        return new ResponseEntity<Expense>(expense, HttpStatus.CREATED);
    }

    /**
     * Deleted user expense by id.
     * 
     * @param userId
     * @param expenseId
     * @return deleted expense
     */
    @RequestMapping(value = "/{expenseId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable int userId, @PathVariable int expenseId) {
        try {
            expenseService.delete(userId, expenseId);
        } catch (Exception e) {
            LOGGER.error("Exception caught during deleting user expense: ", e);
            return new ResponseEntity<String>(HttpStatus.METHOD_FAILURE);
        }

        return new ResponseEntity<String>(HttpStatus.ACCEPTED);
    }

}
