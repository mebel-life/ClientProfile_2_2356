package org.client.service.impl;


import lombok.AllArgsConstructor;
import org.client.dto.IndividualUpdateDto;
import org.client.service.InteractionWithCPLoader;
import org.client.util.ClientProfileLoaderException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class InteractionWithCPLoaderImpl implements InteractionWithCPLoader {
    private static final String CP_LOADER = "http://localhost:9092/api/clients";
    private final RestTemplate restTemplate;

    public void updateIndividual(IndividualUpdateDto userUpdate) throws ClientProfileLoaderException {

        ResponseEntity<?> responseEntity = restTemplate.postForEntity(CP_LOADER, new HttpEntity<>(userUpdate), IndividualUpdateDto.class);

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new ClientProfileLoaderException("Service Client Profile Loader is unavailable");
        }
    }
}
