package pl.akiba.backend.controller;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.akiba.backend.service.ExpenseService;
import pl.akiba.model.entities.Expense;
import pl.akiba.model.entities.Filter;
import pl.akiba.model.entities.OperationType;

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
    public ResponseEntity<Expense> get(@PathVariable final int userId, @PathVariable final int expenseId) {
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
    public ResponseEntity<List<Expense>> getAll(@PathVariable final int userId, @ModelAttribute Filter filter) {
        List<Expense> expenses = null;

        try {
            expenses = expenseService.getAll(userId, filter);
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
    public ResponseEntity<Expense> create(@PathVariable final int userId, @RequestBody final Expense expense) {
        if (!expense.isValid(OperationType.CREATE)) {
            LOGGER.error("Expense entity is not valid!");
            return new ResponseEntity<Expense>(HttpStatus.FORBIDDEN);
        }

        Expense createdExpense = null;
        try {
            createdExpense = expenseService.create(userId, expense);
        } catch (Exception e) {
            LOGGER.error("Exception caught during creating user [" + userId + "] expense: ", e);
            return new ResponseEntity<Expense>(HttpStatus.METHOD_FAILURE);
        }

        return new ResponseEntity<Expense>(createdExpense, HttpStatus.CREATED);
    }

    /**
     * Updates user expense data.
     * 
     * @param userId
     * @param expense
     * @return updated expense
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Expense> update(@PathVariable final int userId, @RequestBody final Expense expense) {
        if (!expense.isValid(OperationType.UPDATE)) {
            LOGGER.error("Expense entity is not valid!");
            return new ResponseEntity<Expense>(HttpStatus.FORBIDDEN);
        }

        try {
            expenseService.update(userId, expense);
        } catch (Exception e) {
            LOGGER.error("Exception caught during updating user [" + userId + "] expense [id: " + expense.getId()
                    + "] ", e);
            return new ResponseEntity<Expense>(HttpStatus.METHOD_FAILURE);
        }

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
    public ResponseEntity<HttpStatus> delete(@PathVariable final int userId, @PathVariable final int expenseId) {
        try {
            expenseService.delete(userId, expenseId);
        } catch (Exception e) {
            LOGGER.error("Exception caught during deleting user expense: ", e);
            return new ResponseEntity<HttpStatus>(HttpStatus.METHOD_FAILURE);
        }

        return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
    }

}
