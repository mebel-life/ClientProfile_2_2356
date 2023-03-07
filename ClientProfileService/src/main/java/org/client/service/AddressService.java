package org.client.service;


import org.client.common.dto.AddressDto;

public interface AddressService {

    void addAddressForClient(String icp, String address);

    AddressDto getAddressClient(String icp);

}
