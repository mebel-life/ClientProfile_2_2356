package org.client.service.impl;

import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.client.common.dto.AddressDto;
import org.client.common.dto.IndividualDto;
import org.client.service.AddressService;
import org.client.service.IndividualService;

import java.util.Collections;
@Slf4j
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final IndividualService individualService;

    @Override
    public void addAddressForClient(String icp, String address) {
        log.info("Add address for client with icp = "+icp," and Address = "+address);
        IndividualDto dto = individualService.getClient(icp);
        AddressDto addressDto = AddressDto.builder().addressName(address).build();
        dto.setAddress(Collections.singleton(addressDto));
    }

    @Override
    public AddressDto getAddressClient(String icp) {
        log.info("get Address for client with icp = " +icp);
        return (AddressDto) Collections.singleton(individualService.getClient(icp).getAddress());
    }
}
