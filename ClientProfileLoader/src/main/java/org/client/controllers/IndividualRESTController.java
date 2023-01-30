package org.client.controllers;

import org.client.entities.Individual;
import org.client.service.IndividualService;
import org.client.util.DataInfoHandler;
import org.client.util.NoSuchIndividualException;
import org.client.util.IndividualWithSuchICPExists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller with get and post methods
 */

@RestController
@RequestMapping("/api")
public class IndividualRESTController {

    private IndividualService individualService;
    Logger logger = LoggerFactory.getLogger(IndividualRESTController.class);
    @Autowired
    public void setIndividualService(IndividualService individualService) {
        this.individualService = individualService;
    }

    @GetMapping("/clients/{icp}")
    public ResponseEntity<Individual> getIndividual(@PathVariable("icp") String icp) {
        Individual individual = individualService.findByIcp(icp);

        if (individual == null) {
            throw new NoSuchIndividualException("There is no individual with icp = " + icp + " in Database");
        }
        logger.info("Getting individual");
        return new ResponseEntity<>(individual, HttpStatus.OK);
    }

    @PostMapping("/clients")
    public ResponseEntity<DataInfoHandler> saveIndividual(@RequestBody Individual individual) {
        try {
            individualService.saveIndividual(individual);
            logger.info("Saving individual");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            throw new IndividualWithSuchICPExists("Individual with such icp exists");
        }
    }


}
