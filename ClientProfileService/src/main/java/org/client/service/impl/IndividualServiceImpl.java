package org.client.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.client.common.dto.IndividualDto;
import org.client.common.entity.*;
import org.client.repo.IndividualRepo;
import org.client.service.IndividualService;
import org.client.util.ArchivedClientException;
import org.client.util.ClientAlreadyExistsException;
import org.client.util.IndividualUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
public class IndividualServiceImpl implements IndividualService {

    @Autowired
    private IndividualUtils individualUtils;
    @Autowired
    private IndividualRepo individualRepo;

    public IndividualServiceImpl() {
    }

    @Transactional
    @Override // добавить клиента
    public void addClient(String icp, String contactsUuid, String documentsUuid, UUID rfPassportUuid,
                          Date birthDate, String countryOfBirth, String fullName, String gender,
                          String name, String patronymic, String placeOfBirth, String surname) {

        individualRepo.createUser(UUID.randomUUID().toString(), birthDate, countryOfBirth, fullName, gender, icp, name, patronymic,
                placeOfBirth, surname, contactsUuid, documentsUuid, rfPassportUuid);
    }

    @Transactional
    @Override
    // добавить клиента
    public void addClient(IndividualDto individualDto) {
        Individual individual = individualUtils.convertToEntity(individualDto);
        individualRepo.save(individual);
    }

    @Override //получить информацию о клиенте по icp
    public IndividualDto getClient(String icp) {
        Individual i = individualRepo.findAllFieldsByIcp(icp);

//        IndividualDto individualDto = IndividualDto.builder().uuid(i.getUuid()).icp(i.getIcp()).name(i.getName()).
//                surname(i.getSurname()).patronymic(i.getPatronymic()).fullName(i.getFullName()).gender(i.getGender()).
//                placeOfBirth(i.getPlaceOfBirth()).countryOfBirth(i.getCountryOfBirth()).birthDate(i.getBirthDate()).documentsUuid(i.getDocuments().getUuid()).
//                rfPassportUuid(i.getPassport().getUuid()).contactsUuid(i.getContacts().getUuid()).build();

        return individualUtils.convertToDto(i);
    }

    @Override //получить всех клиентов
    public List<IndividualDto> getAll() {
        List<Individual> individualList = individualRepo.findAll();
        List<IndividualDto> individualDtoList = new ArrayList<>();

        //для каждого элемента individualList создадим объект типа IndividualDto, и присвоим ему значения из элемента individualList.
        // Потом  - поместим этот объект в лист AddressDto
//        for(Individual i: individualList){
//            IndividualDto individualDto = IndividualDto.builder().uuid(i.getUuid()).icp(i.getIcp()).name(i.getName()).
//                    surname(i.getSurname()).patronymic(i.getPatronymic()).fullName(i.getFullName()).gender(i.getGender()).
//                    placeOfBirth(i.getPlaceOfBirth()).countryOfBirth(i.getCountryOfBirth()).birthDate(i.getBirthDate()).documentsUuid(i.getDocuments().getUuid()).
//                    rfPassportUuid(i.getPassport().getUuid()).contactsUuid(i.getContacts().getUuid()).build();
//            individualDtoList.add(individualDto);
//        }
        return individualDtoList;
    }

    @Override //найти клиента (icp, name, uuid) по номеру телефона
    public IndividualDto getClientByPhoneNum(String value) {
        Individual individual = individualRepo.findByPhNum(value);
        IndividualDto individualDto = IndividualDto.builder().icp(individual.getIcp()).name(individual.getName()).
                uuid(individual.getUuid()).build();
        return individualDto;
    }

    @Transactional
    @Override  // редактировать клиента.
    public void editClient(String icp, Date birthDate2, String countryOfBirth2, String fullName2, String gender2,
                           String name2, String patronymic2, String placeOfBirth2, String surname2) {
        Individual userFromDB = individualRepo.findAllFieldsByIcp(icp); //нашли пользователя в базе по icp

        ContactMedium cont = individualRepo.findContactByIndivIcp(icp);
        String contactUuuid = cont.getUuid(); // находим uuid  первичный ключ для ContactMedium для этого юзера

        RFPassport passp = individualRepo.findPassportUuidByIndividIcp(icp);
//        UUID passpUuid = passp.getUuid(); // находим uuid первичный ключ для паспорта для этого юзера

        Documents docum = individualRepo.findDocumentUuidByIndividIcp(icp);
        String documentuid = docum.getUuid(); // находим uuid первичный ключ для documents для этого юзера

        //переприсваиваем пользователю поля (значения из запроса в постмэне)
        userFromDB = Individual.builder().icp(icp).uuid(userFromDB.getUuid()).birthDate(birthDate2).
                countryOfBirth(countryOfBirth2).fullName(fullName2).gender(gender2).name(name2).
                patronymic(patronymic2).placeOfBirth(placeOfBirth2).surname(surname2).build();

        individualRepo.save(userFromDB); //сохраняем юзера (пока без uuid для контактов, документов и паспорта

        //пересохраняем uuid  таблиц "контакты , пасспорт, документы"  в таблице индивидуал через nativeQuery sql запрос
        // (средствами только spring data jpa у меня это сделать не получилось
//        individualRepo.rewriteContactDocPassp(contactUuuid, documentuid, passpUuid, userFromDB.getUuid());
    }

    @Override //удалить пользователя по icp
    public void deleteIndivid(String icp) {
        Individual ind = individualRepo.findIndividualByIcp(icp).orElse(new Individual());
        individualRepo.deleteById(ind.getUuid());
    }

    @Transactional
    public void updateClientIfArchived(IndividualDto individual) {
        log.info(String.format("converting dto to entity %s", individual));
        Individual entity = individualUtils.convertToEntity(individual);
        RFPassport activePassport = entity.getPassport().stream().
                filter(passport -> passport.getPassportStatus().equals("active")).collect(Collectors.toList()).get(0);
        Individual archivedClient = null;
        try {
            archivedClient = individualRepo.findAllFieldsByIcp(individual.getIcp());
            if (archivedClient.getIcp().equals(individual.getIcp())) {
                throw new ClientAlreadyExistsException("Client already exists");
            }
        } catch (NullPointerException e) {
            //ignore
        }
        try {
            archivedClient = individualRepo.findByPassport(activePassport.getSeries(), activePassport.getNumber());
            archivedClient.setArchived(true);
            archivedClient.setActualIcp(individual.getIcp());
            log.info(String.format("marking archived client %s", archivedClient));
            individualRepo.save(archivedClient);
        } catch (NullPointerException e) {
        }
    }



    public void checkIsArchived(IndividualDto individualDto) {
        log.info(String.format("checking if user archived %s", individualDto));
        if (individualDto.isArchived()) {
            throw new ArchivedClientException(String.format("Client with this ICP is archived, actual ICP is %s", individualDto.getActualIcp()));
        }
    }
}