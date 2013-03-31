package pl.akiba.frontend.expenses.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import pl.akiba.frontend.expenses.service.UserHelper;
import pl.akiba.frontend.kinds.service.KindsService;
import pl.akiba.model.entities.Kind;

@Controller
@RequestMapping("/kind")
@SessionAttributes
public class KindController {

    @Autowired
    @Qualifier("kindsService")
    private KindsService kindsService;

    @Autowired
    private UserHelper userHelper;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addKind(Principal principal) {
        ModelAndView model = new ModelAndView();
        model.setViewName("/kinds/addKind");
        model.addObject("command", new Kind());

        List<Kind> currentKinds = kindsService.getAll(userHelper.getCurrentUser(principal));
        model.addObject("currentKinds", currentKinds);
        return model;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView handleAddKindRequest(@ModelAttribute("kind") Kind kind, BindingResult result,
            Principal principal) {
        kindsService.add(kind, userHelper.getCurrentUser(principal));

        ModelAndView model = new ModelAndView();
        model.setViewName("/kinds/addKind");
        model.addObject("command", new Kind());

        return model;
    }

}
