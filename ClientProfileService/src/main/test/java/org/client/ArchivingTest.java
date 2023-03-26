package org.client;

import static org.junit.Assert.*;

import org.client.common.dto.IndividualDto;
import org.client.common.dto.RFPassportDto;
import org.client.controller.IndividualController;

import org.client.util.ArchivedClientException;
import org.client.util.ClientAlreadyExistsException;
import org.client.util.IndividualUtils;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("/application-test.properties")
public class ArchivingTest {
    @Autowired
    private IndividualController controller;
    @Autowired
    private IndividualUtils utils;
    @Test(expected = ArchivedClientException.class)
    public void successTest() throws ArchivedClientException {

        RFPassportDto passportDto1 = RFPassportDto.builder().number("11111").series("yyyy").passportStatus("active").build();
        IndividualDto dto1 = IndividualDto.builder().name("Archive").icp("112").passport(List.of(passportDto1)).build();
        passportDto1.setIndividual(dto1);
        controller.createIndividual(dto1);

        RFPassportDto passportDto2 = RFPassportDto.builder().number("11111").series("yyyy").passportStatus("active").build();
        IndividualDto dto2 = IndividualDto.builder().name("New").icp("332").passport(List.of(passportDto2)).build();
        passportDto1.setIndividual(dto2);
        controller.createIndividual(dto2);
        controller.getByIcp("112");

    }

    @Test(expected = ClientAlreadyExistsException.class)
    public void alreadyExistTest()  {
        RFPassportDto passportDto1 = RFPassportDto.builder().number("11111").series("yyyy").passportStatus("active").build();
        IndividualDto dto1 = IndividualDto.builder().name("Archive").icp("332").passport(List.of(passportDto1)).build();
        passportDto1.setIndividual(dto1);
        controller.createIndividual(dto1);

        RFPassportDto passportDto2 = RFPassportDto.builder().number("121111").series("yyyy").passportStatus("active").build();
        IndividualDto dto2 = IndividualDto.builder().name("New").icp("332").passport(List.of(passportDto2)).build();
        passportDto1.setIndividual(dto2);
        controller.createIndividual(dto2);
        controller.getByIcp("332");
    }

    }
