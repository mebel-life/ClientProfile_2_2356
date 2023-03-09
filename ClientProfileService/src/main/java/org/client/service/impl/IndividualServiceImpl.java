package org.client.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.client.common.dto.IndividualDto;
import org.client.service.IndividualService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@Slf4j

public class IndividualServiceImpl implements IndividualService {

    private Map<String, IndividualDto> persist = new HashMap<>();
    @Override
    public void addClient(String icp, String name) {
       log.info("Client created with icp = " + icp + " name = "+name);
        persist.put(icp, IndividualDto.builder().icp(icp).uuid(UUID.randomUUID().toString()).name(name).build());
    }

    @Override
    public IndividualDto getClient(String icp) {
        log.info("Get client with icp = "+persist.get(icp));
        return persist.get(icp);
    }
}
