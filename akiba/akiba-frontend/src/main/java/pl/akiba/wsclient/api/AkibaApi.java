package pl.akiba.wsclient.api;

import pl.akiba.model.entities.Expense;

/**
 * Akiba Api
 * Implementation of this interface should be injected to application 
 *
 * 
 * @author OstroS
 */

public interface AkibaApi {
    public CrudApi<Expense> getExpenseApi();
}
