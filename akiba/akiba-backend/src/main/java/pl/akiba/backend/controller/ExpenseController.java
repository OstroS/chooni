package pl.akiba.backend.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
@RequestMapping("/expense")
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;

	/**
	 * Gets user expense by id.
	 * 
	 * @param userId
	 * @param expenseId
	 * @return expense entity
	 */
	@RequestMapping(value = "/{userId}/get/{expenseId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Expense> get(@PathVariable String userId,
			@PathVariable int expenseId) {
		return new ResponseEntity<Expense>(new Expense(expenseId),
				HttpStatus.OK);
	}

	/**
	 * Gets user expenses.
	 * 
	 * @param userId
	 * @return expense entities collection
	 */
	@RequestMapping(value = "/{userId}/get/all", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Expense>> getAll(@PathVariable String userId) {
                List<Expense> expenses = new ArrayList();
                
                // wzbogacone dane testowe
                Expense ex = new Expense();
                ex.setId(0);
                ex.setAmount(new BigDecimal(12323));
                expenses.add(ex);
                
                ex = new Expense();
                ex.setId(1);
                ex.setAmount(new BigDecimal(13212312));
                expenses.add(ex);
                
                ex = new Expense();
                ex.setId(2);
                ex.setAmount(new BigDecimal(23992123));
                expenses.add(ex);
		
		expenses.add(new Expense(3));
		expenses.add(new Expense(4));
		expenses.add(new Expense(5));

		return new ResponseEntity<List<Expense>>(expenses, HttpStatus.OK);
	}

	/**
	 * Creates new user expense.
	 * 
	 * @param userId
	 * @param expense
	 * @return created expense
	 */
	@RequestMapping(value = "/{userId}/create", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Expense> create(@PathVariable String userId,
			@RequestBody Expense expense) {

		return new ResponseEntity<Expense>(new Expense(expense.getId()),
				HttpStatus.CREATED);
	}

	/**
	 * Updates user expense data.
	 * 
	 * @param userId
	 * @param expense
	 * @return updated expense
	 */
	@RequestMapping(value = "/{userId}/update", method = RequestMethod.PUT)
	public ResponseEntity<Expense> update(@PathVariable String userId,
			@RequestBody Expense expense) {

		return new ResponseEntity<Expense>(expense, HttpStatus.CREATED);
	}

	/**
	 * Deleted user expense by id.
	 * 
	 * @param userId
	 * @param expenseId
	 * @return deleted expense
	 */
	@RequestMapping(value = "/{userId}/delete/{expenseId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable String userId,
			@PathVariable int expenseId) {

		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}

}
