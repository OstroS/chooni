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

import pl.akiba.model.entities.Kind;

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

    /**
     * Gets kind by id.
     */
    @RequestMapping(value = "/{kindId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Kind> get(@PathVariable final int userId, @PathVariable final int kindId) {
        return null;
    }

    /**
     * Gets kinds.
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Kind>> getAll(@PathVariable final int userId) {
        return null;
    }

    /**
     * Creates new kind.
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Kind> create(@PathVariable final int userId, @RequestBody final Kind kind) {
        return null;
    }

    /**
     * Updates kind's data.
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Kind> update(@PathVariable final int userId, @RequestBody final Kind kind) {
        return null;
    }

    /**
     * Deletes kind by id.
     */
    @RequestMapping(value = "/{kindId}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> delete(@PathVariable final int userId, @PathVariable final int kindId) {
        return null;
    }

}
