package org.client.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.client.common.dto.IndividualDto;
import org.client.service.ContactService;
import org.client.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/contacts")
@AllArgsConstructor

@Slf4j
public class ContactUpdateController {
    private final ContactService contactService;
    private final EmailService emailService;
    @GetMapping("/get/{uuid}")
    @Operation(summary = "Информация о номере по Uuid")
    public ResponseEntity<?> getByUuid(@Parameter(description = "ICP уникальный номера телефона") String Uuid, @PathVariable("uuid") String uuid) {
        log.info("Profile Service connected "+uuid);

        return new ResponseEntity<>(contactService.getByValueUuid(uuid), HttpStatus.OK);

    }
    @GetMapping(value = "/email/get/{uuid}")
    @Operation(summary = "Информация об электронной почте по Uuid")
    public ResponseEntity<?> getEmailByUuid(@Parameter(description = "ICP уникальный ключ клиента") String Uuid,
                                            @PathVariable("uuid") String uuid ) {
        log.info("Profile Service connected "+uuid);

        return new ResponseEntity<>(emailService.getByValueUuid(uuid), HttpStatus.OK);

    }
}

