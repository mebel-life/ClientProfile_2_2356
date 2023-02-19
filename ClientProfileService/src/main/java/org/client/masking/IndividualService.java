package org.client.masking;

import org.client.dto.IndividualDto;

public interface IndividualService {

    void addClient(String icp, String name);

    IndividualDto getClient(String icp);

}
