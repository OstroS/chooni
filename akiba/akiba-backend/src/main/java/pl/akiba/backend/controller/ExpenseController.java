package pl.akiba.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.akiba.model.entities.Expense;

/**
 * Provides REST services for CRUD operations on {@link Expense} entity.
 * 
 * @author sobczakt
 */
@Controller
@RequestMapping("/{userId}/expense")
public class ExpenseController {

	/**
	 * Gets user expense by id.
	 * 
	 * @param userId
	 * @param expenseId
	 * @return expense entity
	 */
	@RequestMapping(value = "/{expenseId}", method = RequestMethod.GET)
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
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Expense>> getAll(@PathVariable String userId) {
		List<Expense> expenses = new ArrayList<Expense>();
		expenses.add(new Expense(1));
		expenses.add(new Expense(2));
		expenses.add(new Expense(3));

		return new ResponseEntity<List<Expense>>(expenses, HttpStatus.OK);
	}

	/**
	 * Creates new user expense.
	 * 
	 * @param userId
	 * @param expense
	 * @return created expense
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Expense> create(@PathVariable String userId,
			@RequestBody Expense expense) {

		return new ResponseEntity<Expense>(new Expense(expense.getId()),
				HttpStatus.CREATED);
	}

	/**
	 * Updates user expense data (whole collection)
	 * 
	 * @param userId
	 * @param expense
	 * @return updated expense
	 */
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<Expense> update(@PathVariable String userId,
			@RequestBody Expense expense) {

		return new ResponseEntity<Expense>(expense, HttpStatus.CREATED);
	}
        
        /**
	 * Updates user expense data (one particular)
	 * 
	 * @param userId
	 * @param expense
	 * @return updated expense
	 */
	@RequestMapping(value = "/{expenseId}", method = RequestMethod.PUT)
	public ResponseEntity<Expense> updateExpense(@PathVariable String userId,
			@PathVariable Integer expenseId, @RequestBody Expense expense) {

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
	public ResponseEntity<String> delete(@PathVariable String userId,
			@PathVariable int expenseId) {

		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}

}
