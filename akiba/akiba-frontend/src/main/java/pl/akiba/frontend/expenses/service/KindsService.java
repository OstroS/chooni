package pl.akiba.frontend.expenses.service;

import com.google.common.collect.Lists;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
    AkibaApi akibaApi;
    
    public List<Kind> prepareKindsforUser(User user) {
        System.out.println("prepareKindsForUser");
        return akibaApi.getKindApi().getAll(user);
    }

    Kind getKind(Long kindId) {
       return akibaApi.getKindApi().get(kindId);
    }

    public AkibaApi getAkibaApi() {
        return akibaApi;
    }

    public void setAkibaApi(AkibaApi akibaApi) {
        this.akibaApi = akibaApi;
    }
    
    
}
