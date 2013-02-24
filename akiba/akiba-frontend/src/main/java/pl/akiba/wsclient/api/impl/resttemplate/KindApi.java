package pl.akiba.wsclient.api.impl.resttemplate;

import com.google.common.collect.Lists;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import pl.akiba.model.entities.Expense;
import pl.akiba.model.entities.Kind;
import pl.akiba.model.entities.User;
import pl.akiba.wsclient.api.Criteria;
import pl.akiba.wsclient.api.CrudApi;

/**
 * 
 * @author OstroS
 */
@Component("kindApi")
public class KindApi implements CrudApi<Kind> {

    @Autowired
    RestTemplate rest;

    @Override
    public void add(Kind entity, User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Kind> getAll(User user) {
        ResponseEntity<Kind[]> entity = rest.getForEntity("http://localhost:8080/akiba-backend/0/kind", Kind[].class);
        return Lists.newArrayList(entity.getBody());
    }

    @Override
    public Kind get(Long entityId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Kind update(Kind entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Kind entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Kind> get(User user, Criteria criteria) {
        // TODO Auto-generated method stub
        return null;
    }

}
