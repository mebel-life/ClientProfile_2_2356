package org.client.service;

import org.client.entities.Individual;

public interface IndividualService {

    public Individual findByIcp(String icp);

    public void saveIndividual(Individual individual);
}
