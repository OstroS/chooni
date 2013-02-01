package pl.akiba.wsclient.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pl.akiba.model.entities.Expense;
import pl.akiba.model.entities.Kind;
import pl.akiba.model.entities.Profile;
import pl.akiba.wsclient.api.AkibaApi;
import pl.akiba.wsclient.api.CrudApi;
import pl.akiba.wsclient.api.UserApi;

/**
 * Implementation of Akiba API base on json restful webservice
 * 
 * @author OstroS
 */
@Component("akibaApi")
public class AkibaApiRestTemplateImpl implements AkibaApi {

    @Autowired()
    @Qualifier("expenseApi")
    private CrudApi<Expense> expenseApi;

    @Autowired()
    @Qualifier("kindApi")
    private CrudApi<Kind> kindApi;
    
    @Autowired
    @Qualifier("profileApi")
    private CrudApi<Profile> profileApi;

    @Override
    public CrudApi<Expense> getExpenseApi() {
        return expenseApi;
    }

    public void setExpenseApi(CrudApi<Expense> expenseApi) {
        this.expenseApi = expenseApi;
    }

    @Override
    public CrudApi<Kind> getKindApi() {
        return this.kindApi;
    }

    public void setKindApi(CrudApi<Kind> kindApi) {
        this.kindApi = kindApi;
    }

    @Override
    public CrudApi<Profile> getProfileApi() {
        return this.profileApi;
    }
    
    public void setProfileApi(CrudApi<Profile> profileApi) {
        this.profileApi = profileApi;
    }

    @Override
    public UserApi getUserApi() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
