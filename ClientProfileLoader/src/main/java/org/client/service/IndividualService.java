package org.client.service;

import org.client.entity.Individual;
import org.client.entity.RFPassport;

public interface IndividualService {

    public Individual findByIcp(String icp);

    public void saveIndividual(Individual individual);

    public void saveRFPassport(RFPassport rfPassport, String icp);
}
