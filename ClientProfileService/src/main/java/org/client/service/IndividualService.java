package org.client.service;

import org.client.common.dto.IndividualDto;
import org.client.entity.Individual;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface IndividualService {

    void addClient(String icp, String contactsuuid, String documentsUuid, UUID rfpasportsUuid,
                   Date birthdate, String countryOfbirth, String fullName, String gender,
                   String name, String patronymic, String placeOfBirth, String surname);

    IndividualDto getClient(String icp);

    IndividualDto getClientByPhoneNum(String value);

    List<IndividualDto> getAll();

    void editClient(String icp, Date birthdate, String countryOfbirth, String fullName, String gender,
                   String name, String patronymic, String placeOfBirth, String surname);

    void deleteIndivid(String icp);


}
