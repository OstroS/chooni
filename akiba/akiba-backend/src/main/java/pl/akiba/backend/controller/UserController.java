package pl.akiba.backend.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.akiba.model.entities.FacebookUser;
import pl.akiba.model.exception.StatusException;
import pl.akiba.model.service.UserService;

/**
 * Provides REST services for manage users.
 * 
 * @author sobczakt
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

//    @Autowired
//    private UserService userService;

    @RequestMapping(value = "/fb", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<FacebookUser> getFacebookUser(@PathVariable final int facebookId) throws StatusException, IOException, InterruptedException {
        FacebookUser facebookUser = null;

        System.out.println("facebookId: " + facebookId);

//        try {
//            facebookUser = userService.getFacebookUser(facebookId);
//        } catch (Exception e) {
//            LOGGER.error("Exception caught during getting facebook user [facebookId: " + facebookId + "]: ", e);
//            return new ResponseEntity<FacebookUser>(HttpStatus.METHOD_FAILURE);
//        }

        return new ResponseEntity<FacebookUser>(facebookUser, HttpStatus.OK);
    }

}
