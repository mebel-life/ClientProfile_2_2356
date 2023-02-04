package org.client.masking.impl;

import org.client.dto.IndividualDto;
import org.client.masking.IndividualService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class IndividualServiceImpl implements IndividualService {

    private Map<String, IndividualDto> persist = new HashMap<>();
    @Override
    public void addClient(String icp, String name) {
        persist.put(icp, IndividualDto.builder().icp(icp).uuid(UUID.randomUUID().toString()).name(name).build());
    }

    @Override
    public IndividualDto getClient(String icp) {
        return persist.get(icp);
    }
}
