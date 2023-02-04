package org.client.masking.impl;

import lombok.AllArgsConstructor;
import org.client.dto.AddressDto;
import org.client.dto.IndividualDto;
import org.client.masking.AddressService;
import org.client.masking.IndividualService;

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
