package org.client.controller;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.client.dto.IndividualUpdateDto;
import org.client.InteractionWithCPLoader;
import org.client.InteractionWithCPService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/update/individual")
@AllArgsConstructor
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
}
