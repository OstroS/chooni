package pl.akiba.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.akiba.model.entities.User;

/**
 * Provides REST services for manage users accounts.
 * 
 * @author sobczakt
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<User> get(@RequestBody User user) {
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

}
