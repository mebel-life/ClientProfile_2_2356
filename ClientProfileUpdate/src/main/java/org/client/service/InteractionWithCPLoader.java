package org.client.service;

import org.client.dto.IndividualUpdateDto;
import org.client.util.ClientProfileLoaderException;

public interface InteractionWithCPLoader {
    void updateIndividual(IndividualUpdateDto userUpdate) throws ClientProfileLoaderException;
}
