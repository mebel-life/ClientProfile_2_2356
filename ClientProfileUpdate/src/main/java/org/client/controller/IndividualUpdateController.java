package org.client.controller;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.client.dto.EmailUpdateDto;
import org.client.dto.IndividualUpdateDto;
import org.client.dto.PhoneNumberUpdateDto;
import org.client.service.InteractionWithCPLoader;
import org.client.service.InteractionWithCPService;
import org.client.service.InteractionWithCPNotification;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/update/individual")
@AllArgsConstructor
@Slf4j
public class IndividualUpdateController {


    private final InteractionWithCPService interactionWithCPService;
    private final InteractionWithCPLoader interactionWithCPLoader;


    @PostMapping()
    public ResponseEntity<?> updateIndividual(@Parameter(description = "ICP уникальный ключ клиента") String icp,
                                              @RequestBody IndividualUpdateDto userUpdate) throws Exception {

        interactionWithCPService.findIndividual(icp, userUpdate);
        interactionWithCPLoader.updateIndividual(userUpdate);

        return ResponseEntity.ok("User updated");
    }

    @PostMapping("/phone")
    public void updateIndividualPhoneNumber(
            @RequestBody PhoneNumberUpdateDto phoneNumberUpdateDto) throws Exception {
        log.info("connected " + phoneNumberUpdateDto.getUuid());
        interactionWithCPService.findPhoneNumberByIcp(phoneNumberUpdateDto.getUuid(), phoneNumberUpdateDto);
        interactionWithCPLoader.updatePhoneNumber(phoneNumberUpdateDto);


    }

    @PostMapping("/email")
    public void updateIndividualEmail(
            @RequestBody EmailUpdateDto emailUpdateDto) throws Exception {
        log.info("connected " + emailUpdateDto);
        interactionWithCPService.findEmailByUuid(emailUpdateDto.getUuid(), emailUpdateDto);
        interactionWithCPLoader.updateEmail(emailUpdateDto);


    }
}
