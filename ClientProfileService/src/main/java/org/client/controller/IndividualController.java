package org.client.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.client.common.util.AuthUtil;
import org.client.common.dto.IndividualDto;
import org.client.common.entity.Individual;

import org.client.service.IndividualService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/individual")
@Tag(name = "Individual controller", description = "Методы для работы с пользователем")
public class IndividualController {
    private AuthUtil authUtil = new AuthUtil();
    HttpServletRequest request;

    private final IndividualService individualService;

    public IndividualController(IndividualService individualService) {
        this.individualService = individualService;
    }

    @GetMapping("/getAll")
    @Operation(summary = "находим всех пользователей")
    public ResponseEntity<List<IndividualDto>> getAll() {
//        try {
//            authUtil.checkAuth(request);
//        } catch (HttpClientErrorException e) {
//            return ResponseEntity.status(401).build();
//        }
        return new ResponseEntity<>(individualService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getClientByIcp/{icp}")
    @Operation(summary = "Информация о клиенте по ICP")
    public ResponseEntity<IndividualDto> getByIcp(@Parameter(description = "ICP уникальный ключ клиента") String ICP,
                                                  @PathVariable(value="icp") String icp) {
//        try {
//            authUtil.checkAuth(request);
//        } catch (HttpClientErrorException e) {
//            return ResponseEntity.status(401).build();
//        }
        return new ResponseEntity<>(individualService.getClient(icp), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createIndividual(@RequestBody IndividualDto dto) {
//        try {
//            authUtil.checkAuth(request);
//        } catch (HttpClientErrorException e) {
//            return ResponseEntity.status(401).build();
//        }
        individualService.addClient(dto.getIcp(),  dto.getContactsUuid(),
                dto.getDocumentsUuid(), dto.getRfPassportUuid(), dto.getBirthDate(), dto.getCountryOfBirth(),
                dto.getFullName(), dto.getGender(), dto.getName(), dto.getPatronymic(),
                dto.getPlaceOfBirth(), dto.getSurname());
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/getClientByPhonenum/{value}")
    @Operation(summary = "Информация о клиенте по номеру телефона")
    public ResponseEntity<IndividualDto> getByPhonenumber(@Parameter(description = "телефон клиента") String Value,
                                                          @PathVariable(value="value") String value) {
//        try {
//            authUtil.checkAuth(request);
//        } catch (HttpClientErrorException e) {
//            return ResponseEntity.status(401).build();
//        }
        return new ResponseEntity<>(individualService.getClientByPhoneNum(value), HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<Void> editIndividual(@RequestBody IndividualDto dto) {
//        try {
//            authUtil.checkAuth(request);
//        } catch (HttpClientErrorException e) {
//            return ResponseEntity.status(401).build();
//        }
        individualService.editClient(dto.getIcp(), dto.getBirthDate(), dto.getCountryOfBirth(),
                dto.getFullName(), dto.getGender(), dto.getName(), dto.getPatronymic(),
                dto.getPlaceOfBirth(), dto.getSurname());
        return ResponseEntity.status(200).build();
    }

    @PostMapping("/delete/{icp}")  //post запрос с пустым телом
    public ResponseEntity<Void> deleteIndividual(@PathVariable(value="icp") String icp) {
//        try {
//            authUtil.checkAuth(request);
//        } catch (HttpClientErrorException e) {
//            return ResponseEntity.status(401).build();
//        }
        individualService.deleteIndivid(icp);
        return ResponseEntity.status(200).build();
    }


}

