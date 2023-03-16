package org.client.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.client.common.util.AuthUtil;
import org.client.dto.IndividualDto;
import org.client.entity.Individual;

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
    private final IndividualService individualService;

    public IndividualController(IndividualService individualService) {
        this.individualService = individualService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Individual>> getAll(HttpServletRequest request) {
        try {
            authUtil.checkAuth(request);
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(401).build();
        }
        List<Individual> list = individualService.getAll();
        System.out.println(list.get(0).getIcp() + " user1 (icp) @@@");
        System.out.println(list.get(1).getIcp() + " user2 (icp) @@@");
        System.out.println(list.get(2).getIcp() + " user3 (icp) @@@");
        return ResponseEntity.ok(list); //не понимаю, как передать лист пользователей в json, просто вывел их icp в консоль
    }

    @GetMapping("/get/{icp}")
    @Operation(summary = "Информация о клиенте по ICP")
    public ResponseEntity<IndividualDto> getByIcp(@PathVariable String icp,
                                                  HttpServletRequest request) {
        try {
            authUtil.checkAuth(request);
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(401).build();
        }
        return new ResponseEntity<>(individualService.getClient(icp), HttpStatus.OK);
    }

    @PostMapping("/create")
    public void createIndividual(@RequestBody IndividualDto dto) {
        individualService.addClient(dto.getIcp(),  dto.getContactsUuid(),
                dto.getDocumentsUuid(), dto.getRfPassportUuid(), dto.getBirthDate(), dto.getCountryOfBirth(),
                dto.getFullName(), dto.getGender(), dto.getName(), dto.getPatronymic(),
                dto.getPlaceOfBirth(), dto.getSurname());
    }

    @GetMapping("/get2/{icp}")
    @Operation(summary = "Информация о клиенте по номеру телефона")
    public ResponseEntity<IndividualDto> getByPhonenumber(@PathVariable String value,
                                                  HttpServletRequest request) {
        try {
            authUtil.checkAuth(request);
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(401).build();
        }
        return new ResponseEntity<>(individualService.getClientByPhoneNum(value), HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<Void> editIndividual(@RequestBody IndividualDto dto, HttpServletRequest request) {
        try {
            authUtil.checkAuth(request);
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(401).build();
        }
        individualService.editClient(dto.getIcp(), dto.getBirthDate(), dto.getCountryOfBirth(),
                dto.getFullName(), dto.getGender(), dto.getName(), dto.getPatronymic(),
                dto.getPlaceOfBirth(), dto.getSurname());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete/{icp}")  // post запрос с пустым телом
    public ResponseEntity<Void> editIndividual(@PathVariable(value="icp") String icp, HttpServletRequest request) {
        try {
            authUtil.checkAuth(request);
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(401).build();
        }
        individualService.deleteIndivid(icp);
        return ResponseEntity.ok().build();
    }




}
