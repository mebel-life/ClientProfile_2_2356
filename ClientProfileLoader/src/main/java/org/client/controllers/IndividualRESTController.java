package org.client.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.client.entity.Individual;
import org.client.entity.RFPassport;
import org.client.service.IndividualService;
import org.client.util.DataInfoHandler;
import org.client.util.NoSuchIndividualException;
import org.client.util.IndividualWithSuchICPExists;
import org.client.util.PassportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for individual
 */

@RestController
@RequestMapping("/api")
public class IndividualRESTController {

    private IndividualService individualService;
    Logger logger = LoggerFactory.getLogger(IndividualRESTController.class);
    private JmsTemplate jmsTemplate;
    @Value("${ibm.mq.queueName}")
    private String queueName;
    @Autowired
    public void setIndividualService(IndividualService individualService) {
        this.individualService = individualService;
    }
    @Autowired
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
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
    public ResponseEntity<DataInfoHandler> saveIndividual(@RequestBody Individual individual) throws JsonProcessingException {
        try {
            individualService.saveIndividual(individual);
            logger.info("Saving individual");

            ObjectMapper mapper = new ObjectMapper();
            jmsTemplate.convertAndSend(queueName, mapper.writeValueAsString(individual));

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            throw new IndividualWithSuchICPExists("Individual with such icp exists");
        }
    }
    @PutMapping("/clients/{icp}")
    public ResponseEntity<DataInfoHandler> saveRFPassport(@RequestBody RFPassport rfPassport, @PathVariable("icp") String icp) {
        try {
            Individual individual = individualService.findByIcp(icp);
            individual.setRfPassport(rfPassport);
            logger.info("Saving RF Passport");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            throw new PassportException("Invalid passport data");
        }
    }


}
