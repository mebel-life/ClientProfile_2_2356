package org.client.service;

import org.client.dto.IndividualUpdateDto;

public interface InteractionWithCPService  {
    void findIndividual(String icp, IndividualUpdateDto userUpdate) throws Exception;
}
