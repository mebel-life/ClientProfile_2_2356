package org.client.service;

import org.client.dto.EmailUpdateDto;
import org.client.dto.IndividualUpdateDto;

import org.client.dto.PhoneNumberUpdateDto;
import org.client.util.UpdateIndividualException;

public interface InteractionWithCPService  {
    void findIndividual(String icp, IndividualUpdateDto userUpdate) throws Exception;
    void findPhoneNumberByIcp(String icp, PhoneNumberUpdateDto phoneNumberUpdateDto) throws UpdateIndividualException;
    void findEmailByUuid(String uuid, EmailUpdateDto emailUpdateDto) throws UpdateIndividualException;
}
