package org.client.service.impl;

import org.client.entities.Individual;
import org.client.repositories.IndividualRepository;
import org.client.service.IndividualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class IndividualServiceImpl implements IndividualService {
    private IndividualRepository individualRepository;

    @Autowired
    public void setIndividualRepository(IndividualRepository individualRepository) {
        this.individualRepository = individualRepository;
    }

    @Override
    public Individual findByIcp(String icp) {
        return individualRepository.findByIcp(icp);
    }

    @Override
    @Transactional
    public void saveIndividual(Individual individual) {
        individualRepository.save(individual);
    }
}
