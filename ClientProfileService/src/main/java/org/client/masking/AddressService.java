package org.client.masking;

import org.client.dto.AddressDto;

public interface AddressService {

    void addAddressForClient(String icp, String address);

    AddressDto getAddressClient(String icp);

}
