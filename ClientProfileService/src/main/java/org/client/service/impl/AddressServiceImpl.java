package org.client.service.impl;

import lombok.AllArgsConstructor;

import org.client.common.dto.AddressDto;
import org.client.common.dto.IndividualDto;
import org.client.service.AddressService;
import org.client.service.IndividualService;

import java.util.Collections;
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final IndividualService individualService;

    @Override
    public void addAddressForClient(String icp, String address) {
        IndividualDto dto = individualService.getClient(icp);
        AddressDto addressDto = AddressDto.builder().addressName(address).build();
        dto.setAddress(Collections.singleton(addressDto));
    }

    @Override
    public AddressDto getAddressClient(String icp) {
        return (AddressDto) Collections.singleton(individualService.getClient(icp).getAddress());
    }
}
