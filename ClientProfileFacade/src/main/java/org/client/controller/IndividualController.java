package org.client.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.client.common.dto.IndividualDto;
import org.client.service.MaskingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;


@RestController
@RequestMapping("api")
@Tag(name = "Individual controller", description = "Методы для работы фронта с пользователем")
public class IndividualController {
    HttpHeaders headers = new HttpHeaders();
    {
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    }

    private RestTemplate restTemplate;

    private MaskingService maskingService;

    @Autowired
    public IndividualController(RestTemplate restTemplate, MaskingService maskingService) {
        this.restTemplate = restTemplate;
        this.maskingService = maskingService;
    }


    @GetMapping("/individual")
    @Operation(summary = "Полная маскированная информация о клиенте по ICP")
    public ResponseEntity<IndividualDto> getClientById(@Parameter(description = "ICP уникальный ключ клиента") String icp) {
        HttpEntity<Void> http = new HttpEntity<>(this.headers);
        ResponseEntity<IndividualDto> responseEntity = restTemplate.exchange("http://localhost:8080/individual/get?icp={icp}", HttpMethod.GET, http, IndividualDto.class, icp);
        IndividualDto individualDto = responseEntity.getBody();
        System.out.println(individualDto);
        return new ResponseEntity<>(maskingService.maskIndividual(individualDto), HttpStatus.OK);
    }

}
