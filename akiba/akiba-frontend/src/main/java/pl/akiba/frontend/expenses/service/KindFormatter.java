package pl.akiba.frontend.expenses.service;

import java.text.ParseException;
import java.util.Locale;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.Formatter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.akiba.model.entities.Kind;
import pl.akiba.model.entities.User;

/**
 * 
 * @author OstroS
 */
@Component("kindFormatter")
public class KindFormatter implements Formatter<Kind> {

    @Autowired
    @Qualifier("kindsService")
    KindsService kindsService;
    
    @Autowired
    UserHelper userHelper;

    @Override
    public String print(Kind t, Locale locale) {
        return t.toString();
    }

    @Override
    public Kind parse(String kindId, Locale locale) throws ParseException {
        // FIXME Possibly may cause error in multithreding enviroment
        User currentUser = userHelper.getCurrentUser(SecurityContextHolder.getContext().getAuthentication());
        Logger.getLogger(KindFormatter.class.toString()).info("Current user: " + currentUser + ", kindId=" + kindId);
        return kindsService.get(currentUser.getId(), Long.parseLong(kindId));
 
    }

    public void setKindsService(KindsServiceImpl ks) {
        this.kindsService = ks;
    }
}
