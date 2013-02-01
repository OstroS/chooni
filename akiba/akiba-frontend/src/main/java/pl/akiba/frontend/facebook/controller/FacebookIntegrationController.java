package pl.akiba.frontend.facebook.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.akiba.frontend.facebook.service.FacebookLoginService;

/**
 * 
 * Class that implements all of operations that can be done using facebook
 *
 * @author Ostros
 */
@Controller
@RequestMapping("/fb")
public class FacebookIntegrationController {

    @Autowired
    private FacebookLoginService fls;

    /**
     * Method begins facebook login process. 
     * @param request Http Request
     * @param response Http Response
     * @throws IOException
     * @throws URISyntaxException 
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void facebookLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException {
        // redirect
        response.sendRedirect(fls.beginLoginProcess());
    }
}
