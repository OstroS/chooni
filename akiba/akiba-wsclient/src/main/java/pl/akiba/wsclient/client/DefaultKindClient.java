package pl.akiba.wsclient.client;

import java.util.List;

import org.eclipse.jetty.client.HttpClient;

import pl.akiba.model.entities.Kind;
import pl.akiba.model.exception.EmptyResultException;
import pl.akiba.model.exception.EntityIsNotValidException;
import pl.akiba.model.service.KindService;

/**
 * 
 * @author sobczakt
 */
public class DefaultKindClient extends DefaultClient implements KindService {

    protected DefaultKindClient(String address, HttpClient httpClient) {
        super(address, httpClient);
    }

    @Override
    public Kind get(int userId, int kindId) throws EmptyResultException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Kind> getAll(int userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Kind create(int userId, Kind kind) throws EntityIsNotValidException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update(int userId, Kind kind) throws EntityIsNotValidException {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(int userId, int kindId) {
        // TODO Auto-generated method stub

    }

}
