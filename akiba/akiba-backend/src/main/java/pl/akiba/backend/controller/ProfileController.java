package pl.akiba.backend.controller;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.akiba.model.entities.Profile;
import pl.akiba.model.exception.EmptyResultException;
import pl.akiba.model.exception.EntityIsNotValidException;
import pl.akiba.model.service.ProfileService;

/**
 * 
 * @author sobczakt
 */
@Controller
@RequestMapping("/{userId}/profile")
public class ProfileController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileController.class);

    @Autowired
    private ProfileService profileService;

    @RequestMapping(value = "/default", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Profile> getDefault(@PathVariable final long userId) {
        Profile profile = null;

        try {
            profile = profileService.getDefault(userId);
        } catch (EmptyResultException e) {
            LOGGER.warn("Default profile [userId:" + userId + "] doesn't exist!");
            return new ResponseEntity<Profile>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LOGGER.error("Exception caught during getting user's default profile: ", e);
            return new ResponseEntity<Profile>(HttpStatus.METHOD_FAILURE);
        }

        return new ResponseEntity<Profile>(profile, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Profile>> getAll(@PathVariable final long userId) {
        List<Profile> profiles = null;

        try {
            profiles = profileService.getAll(userId);
        } catch (Exception e) {
            LOGGER.error("Exception caught during getting user's all profiles: ", e);
            return new ResponseEntity<List<Profile>>(HttpStatus.METHOD_FAILURE);
        }

        if (CollectionUtils.isEmpty(profiles)) {
            return new ResponseEntity<List<Profile>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Profile>>(profiles, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Profile> create(@PathVariable final long userId, @RequestBody final Profile profile) {
        Profile createdProfile = null;

        try {
            createdProfile = profileService.create(userId, profile);
        } catch (EntityIsNotValidException e) {
            LOGGER.error("Exception caught during creating user's [id: " + userId + "] profile: ", e);
            return new ResponseEntity<Profile>(HttpStatus.METHOD_FAILURE);
        } catch (Exception e) {
            LOGGER.error("Exception caught during creating user's [id: " + userId + "] profile: ", e);
            return new ResponseEntity<Profile>(HttpStatus.METHOD_FAILURE);
        }

        return new ResponseEntity<Profile>(createdProfile, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Profile> update(@PathVariable final long userId, @RequestBody final Profile profile) {
        try {
            profileService.update(userId, profile);
        } catch (EntityIsNotValidException e) {
            LOGGER.error("Exception caught during updating user's [id: " + userId + "] profile [id: " + profile.getId()
                    + "] ", e);
            return new ResponseEntity<Profile>(HttpStatus.METHOD_FAILURE);
        } catch (Exception e) {
            LOGGER.error("Exception caught during updating user's [id: " + userId + "] profile [id: " + profile.getId()
                    + "] ", e);
            return new ResponseEntity<Profile>(HttpStatus.METHOD_FAILURE);
        }

        return new ResponseEntity<Profile>(profile, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{profileId}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> delete(@PathVariable final long userId, @PathVariable final int profileId) {
        try {
            profileService.delete(userId, profileId);
        } catch (Exception e) {
            LOGGER.error("Exception caught during deleting user's profile: ", e);
            return new ResponseEntity<HttpStatus>(HttpStatus.METHOD_FAILURE);
        }

        return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
    }

}
