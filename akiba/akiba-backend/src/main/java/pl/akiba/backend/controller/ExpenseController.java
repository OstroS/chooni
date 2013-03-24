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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.akiba.model.entities.Expense;
import pl.akiba.model.entities.Filter;
import pl.akiba.model.exception.EmptyResultException;
import pl.akiba.model.exception.EntityIsNotValidException;
import pl.akiba.model.service.ExpenseService;

/**
 * Provides REST services for CRUD operations on {@link Expense} entity.
 * 
 * @author sobczakt
 */
@Controller
@RequestMapping("/{userId}/expense")
public class ExpenseController {

    /**
     * Nazwa headera w jakim znajduje sie authCode
     */
    private static final String AUTH_CODE_HEADER = "x-akiba-auth-code";
    
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
    public ResponseEntity<Expense> get(
            @RequestHeader(value = AUTH_CODE_HEADER, required = true) final String authCode,
            @PathVariable final long userId, @PathVariable final int expenseId) {

        Expense expense = null;

        try {
            expense = expenseService.get(userId, authCode, expenseId);
        } catch (EmptyResultException e) {
            LOGGER.warn("Expense [userId:" + userId + ", expenseId:" + expenseId + "] doesn't exist!");
            return new ResponseEntity<Expense>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LOGGER.error("Exception caught during getting user's expense: ", e);
            return new ResponseEntity<Expense>(HttpStatus.METHOD_FAILURE);
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
    public ResponseEntity<List<Expense>> getAll(
            @RequestHeader(value = AUTH_CODE_HEADER, required = true) final String authCode,
            @PathVariable final long userId, @ModelAttribute Filter filter) {
        List<Expense> expenses = null;

        try {
            expenses = expenseService.getAll(userId, authCode, filter);
        } catch (Exception e) {
            LOGGER.error("Exception caught during getting user's all expenses: ", e);
            return new ResponseEntity<List<Expense>>(HttpStatus.METHOD_FAILURE);
        }

        if (CollectionUtils.isEmpty(expenses)) {
            return new ResponseEntity<List<Expense>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Expense>>(expenses, HttpStatus.OK);
    }

    /**
     * 
     */
    @RequestMapping(value = "/total", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Double> getTotal(
            @RequestHeader(value = AUTH_CODE_HEADER, required = true) final String authCode,
            @PathVariable final long userId, @ModelAttribute Filter filter) {
        double total = 0;

        try {
            total = expenseService.getTotal(userId, authCode, filter);
        } catch (Exception e) {
            LOGGER.error("Exception caught during getting user's total expense: ", e);
            return new ResponseEntity<Double>(HttpStatus.METHOD_FAILURE);
        }

        return new ResponseEntity<Double>(total, HttpStatus.OK);
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
    public ResponseEntity<Expense> create(
            @RequestHeader(value = AUTH_CODE_HEADER, required = true) final String authCode,
            @PathVariable final long userId, @RequestBody final Expense expense) {
        Expense createdExpense = null;

        try {
            createdExpense = expenseService.create(userId, authCode, expense);
        } catch (EntityIsNotValidException e) {
            LOGGER.error("Exception caught during creating user [id: " + userId + "] expense: ", e);
            return new ResponseEntity<Expense>(HttpStatus.METHOD_FAILURE);
        } catch (Exception e) {
            LOGGER.error("Exception caught during creating user [id: " + userId + "] expense: ", e);
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
    public ResponseEntity<Expense> update(
            @RequestHeader(value = AUTH_CODE_HEADER, required = true) final String authCode,
            @PathVariable final long userId, @RequestBody final Expense expense) {
        try {
            expenseService.update(userId, authCode, expense);
        } catch (EntityIsNotValidException e) {
            LOGGER.error("Exception caught during updating user [id: " + userId + "] expense [id: " + expense.getId()
                    + "] ", e);
            return new ResponseEntity<Expense>(HttpStatus.METHOD_FAILURE);
        } catch (Exception e) {
            LOGGER.error("Exception caught during updating user [id: " + userId + "] expense [id: " + expense.getId()
                    + "] ", e);
            return new ResponseEntity<Expense>(HttpStatus.METHOD_FAILURE);
        }

        return new ResponseEntity<Expense>(expense, HttpStatus.CREATED);
    }

    /**
     * Deletes user expense by id.
     * 
     * @param userId
     * @param expenseId
     * @return deleted expense
     */
    @RequestMapping(value = "/{expenseId}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> delete(
            @RequestHeader(value = AUTH_CODE_HEADER, required = true) final String authCode,
            @PathVariable final long userId, @PathVariable final int expenseId) {
        try {
            expenseService.delete(userId, authCode, expenseId);
        } catch (Exception e) {
            LOGGER.error("Exception caught during deleting user expense: ", e);
            return new ResponseEntity<HttpStatus>(HttpStatus.METHOD_FAILURE);
        }

        return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
    }

}
