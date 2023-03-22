package org.client.controller;

import io.swagger.v3.oas.annotations.Parameter;
import org.client.common.dto.AddressDto;
import org.client.service.AddressService;
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
        return new ResponseEntity<>(addressService.getAddressClient(icp), HttpStatus.OK);
    }

    @PostMapping("/create")
    public void createAddress(@Parameter String icp, @RequestBody AddressDto dto) {
        addressService.addAddressForClient(icp, dto.getAddressName());
    }


}
