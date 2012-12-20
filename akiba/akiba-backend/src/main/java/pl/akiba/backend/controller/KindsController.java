/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.akiba.backend.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.akiba.model.entities.Kind;

/**
 *
 * @author OstroS
 */
@Controller
@RequestMapping("/{userId}/kind")
public class KindsController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Kind>> getAll(@PathVariable String userId) {
        List<Kind> kinds = new ArrayList();

        // should fetch kinds for user 
        // test data:

        kinds.add(new Kind(0, "Alkohol"));
        kinds.add(new Kind(1, "Zywnosc"));
        kinds.add(new Kind(2, "Prasa"));
        kinds.add(new Kind(3, "Ksiazki"));
        kinds.add(new Kind(4, "Squash"));
        kinds.add(new Kind(5, "Biegowki"));

        return new ResponseEntity<>(kinds, HttpStatus.OK);
    }
}
