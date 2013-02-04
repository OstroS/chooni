package pl.akiba.backend.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.akiba.model.entities.Profile;

/**
 * 
 * @author sobczakt
 */
@Controller
@RequestMapping("/{userId}/profile")
public class ProfileController {

    private static final Logger LOGGER = LoggerFactory.getLogger(KindController.class);

    @RequestMapping(value = "/{profileId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Profile> get(@PathVariable final int userId, @PathVariable final int profileId) {
        return null;
    }

    @RequestMapping(value = "/default", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Profile> getDefault(@PathVariable final int userId) {
        return null;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Profile>> getAll(@PathVariable final int userId) {
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Profile> create(@PathVariable final int userId, @RequestBody final Profile profile) {
        return null;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Profile> update(@PathVariable final int userId, @RequestBody final Profile profile) {
        return null;
    }

    @RequestMapping(value = "/{profileId}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> delete(@PathVariable final int userId, @PathVariable final int profileId) {
        return null;
    }

}
