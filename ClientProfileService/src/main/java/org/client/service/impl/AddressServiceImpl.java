package org.client.service.impl;

import lombok.AllArgsConstructor;
import org.client.controller.AddressController;
import org.client.dto.AddressDto;
import org.client.dto.IndividualDto;
import org.client.entity.Address;
import org.client.entity.Individual;
import org.client.repository.IndividualRepository;
import org.client.service.AddressService;
import org.client.service.IndividualService;
import org.client.util.MappingUtils;
import org.client.util.exceptions.NotFoundClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.UUID;

@AllArgsConstructor
@Transactional(readOnly = true)
public class AddressServiceImpl implements AddressService {

    private final IndividualRepository individualRepository;
    private final MappingUtils mappingUtils;

    @Override
    @Transactional
    public void addAddressForClient(String icp, String address) {
        Individual individual = individualRepository.findByIcp(icp);

        Address addressEntity = new Address();
        addressEntity.setIcp(icp);
        addressEntity.setUuid(UUID.randomUUID().toString());
        addressEntity.setAddressName(address);

        individual.setAddress(addressEntity);
        individualRepository.save(individual);
    }

    @Override
    public AddressDto getAddressClient(String icp) {
        Individual individual = individualRepository.findByIcp(icp);
        if (individual == null) {
            throw new NotFoundClientException("Not found client with icp = " + icp + " in database");
        }
        return mappingUtils.mapToAddressDto(individual.getAddress());
    }
}

