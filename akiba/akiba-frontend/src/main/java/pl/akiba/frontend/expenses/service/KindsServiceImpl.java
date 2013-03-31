package pl.akiba.frontend.expenses.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.akiba.model.entities.Kind;
import pl.akiba.model.entities.User;
import pl.akiba.model.exception.StatusException;
import pl.akiba.wsclient.client.DefaultKindClient;


/**
 * 
 * @author OstroS
 */
@Component("kindsService")
public class KindsServiceImpl implements KindsService {

    @Autowired
    private DefaultKindClient kindClient;
    
    private static final Logger logger = Logger.getLogger(KindsServiceImpl.class.toString());
    
    @Override
    public List<Kind> getAll(User user) {
        try {
            return kindClient.getAll(user.getId(), user.getAuthenticationCode());
        } catch (StatusException | IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            logger.severe(e.toString());
        }
        return new ArrayList<Kind>();
        
    }

    @Override
    public void add(Kind kind, User user) {
        try {
            logger.info("Add kind: " + kind + ", " + user);
            kindClient.create(user.getId(), user.getAuthenticationCode(), kind);
        } catch (StatusException | IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            logger.severe(e.toString());
        }
        
    }

    @Override
    public Kind get(User user, Long kindId) {
        try {
            return kindClient.get(user.getId(), user.getAuthenticationCode(), (int)(long)kindId);
        } catch (StatusException | IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            logger.severe(e.toString());
        }
        return null;
    }
   

}
