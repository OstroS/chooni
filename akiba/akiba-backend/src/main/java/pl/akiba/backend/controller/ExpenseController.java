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
 * 
 * @author sobczakt
 */
@Controller
@RequestMapping("/expense")
public class ExpenseController {

	/**
	 * 
	 */
	@RequestMapping(value = "/{userId}/get/{expenseId}", method = RequestMethod.GET)
	@ResponseBody
	public Expense get(@PathVariable String userId, @PathVariable int expenseId) {
		return new Expense(expenseId);
	}

	/**
	 * 
	 */
	@RequestMapping(value = "/{userId}/get/all", method = RequestMethod.GET)
	@ResponseBody
	public List<Expense> getAll(@PathVariable String userId) {
		List<Expense> expenses = new ArrayList<Expense>();
		expenses.add(new Expense(1));
		expenses.add(new Expense(2));
		expenses.add(new Expense(3));

		return expenses;
	}

	/**
	 * 
	 */
	@RequestMapping(value = "/{userId}/create", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Expense> create(@PathVariable String userId,
			@RequestBody Expense expense) {

		return new ResponseEntity<Expense>(new Expense(expense.getId()),
				HttpStatus.CREATED);
	}

	/**
	 * 
	 */
	@RequestMapping(value = "/{userId}/update", method = RequestMethod.PUT)
	public ResponseEntity<Expense> update(@PathVariable String userId,
			@RequestBody Expense expense) {

		return new ResponseEntity<Expense>(expense, HttpStatus.CREATED);
	}

	/**
	 * 
	 */
	@RequestMapping(value = "/{userId}/delete/{expenseId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable String userId,
			@PathVariable int expenseId) {

		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}

}
