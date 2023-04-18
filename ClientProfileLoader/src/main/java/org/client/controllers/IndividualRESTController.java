package org.client.controllers;

import lombok.extern.slf4j.Slf4j;
import org.client.common.dto.PhoneNumberDto;
import org.client.common.entity.Contacts.Email;
import org.client.common.entity.Individual;
import org.client.common.entity.RFPassport;
import org.client.service.IndividualService;
import org.client.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.client.common.entity.Contacts.PhoneNumber;

/**
 * REST controller for individual
 */

@RestController
@RequestMapping("/api")
@Slf4j
public class IndividualRESTController {

    private IndividualService individualService;

    Logger logger = LoggerFactory.getLogger(IndividualRESTController.class);
    @Autowired
    public void setIndividualService(IndividualService individualService) {
        this.individualService = individualService;
    }

    @GetMapping("/clients/{icp}") // http://localhost:8085/api/clients/2340
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
    @PutMapping("/clients/{icp}")
    public ResponseEntity<DataInfoHandler> saveRFPassport(@RequestBody RFPassport rfPassport, @PathVariable("icp") String icp) {
        try {
            Individual individual = individualService.findByIcp(icp);
//            individual.setRfPassport(rfPassport);
            logger.info("Saving RF Passport");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            throw new PassportException("Invalid passport data");
        }
    }

    @PostMapping(value = "/phone")
    public ResponseEntity<DataInfoHandler> savePhoneNum(@RequestBody PhoneNumber phoneNumber){
        try{
            individualService.savePhoneNumber(phoneNumber);
            log.info("saving PhoneNumber with uuid " +
                    phoneNumber.getUuid()+" and with number " + phoneNumber.getValue()+phoneNumber);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (DataIntegrityViolationException e){
            throw new PhoneNumberWithSuchValueExists("PhoneNumber with such value Exists");
        }
    }

    @PostMapping(value = "/email")
    public ResponseEntity<DataInfoHandler> saveEmail(@RequestBody Email email){
        try{
            individualService.saveEmail(email);
            log.info("saving Email");
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (DataIntegrityViolationException e){
            throw new EmailWithSuchValueExists("Email with such value Exists");
        }
    }

}
