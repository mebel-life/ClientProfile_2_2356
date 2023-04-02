package org.client.service;

import org.client.common.dto.PhoneNumberDto;
import org.client.common.entity.Contacts.Email;
import org.client.common.entity.Contacts.PhoneNumber;
import org.client.common.entity.Individual;
import org.client.common.entity.RFPassport;
import org.client.common.entity.Individual;
import org.client.common.entity.RFPassport;

public interface IndividualService {

    public Individual findByIcp(String icp);

    public void saveIndividual(Individual individual);

    public void saveRFPassport(RFPassport rfPassport, String icp);
    public void savePhoneNumber(PhoneNumber phoneNumber);
    public void saveEmail(Email email);
}
