package org.client.controller;
import org.client.dto.IndividualDto;
import org.client.masking.MaskingService;
import org.client.service.IndividualService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
/*
1) Из МКС ProfileProfileFacade через RestTamplate делать запрос по пользователю(по icp) в МКС ProfileService
2)В ответ на запрос получать тело ответа и проанализировать,
если ответ пустой вывести Exception "Пользователь с icp: "....." не найдет
если ответ пришел вывести в формате ответа, но замаскировать ФИО\Телефон\Паспорт использую ранее написанный сервис

 */

@RestController
@RequestMapping("/masking")
public class IndividualDataController {

    private final IndividualService individualService;
    private final MaskingService maskingService;

    public IndividualDataController(IndividualService individualService, MaskingService maskingService) {
        this.individualService = individualService;
        this.maskingService = maskingService;
    }

    @GetMapping
    public ResponseEntity<?> getById(@RequestParam (required = true) String icp) {

        try {
            if (icp.isBlank()) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Пользователь с icp " + icp + " не найден");
            } else {
                return new ResponseEntity<>(individualService.getClient(icp), HttpStatus.OK);
            }
        }
        catch (ResponseStatusException e) {
            return new ResponseEntity<>(individualService.getClient(icp), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = {
            MediaType.APPLICATION_JSON_VALUE
    }, produces = {
            MediaType.APPLICATION_JSON_VALUE
    })
    @ResponseBody
    public ResponseEntity maskData(@RequestBody IndividualDto dto) {

        final var masked = MaskingService.maskName(dto.getName(), dto.getSurname(), dto.getPatronymic());

        return ResponseEntity.ok().body(masked);

    }

}


