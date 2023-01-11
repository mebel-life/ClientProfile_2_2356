package org.client.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.client.dto.IndividualDto;
import org.client.service.IndividualService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    public ResponseEntity<IndividualDto> getById(@Parameter(description = "ICP уникальный ключ клиента") String icp) {
        return new ResponseEntity<>(individualService.getClient(icp), HttpStatus.OK);
    }

    @PostMapping("/create")
    public void createIndividual(@RequestBody IndividualDto dto) {
        individualService.addClient(dto.getIcp(), dto.getName());
    }
}
