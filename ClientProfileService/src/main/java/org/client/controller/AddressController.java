package org.client.controller;

import io.swagger.v3.oas.annotations.Parameter;

import org.client.common.dto.AddressDto;
import org.client.service.AddressService;
import org.client.service.producer.Producer;
import org.client.util.exceptions.AddressWithSuchICPExists;
import org.client.util.exceptions.EmptyParameterException;
import org.client.util.exceptions.NotFoundAddressException;
import org.client.util.exceptions.NotFoundClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/address")
public class AddressController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddressController.class);

    private final AddressService addressService;

    @Autowired
    private Producer producer;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<?> getById(@RequestParam String icp) {
        AddressDto addressDto = null;

        try {
            addressDto = addressService.getAddressClient(icp);
        } catch (NotFoundClientException e) {
            throw new NotFoundClientException("Not found client with icp = " + icp + " in database to get an address");
        }

        if (addressDto == null)
            throw new NotFoundAddressException("Not found address with icp = " + icp + " in database");
        return new ResponseEntity<>(addressDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createAddress(@Parameter(required = true) String icp, @RequestBody AddressDto dto) {

        if (dto.getAddressName() == null) throw new EmptyParameterException("Parameter 'Address name' not set");

        if (icp == null) {
            throw new EmptyParameterException("Parameter 'icp' not set");
        } else {
            AddressDto addressDto = null;

            try {
                addressDto = addressService.getAddressClient(icp);
            } catch (NotFoundClientException e) {
                throw new NotFoundClientException("Not found client with icp = " + icp +
                        " in database to add an address");
            }

            if (addressDto != null) throw new AddressWithSuchICPExists("Address with such icp = " + icp + " exists");
        }

        addressService.addAddressForClient(icp, dto.getAddressName());

        try {
            addressService.addAddressForClient(icp, dto.getAddressName());
        } catch (NoSuchElementException e) {
            LOGGER.warn("Address name not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            producer.sendMessage(dto);
        } catch (NoSuchElementException e) {
            LOGGER.warn("The message was not sent");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
