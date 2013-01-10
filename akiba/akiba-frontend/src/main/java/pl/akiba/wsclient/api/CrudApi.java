package pl.akiba.wsclient.api;

import java.util.List;
import pl.akiba.model.entities.User;

/**
 * 
 * @author OstroS
 */
public interface CrudApi<K> {
    public void add(K entity, User user);

    public List<K> getAll(User user);

    public K get(Long entityId);
    
    public K get(User user, Criteria criteria);

    public K update(K entity);

    public void delete(K entity);
}
