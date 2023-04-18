package org.client.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.client.common.entity.Contacts.Email;
import org.client.common.entity.Contacts.PhoneNumber;
import org.client.common.entity.Individual;
import org.client.common.entity.RFPassport;
import org.client.repositories.EmailRepository;
import org.client.repositories.IndividualRepository;
import org.client.repositories.PhoneNumberRepository;
import org.client.service.IndividualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j

@Service
@Transactional(readOnly = true)
public class IndividualServiceImpl implements IndividualService {
    private IndividualRepository individualRepository;
    private PhoneNumberRepository phoneNumberRepository;
    private EmailRepository emailRepository;


    @Autowired
    public void setPhoneNumberRepository(PhoneNumberRepository phoneNumberRepository) {
        this.phoneNumberRepository = phoneNumberRepository;
    }

    @Autowired
    public void setEmailRepository(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

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

    @Override
    public void saveRFPassport(RFPassport rfPassport, String icp) {
        Individual individual = findByIcp(icp);
//        individual.setRfPassport(rfPassport);
    }

    @Override
    @Transactional
    public void savePhoneNumber(PhoneNumber phoneNumber) {


        phoneNumberRepository.save(phoneNumber);

    }

    @Override
    @Transactional
    public void saveEmail(Email email) {

        emailRepository.save(email);

    }
}
