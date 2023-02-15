package org.client.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.client.dto.IndividualDto;
import org.client.service.IndividualService;
import org.client.util.exceptions.ClientWithSuchICPExists;
import org.client.util.exceptions.EmptyParameterException;
import org.client.util.exceptions.NotFoundClientException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/individual")
@Tag(name = "Individual controller", description = "Методы для работы с пользователем")
public class IndividualController {

    private final IndividualService individualService;

    public IndividualController(IndividualService individualService) {
        this.individualService = individualService;
    }

    @GetMapping("/get")
    @Operation(summary = "Информация о клиенте по ICP")
    public ResponseEntity<IndividualDto> getById(@Parameter(required = true,
            description = "ICP уникальный ключ клиента") String icp) {
        if (icp == null) throw new EmptyParameterException("Parameter icp not set");
        IndividualDto individualDto = individualService.getClient(icp);
        if (individualDto == null) throw new NotFoundClientException("Not found client with icp = " + icp + " in database");
        return new ResponseEntity<>(individualDto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public void createIndividual(@RequestBody IndividualDto dto) {
        if (dto.getIcp() == null) {
            throw new EmptyParameterException("Parameter 'icp' not set");
        } else {
            if(individualService.getClient(dto.getIcp()) != null) {
                throw new ClientWithSuchICPExists("Client with such icp = " + dto.getIcp() + " exists");
            }
        }

        if (dto.getName() == null) throw new EmptyParameterException("Parameter 'name' not set");
        individualService.addClient(dto.getIcp(), dto.getName());
    }
}
