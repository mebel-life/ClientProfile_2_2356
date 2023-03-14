package org.client.service.impl;

import org.client.common.dto.IndividualDto;
import org.client.entity.Individual;
import org.client.repository.IndividualRepository;
import org.client.service.IndividualService;
import org.client.util.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
