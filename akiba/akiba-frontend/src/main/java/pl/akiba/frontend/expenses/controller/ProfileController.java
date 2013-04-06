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
import pl.akiba.frontend.profiles.service.ProfilesService;
import pl.akiba.model.entities.Profile;



@Controller
@RequestMapping("/profile")
@SessionAttributes
public class ProfileController {
    @Autowired
    @Qualifier("profilesService")
    private ProfilesService profilesService;

    @Autowired
    private UserHelper userHelper;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addProfile(Principal principal) {
        ModelAndView model = new ModelAndView();
        model.setViewName("/profiles/addProfile");
        model.addObject("command", new Profile());

        List<Profile> currentProfiles = profilesService.getAll(userHelper.getCurrentUser(principal));
        model.addObject("currentProfiles", currentProfiles);
        return model;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView handleAddKindRequest(@ModelAttribute("kind") Profile profile, BindingResult result,
            Principal principal) {
        profilesService.add(userHelper.getCurrentUser(principal), profile);

        ModelAndView model = new ModelAndView();
        model.setViewName("/profiles/addProfile");
        model.addObject("command", new Profile());

        return model;
    }


}
