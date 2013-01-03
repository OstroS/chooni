package pl.akiba.wsclient.api.impl;

import com.google.common.collect.Lists;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.akiba.model.entities.Expense;
import pl.akiba.model.entities.User;
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

    public void add(Expense expense, User user) {
        throw new UnsupportedOperationException("Not supported yet.");
        //
    }

    @Override
    public List<Expense> getAll(User user) {
        ResponseEntity<Expense[]> entity = rest.getForEntity(AKIBA_BACKEND_LOCATION + GET_ALL_EXPENSES_FOR_USER_SUFFIX,
                Expense[].class, user.getId());
        System.out.println("GetAllExpenses status code: " + entity.getStatusCode());
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
}
