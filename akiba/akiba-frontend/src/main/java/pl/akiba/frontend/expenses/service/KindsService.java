package pl.akiba.frontend.expenses.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import pl.akiba.frontend.expenses.controller.DashboardController;
import pl.akiba.model.entities.Kind;
import pl.akiba.model.entities.User;
import pl.akiba.wsclient.api.AkibaApi;

/**
 * 
 * @author OstroS
 */
@Component("kindsService")
public class KindsService {
    @Autowired
    @Qualifier(value = "akibaApiMock")
    AkibaApi akibaApi;
    
    private static final Logger logger = Logger.getLogger(KindsService.class.toString());

    public List<Kind> prepareKindsforUser(User user) {
        logger.info("Prepare kinds for user, " + user);
        return akibaApi.getKindApi().getAll(user);
    }

    Kind getKind(Long kindId) {
        logger.info("Get kind by id=" + kindId);
        return akibaApi.getKindApi().get(kindId);
    }

    public void addKind(Kind kind, User user) {
        logger.info("Add kind, " + kind + ", " + user);
        this.akibaApi.getKindApi().add(kind, user);
    }

    public AkibaApi getAkibaApi() {
        return akibaApi;
    }

    public void setAkibaApi(AkibaApi akibaApi) {
        this.akibaApi = akibaApi;
    }

}
