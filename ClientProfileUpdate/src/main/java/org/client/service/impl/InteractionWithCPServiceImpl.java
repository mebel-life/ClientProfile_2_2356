package org.client.service.impl;

import lombok.AllArgsConstructor;
import org.client.dto.IndividualUpdateDto;
import org.client.service.InteractionWithCPService;
import org.client.util.UpdateIndividualException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class InteractionWithCPServiceImpl implements InteractionWithCPService {

    private static final String CP_SERVICE = "http://localhost:8080/individual";
    private final RestTemplate restTemplate;

    public void findIndividual(String icp, IndividualUpdateDto userUpdate) throws UpdateIndividualException {

        ResponseEntity<IndividualUpdateDto> responseEntity = restTemplate.getForEntity(CP_SERVICE + "/get?icp=" + icp, IndividualUpdateDto.class);
        var userFromDB = responseEntity.getBody();
        if (userFromDB == null) {
            throw new UpdateIndividualException("There is no user with this ICP");
        }

        if (userFromDB.equals(userUpdate)) {
            throw new UpdateIndividualException("User already has valid fields");
        }
    }



}
