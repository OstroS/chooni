package pl.akiba.frontend.expenses.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import pl.akiba.model.entities.Kind;
import pl.akiba.model.entities.User;
import pl.akiba.wsclient.api.AkibaApi;

/**
 * 
 * @author OstroS
 */
@Component("kindsServiceMock")
public class KindsServiceMock implements KindsService {
    @Autowired
    @Qualifier(value = "akibaApiMock")
    AkibaApi akibaApi;
    
    private static final Logger logger = Logger.getLogger(KindsServiceImpl.class.toString());

    /* (non-Javadoc)
     * @see pl.akiba.frontend.expenses.service.KindsService#prepareKindsforUser(pl.akiba.model.entities.User)
     */
    @Override
    public List<Kind> getAll(User user) {
        logger.info("Prepare kinds for user, " + user);
        return akibaApi.getKindApi().getAll(user);
    }

    Kind getKind(Long kindId) {
        logger.info("Get kind by id=" + kindId);
        return akibaApi.getKindApi().get(kindId);
    }

    /* (non-Javadoc)
     * @see pl.akiba.frontend.expenses.service.KindsService#addKind(pl.akiba.model.entities.Kind, pl.akiba.model.entities.User)
     */
    @Override
    public void add(Kind kind, User user) {
        logger.info("Add kind, " + kind + ", " + user);
        this.akibaApi.getKindApi().add(kind, user);
    }

    public AkibaApi getAkibaApi() {
        return akibaApi;
    }

    public void setAkibaApi(AkibaApi akibaApi) {
        this.akibaApi = akibaApi;
    }

    public Kind get(Long userId, Long kindId) {
        logger.info("Get kind by id=" + kindId);
        return akibaApi.getKindApi().get(kindId);
    }

    @Override
    public Kind get(User user, Long kindId) {
        logger.info("Get kind by id=" + kindId);
        return akibaApi.getKindApi().get(kindId);
    }

}
