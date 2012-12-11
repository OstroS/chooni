package pl.akiba.frontend.expenses.service;

import com.google.common.collect.Lists;
import java.util.List;
import pl.akiba.model.entities.Kind;
import pl.akiba.model.entities.User;

/**
 *
 * @author OstroS
 */
public class KindsService {
    private final static List<Kind> kinds;
    
    static {
        kinds = Lists.newArrayList(new Kind(0, "Alkohol"),
                                  new Kind(1, "Zywnosc"),
                                  new Kind(2, "Prasa"),
                                  new Kind(3, "Ksiazki"),
                                  new Kind(4, "Squash"),
                                  new Kind(5, "Biegowki"));
    }
    /**
     * TODO mock method
     * @param user Current user
     * @return List of kinds of expense for given user
     */
    public List<Kind> prepareKindsforUser(User user) {
        return kinds;
    }

    Kind getKind(int kindId) {
       return kinds.get(kindId);
    }
}
