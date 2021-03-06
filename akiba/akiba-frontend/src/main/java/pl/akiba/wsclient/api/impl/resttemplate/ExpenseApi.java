package pl.akiba.wsclient.api.impl.resttemplate;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.akiba.model.entities.Expense;
import pl.akiba.model.entities.User;
import pl.akiba.wsclient.api.Criteria;
import pl.akiba.wsclient.api.CrudApi;

/**
 * Expense api based on json restful web service
 * 
 * @author OstroS
 */
@Component("expenseApi")
public class ExpenseApi implements CrudApi<Expense> {

    @Autowired
    RestTemplate rest;

    private static final String AKIBA_BACKEND_LOCATION = "http://localhost:8080/akiba-backend";
    private static final String GET_ALL_EXPENSES_FOR_USER_SUFFIX = "/expense/{userId}/get/all";

    private static final Logger logger = Logger.getLogger(ExpenseApi.class.toString());
    
    public void add(Expense expense, User user) {
        logger.info("Add: " + expense + ", " + user);
        throw new UnsupportedOperationException("Not supported yet.");
        //
    }

    @Override
    public List<Expense> getAll(User user) {
        ResponseEntity<Expense[]> entity = rest.getForEntity(AKIBA_BACKEND_LOCATION + GET_ALL_EXPENSES_FOR_USER_SUFFIX,
                Expense[].class, user.getId());
        logger.info("GetAllExpenses status code: " + entity.getStatusCode());
        return Lists.newArrayList(entity.getBody());
    }

    @Override
    public Expense get(Long entityId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Expense update(Expense entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Expense entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

     @Override
    public List<Expense> get(User user, Criteria criteria) {
        // TODO Auto-generated method stub
        return null;
    }
}
