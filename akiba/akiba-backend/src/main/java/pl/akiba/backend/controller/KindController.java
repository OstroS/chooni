package pl.akiba.backend.controller;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.akiba.backend.service.KindService;
import pl.akiba.model.entities.Kind;
import pl.akiba.model.exception.EntityIsNotValidException;

/**
 * Provides REST services for CRUD operations on {@link Kind} entity.
 * 
 * @author sobczakt
 * 
 */
@Controller
@RequestMapping("/{userId}/kind")
public class KindController {

    private static final Logger LOGGER = LoggerFactory.getLogger(KindController.class);

    @Autowired
    private KindService kindService;

    /**
     * Gets kind by id.
     */
    @RequestMapping(value = "/{kindId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Kind> get(@PathVariable final int userId, @PathVariable final int kindId) {
        Kind kind = null;

        try {
            kind = kindService.get(userId, kindId);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.warn("Kind [userId:" + userId + ", kindId:" + kindId + "] doesn't exist!");
            return new ResponseEntity<Kind>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LOGGER.error("Exception caught during getting user's kind: ", e);
            return new ResponseEntity<Kind>(HttpStatus.METHOD_FAILURE);
        }

        return new ResponseEntity<Kind>(kind, HttpStatus.OK);
    }

    /**
     * Gets kinds.
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Kind>> getAll(@PathVariable final int userId) {
        List<Kind> kinds = null;

        try {
            kinds = kindService.getAll(userId);
        } catch (Exception e) {
            LOGGER.error("Exception caught during getting user's all kinds: ", e);
            return new ResponseEntity<List<Kind>>(HttpStatus.METHOD_FAILURE);
        }

        if (CollectionUtils.isEmpty(kinds)) {
            return new ResponseEntity<List<Kind>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Kind>>(kinds, HttpStatus.OK);
    }

    /**
     * Creates new kind.
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Kind> create(@PathVariable final int userId, @RequestBody final Kind kind) {
        Kind createdKind = null;

        try {
            createdKind = kindService.create(userId, kind);
        } catch (EntityIsNotValidException e) {
            LOGGER.error("Exception caught during creating user's [id: " + userId + "] kind: ", e);
            return new ResponseEntity<Kind>(HttpStatus.METHOD_FAILURE);
        } catch (Exception e) {
            LOGGER.error("Exception caught during creating user's [id: " + userId + "] kind: ", e);
            return new ResponseEntity<Kind>(HttpStatus.METHOD_FAILURE);
        }

        return new ResponseEntity<Kind>(createdKind, HttpStatus.CREATED);
    }

    /**
     * Updates kind's data.
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Kind> update(@PathVariable final int userId, @RequestBody final Kind kind) {
        try {
            kindService.update(userId, kind);
        } catch (EntityIsNotValidException e) {
            LOGGER.error("Exception caught during updating user's [id: " + userId + "] kind [id: " + kind.getId()
                    + "] ", e);
            return new ResponseEntity<Kind>(HttpStatus.METHOD_FAILURE);
        } catch (Exception e) {
            LOGGER.error("Exception caught during updating user's [id: " + userId + "] kind [id: " + kind.getId()
                    + "] ", e);
            return new ResponseEntity<Kind>(HttpStatus.METHOD_FAILURE);
        }

        return new ResponseEntity<Kind>(kind, HttpStatus.CREATED);
    }

    /**
     * Deletes kind by id.
     */
    @RequestMapping(value = "/{kindId}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> delete(@PathVariable final int userId, @PathVariable final int kindId) {
        try {
            kindService.delete(userId, kindId);
        } catch (Exception e) {
            LOGGER.error("Exception caught during deleting user's kind: ", e);
            return new ResponseEntity<HttpStatus>(HttpStatus.METHOD_FAILURE);
        }

        return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
    }

}
