package org.client.service.impl;

import org.client.dto.IndividualDto;
import org.client.model.Address;
import org.client.model.Individual;
import org.client.model.Wallet;
import org.client.repository.AddressRepository;
import org.client.repository.IndividualRepository;
import org.client.repository.WalletRepository;
import org.client.service.IndividualService;
import org.client.utils.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class IndividualServiceImpl implements IndividualService {

    private final IndividualRepository individualRepository;
    private final MappingUtils mappingUtils;

    @Autowired
    public IndividualServiceImpl(IndividualRepository individualRepository, MappingUtils mappingUtils) {
        this.individualRepository = individualRepository;
        this.mappingUtils = mappingUtils;
    }

    @Override
    @Transactional
    public void addClient(String icp, String name) {
        Individual individual = new Individual();
        individual.setIcp(icp);
        individual.setUuid(UUID.randomUUID().toString());
        individual.setName(name);
        individualRepository.save(individual);
    }

    @Override
    public IndividualDto getClient(String icp) {
        return mappingUtils.mapToIndividualDto(individualRepository.findByIcp(icp));
    }
}
