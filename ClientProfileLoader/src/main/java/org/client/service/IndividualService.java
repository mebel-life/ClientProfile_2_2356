package org.client.service;

import org.client.common.entity.Individual;
import org.client.common.entity.RFPassport;

public interface IndividualService {

    public Individual findByIcp(String icp);

    public void saveIndividual(Individual individual);

    public void saveRFPassport(RFPassport rfPassport, String icp);
}
