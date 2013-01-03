package pl.akiba.wsclient.api;

import pl.akiba.model.entities.Expense;
import pl.akiba.model.entities.Kind;

/**
 * Akiba Api Implementation of this interface should be injected to application
 * 
 * 
 * @author OstroS
 */

public interface AkibaApi {
    public CrudApi<Expense> getExpenseApi();

    public CrudApi<Kind> getKindApi();
}
