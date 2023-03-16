package org.client.service.impl;

import lombok.AllArgsConstructor;
import org.client.dto.IndividualDto;
import org.client.entity.ContactMedium;
import org.client.entity.Documents;
import org.client.entity.Individual;
import org.client.entity.RFPassport;
import org.client.repo.IndividualRepo;
import org.client.repo.IndividualRepo;
import org.client.service.IndividualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.*;
import java.util.UUID;

@Component
@AllArgsConstructor
public class IndividualServiceImpl implements IndividualService {

    @Autowired
    IndividualRepo individualRepo;

    public IndividualServiceImpl() {}

    @Transactional
    @Override // добавить клиента
    public void addClient(String icp, String contactsUuid, String documentsUuid, UUID rfPassportUuid,
                          Date birthDate, String countryOfBirth, String fullName, String gender,
                          String name, String patronymic, String placeOfBirth, String surname) {

        individualRepo.createUser(UUID.randomUUID().toString(), birthDate, countryOfBirth, fullName, gender, icp, name, patronymic,
                placeOfBirth, surname, contactsUuid, documentsUuid, rfPassportUuid);
    }

    @Override //получить информацию о клиенте по icp
    public IndividualDto getClient(String icp) {
        Individual individual = individualRepo.findAllFieldsByIcp(icp);

        IndividualDto individualDto = new IndividualDto();
        individualDto.setUuid(individual.getUuid());
        individualDto.setBirthDate(individual.getBirthDate());
        individualDto.setCountryOfBirth(individual.getCountryOfBirth());
        individualDto.setFullName(individual.getFullName());
        individualDto.setGender(individual.getGender());
        individualDto.setIcp(individual.getIcp());
        individualDto.setName(individual.getName());
        individualDto.setPlaceOfBirth(individual.getPlaceOfBirth());
        individualDto.setPatronymic(individual.getPatronymic());
        individualDto.setSurname(individual.getSurname());
        individualDto.setSurname(individual.getSurname());

        ContactMedium cont = individualRepo.findContactByIndivIcp(icp);
        String contactUuuid =  cont.getUuid(); // находим uuid  ContactMedium для этого юзера

        RFPassport passp = individualRepo.findPassportUuidByIndividIcp(icp);
        UUID passpUuid = passp.getUuid(); // находим uuid паспорта для этого юзера

        Documents docum = individualRepo.findDocumentUuidByIndividIcp(icp);
        String documentuid = docum.getUuid(); // находим uuid documents для этого юзера

        individualDto.setContactsUuid(contactUuuid);
        individualDto.setDocumentsUuid(documentuid);
        individualDto.setRfPassportUuid(passpUuid);

        return individualDto;
    }

    @Override //получить всех клиентов
    public List<Individual> getAll(){
       return individualRepo.findAll();
    }

    @Override //найти клиента (icp, name, uuid) по номеру телефона
    public IndividualDto getClientByPhoneNum(String value) {
        Individual individual = individualRepo.findByPhNum(value);
         IndividualDto individualDto = IndividualDto.builder().icp(individual.getIcp()).name(individual.getName()).
                uuid(individual.getUuid()).build();
        return individualDto;
    }

    @Transactional
    @Override  // редактировать клиента
    public void editClient(String icp, Date birthDate2, String countryOfBirth2, String fullName2, String gender2,
                          String name2, String patronymic2, String placeOfBirth2, String surname2) {
        Individual userFromDB = individualRepo.findAllFieldsByIcp(icp); //нашли пользователя в базе по icp

        ContactMedium cont = individualRepo.findContactByIndivIcp(icp);
        String contactUuuid =  cont.getUuid(); // находим uuid  первичный ключ для ContactMedium для этого юзера

        RFPassport passp = individualRepo.findPassportUuidByIndividIcp(icp);
        UUID passpUuid = passp.getUuid(); // находим uuid первичный ключ для паспорта для этого юзера

        Documents docum = individualRepo.findDocumentUuidByIndividIcp(icp);
        String documentuid = docum.getUuid(); // находим uuid первичный ключ для documents для этого юзера

        //переприсваиваем пользователю поля (значения из запроса в постмэне)
        userFromDB = Individual.builder().icp(icp).uuid(userFromDB.getUuid()).birthDate(birthDate2).
                countryOfBirth(countryOfBirth2).fullName(fullName2).gender(gender2).name(name2).
                patronymic(patronymic2).placeOfBirth(placeOfBirth2).surname(surname2).build();

        individualRepo.save(userFromDB); //сохраняем юзера (пока без uuid для контактов, документов и паспорта

        //пересохраняем uuid  таблиц "контакты , пасспорт, документы"  в таблице индивидуал через nativeQuery sql запрос
        // (средствами только spring data jpa у меня это сделать не получилось
        individualRepo.rewriteContactDocPassp(contactUuuid, documentuid, passpUuid, userFromDB.getUuid());
    }

    @Override //удалить пользователя по icp
    public void deleteIndivid(String icp) {
        Individual ind = individualRepo.findIndividualByIcp(icp).orElse(new Individual());
        individualRepo.deleteById(ind.getUuid());
    }

}
