package pl.akiba.wsclient.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.akiba.model.entities.Expense;
import pl.akiba.wsclient.api.AkibaApi;
import pl.akiba.wsclient.api.CrudApi;

/**
 * Implementation of Akiba API base on json restful webservice
 * @author OstroS
 */
@Component("akibaApi")
public class AkibaApiRestTemplateImpl implements AkibaApi {

    @Autowired
    private CrudApi<Expense> expenseApi;
    
    @Override
    public CrudApi<Expense> getExpenseApi() {
        return expenseApi;
    }
    
    public void setExpenseApi(CrudApi<Expense> expenseApi) {
        this.expenseApi = expenseApi;
    }
    
}
