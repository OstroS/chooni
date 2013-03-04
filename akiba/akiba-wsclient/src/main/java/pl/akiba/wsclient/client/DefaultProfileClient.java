package pl.akiba.wsclient.client;

import java.util.List;

import org.eclipse.jetty.client.HttpClient;

import pl.akiba.model.entities.Profile;
import pl.akiba.model.exception.EmptyResultException;
import pl.akiba.model.exception.EntityIsNotValidException;
import pl.akiba.model.service.ProfileService;

/**
 * 
 * @author sobczakt
 */
public class DefaultProfileClient extends DefaultClient implements ProfileService {

    protected DefaultProfileClient(String address, HttpClient httpClient) {
        super(address, httpClient);
    }

    @Override
    public List<Profile> getAll(int userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Profile getDefault(int userId) throws EmptyResultException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Profile create(int userId, Profile profile) throws EntityIsNotValidException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update(int userId, Profile profile) throws EntityIsNotValidException {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(int userId, int profileId) {
        // TODO Auto-generated method stub

    }

}
