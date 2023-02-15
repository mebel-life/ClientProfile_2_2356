package org.client.controller;

import io.swagger.v3.oas.annotations.Parameter;
import org.client.dto.AddressDto;
import org.client.service.AddressService;
import org.client.util.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

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

        if (addressDto == null) throw new NotFoundAddressException("Not found address with icp = " + icp + " in database");
        return new ResponseEntity<>(addressDto, HttpStatus.OK);
    }

    @PostMapping
    public void createAddress(@Parameter String icp, @RequestBody AddressDto dto) {
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

            if(addressDto != null) throw new AddressWithSuchICPExists("Address with such icp = " + icp + " exists");
        }

        addressService.addAddressForClient(icp, dto.getAddressName());
    }
}
