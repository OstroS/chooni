package pl.akiba.frontend.expenses.service;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import pl.akiba.model.entities.Kind;

/**
 * 
 * @author OstroS
 */
@Component("kindFormatter")
public class KindFormatter implements Formatter<Kind> {

    @Autowired
    KindsService kindsService;

    @Override
    public String print(Kind t, Locale locale) {
        return t.toString();
    }

    @Override
    public Kind parse(String kindId, Locale locale) throws ParseException {
        return kindsService.getKind(Long.parseLong(kindId));
    }

    public void setKindsService(KindsService ks) {
        this.kindsService = ks;
    }
}
