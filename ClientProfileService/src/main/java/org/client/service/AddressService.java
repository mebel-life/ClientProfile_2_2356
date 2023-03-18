package org.client.service;

import org.client.common.dto.AddressDto;
import org.client.common.dto.IndividualDto;

import java.util.List;

public interface AddressService {

    void addAddressForClient(String individualIcp, String addressName, String notFormAddrName, String country, String zipCode);

    List<IndividualDto> getClientByAddress(String zipCode);

    List<AddressDto> getAddressByClienticp(String icp);

}
