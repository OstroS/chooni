package pl.akiba.wsclient.api.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import pl.akiba.model.entities.Profile;
import pl.akiba.model.entities.User;
import pl.akiba.wsclient.api.Criteria;
import pl.akiba.wsclient.api.CrudApi;

/**
 * @author OstroS
 * 
 */
@Component("profileApi")
public class ProfileApi implements CrudApi<Profile> {

    @Override
    public void add(Profile entity, User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not supported yet.");

    }

    @Override
    public List<Profile> getAll(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Profile get(Long entityId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Profile update(Profile entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Profile entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Profile get(Criteria criteria) {
        // TODO Auto-generated method stub
        return null;
    }

}
