package org.client.service;

import org.client.common.dto.IndividualDto;

public interface IndividualService {

    void addClient(String icp, String name);

    IndividualDto getClient(String icp);
    IndividualDto getClientUuid(String uuid);

}
