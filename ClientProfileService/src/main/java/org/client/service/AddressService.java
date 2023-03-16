package org.client.service;

import org.client.dto.AddressDto;
import org.client.dto.IndividualDto;

public interface AddressService {

    void addAddressForClient(String icp, String address);

    AddressDto getAddressClient(String icp);

}
