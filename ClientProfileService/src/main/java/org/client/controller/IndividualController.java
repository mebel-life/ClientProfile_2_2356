package org.client.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.client.common.util.AuthUtil;
import org.client.common.dto.IndividualDto;

import org.client.service.IndividualService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/individual")
@Tag(name = "Individual controller", description = "Методы для работы с пользователем")
public class IndividualController {

    private AuthUtil authUtil = new AuthUtil();


    private final IndividualService individualService;

    public IndividualController(IndividualService individualService) {
        this.individualService = individualService;
    }

    @GetMapping("/getAll")
    @Operation(summary = "находим всех пользователей")
    public ResponseEntity<List<IndividualDto>> getAll(HttpServletRequest request) {
        try {
            authUtil.checkAuth(request);
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(401).build();
        }
        return new ResponseEntity<>(individualService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getClientByIcp/{icp}")
    @Operation(summary = "Информация о клиенте по ICP")
    public ResponseEntity<IndividualDto> getByIcp(
                                                  @PathVariable(value="icp") String icp) {
//        try {
//            authUtil.checkAuth(request);
//        } catch (HttpClientErrorException e) {
//            return ResponseEntity.status(401).build();
//        }
        IndividualDto individualDto = individualService.getClient(icp);
        individualService.checkIsArchived(individualDto);
        return new ResponseEntity<>(individualDto, HttpStatus.OK);
    }

    @PostMapping("/create")
    @Operation(summary = "создaние нового клиента")
    public ResponseEntity<Void> createIndividual(@RequestBody IndividualDto dto) {
//        try {
//            authUtil.checkAuth(request);
//        } catch (HttpClientErrorException e) {
//            return ResponseEntity.status(401).build();
//        }
        individualService.updateClientIfArchived(dto);
//        individualService.addClient(dto.getIcp(),  dto.getContactsUuid(),
//                dto.getDocumentsUuid(), dto.getRfPassportUuid(), dto.getBirthDate(), dto.getCountryOfBirth(),
//                dto.getFullName(), dto.getGender(), dto.getName(), dto.getPatronymic(),
//                dto.getPlaceOfBirth(), dto.getSurname());
        individualService.addClient(dto);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/getClientByPhonenum/{value}")
    @Operation(summary = "Информация о клиенте по номеру телефона")
    public ResponseEntity<IndividualDto> getByPhonenumber(HttpServletRequest request,
                                                          @PathVariable(value="value") String value) {
        try {
            authUtil.checkAuth(request);
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(401).build();
        }
        return new ResponseEntity<>(individualService.getClientByPhoneNum(value), HttpStatus.OK);
    }

    @PutMapping("/edit")
    @Operation(summary = "редактирование информации о клиенте")
    public ResponseEntity<Void> editIndividual(@RequestBody IndividualDto dto, HttpServletRequest request) {
        try {
            authUtil.checkAuth(request);
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(401).build();
        }
        individualService.editClient(dto.getIcp(), dto.getBirthDate(), dto.getCountryOfBirth(),
                dto.getFullName(), dto.getGender(), dto.getName(), dto.getPatronymic(),
                dto.getPlaceOfBirth(), dto.getSurname());
        return ResponseEntity.status(200).build();
    }

    @PostMapping("/delete")  //post запрос с icp клиента в  теле
    @Operation(summary = "удаление клиента по icp клиента")
    public ResponseEntity<Void> deleteIndividual(@RequestBody IndividualDto dto, HttpServletRequest request) {
        try {
            authUtil.checkAuth(request);
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(401).build();
        }
        individualService.deleteIndivid(dto.getIcp());
        return ResponseEntity.status(200).build();
    }


}

