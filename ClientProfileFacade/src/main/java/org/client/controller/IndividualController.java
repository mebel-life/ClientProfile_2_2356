package org.client.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.client.common.dto.*;

import org.client.common.util.AuthUtil;
import org.client.dto.shortIndividual.IndividualClientDto;
import org.client.dto.shortIndividual.IndividualDocStatusDto;
import org.client.dto.shortIndividual.IndividualShortDto;
import org.client.service.MaskingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


@RestController
@CrossOrigin(allowedHeaders = {"username", "password"})
@RequestMapping("api")
@Tag(name = "Individual controller", description = "Методы для работы фронта с пользователем")
public class IndividualController {

    class UriVariables {
        private static final String INDIVIDUAL_PATH = "/individual/get?icp={icp}";
        private static final String CLIENT_PROFILE_SERVICE_PATH = "http://localhost:8080";
    }

    private HttpHeaders headers = new HttpHeaders();

    {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
    }

    //Util for restrict access to methods
    private AuthUtil authUtil = new AuthUtil();
    private HttpEntity<Void> http = new HttpEntity<>(headers);
    private RestTemplate restTemplate;
    private MaskingService maskingService;
    private Logger logger = Logger.getLogger("IndividualControllerFacade");

    @Autowired
    public IndividualController(RestTemplate restTemplate, MaskingService maskingService) {
        this.restTemplate = restTemplate;
        this.maskingService = maskingService;
    }


    @GetMapping("/individual")
    @Operation(summary = "Полная маскированная информация о клиенте по ICP")
    public ResponseEntity<IndividualDto> getClientById(@Parameter(description = "ICP уникальный ключ клиента") String icp, HttpServletRequest request) {
        try {
            authUtil.checkAuth(request);
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(401).build();
        }
        IndividualDto individualDto = new IndividualDto();
        try {
            ResponseEntity<IndividualDto> responseEntity =
                    restTemplate.exchange(UriVariables.CLIENT_PROFILE_SERVICE_PATH + UriVariables.INDIVIDUAL_PATH, HttpMethod.GET, http, IndividualDto.class, icp);
            individualDto = responseEntity.getBody();
        } catch (RuntimeException e) {
            logger.log(Level.WARNING, "Client not found");
        }
        logger.log(Level.INFO, "Client info sent");
        return new ResponseEntity<>(maskingService.maskIndividual(individualDto), HttpStatus.OK);
    }

    @GetMapping("/individualShort")
    @Operation(summary = "Частичная информация о клиенте по ICP(ФИО, icp, uuid")
    public ResponseEntity<IndividualShortDto> getClientShortById(@Parameter(description = "ICP уникальный ключ клиента") String icp, HttpServletRequest request) {
        try {
            authUtil.checkAuth(request);
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(401).build();
        }
        IndividualShortDto individualShortDto = new IndividualShortDto();
        try {
            ResponseEntity<IndividualShortDto> responseEntity =
                    restTemplate.exchange(UriVariables.CLIENT_PROFILE_SERVICE_PATH + UriVariables.INDIVIDUAL_PATH, HttpMethod.GET, http, IndividualShortDto.class, icp);
            individualShortDto = responseEntity.getBody();
        } catch (RuntimeException e) {
            logger.log(Level.WARNING, "Client not found");
        }
        logger.log(Level.INFO, "Client info sent");
        return new ResponseEntity<>(maskingService.maskIndividual(individualShortDto), HttpStatus.OK);
    }

    @GetMapping("/individualDocStatus")
    @Operation(summary = "Частичная информация о клиенте с документами по ICP(ФИО, icp, uuid, RFPassport")
    public ResponseEntity<IndividualDocStatusDto> getClientDocStatusById(@Parameter(description = "ICP уникальный ключ клиента") String icp, HttpServletRequest request) {
        try {
            authUtil.checkAuth(request);
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(401).build();
        }

        IndividualDocStatusDto individualDocStatusDto = new IndividualDocStatusDto();
        try {
            ResponseEntity<IndividualDocStatusDto> responseEntity =
                    restTemplate.exchange(UriVariables.CLIENT_PROFILE_SERVICE_PATH + UriVariables.INDIVIDUAL_PATH, HttpMethod.GET, http, IndividualDocStatusDto.class, icp);
            individualDocStatusDto = responseEntity.getBody();
        } catch (RuntimeException e) {
            logger.log(Level.WARNING, "Client not found");
        }
        logger.log(Level.INFO, "Client info sent");
        return new ResponseEntity<>(maskingService.maskIndividual(individualDocStatusDto), HttpStatus.OK);
    }

    @GetMapping("/individualClient")
    @Operation(summary = "Частичная информация о клиенте по ICP(ФИО, icp, uuid, Avatar, Adress, Documents")
    public ResponseEntity<IndividualClientDto> getClientInfoById(@Parameter(description = "ICP уникальный ключ клиента") String icp, HttpServletRequest request) {
        try {
            authUtil.checkAuth(request);
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(401).build();
        }
        IndividualClientDto individualClientDto = new IndividualClientDto();
        try {
            ResponseEntity<IndividualClientDto> responseEntity =
                    restTemplate.exchange(UriVariables.CLIENT_PROFILE_SERVICE_PATH + UriVariables.INDIVIDUAL_PATH, HttpMethod.GET, http, IndividualClientDto.class, icp);
            individualClientDto = responseEntity.getBody();
        } catch (RuntimeException e) {
            logger.log(Level.WARNING, "Client not found");
        }
        logger.log(Level.INFO, "Client info sent");
        return new ResponseEntity<>(maskingService.maskIndividual(individualClientDto), HttpStatus.OK);
    }
}
