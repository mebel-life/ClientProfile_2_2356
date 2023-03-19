package org.client.service.impl;

import lombok.AllArgsConstructor;
import org.client.common.dto.IndividualDto;
import org.client.common.entity.*;
import org.client.repo.IndividualRepo;
import org.client.service.IndividualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
        Individual i = individualRepo.findAllFieldsByIcp(icp);

        IndividualDto individualDto = IndividualDto.builder().uuid(i.getUuid()).icp(i.getIcp()).name(i.getName()).
                surname(i.getSurname()).patronymic(i.getPatronymic()).fullName(i.getFullName()).gender(i.getGender()).
                placeOfBirth(i.getPlaceOfBirth()).countryOfBirth(i.getCountryOfBirth()).birthDate(i.getBirthDate()).documentsUuid(i.getDocuments().getUuid()).
                rfPassportUuid(i.getRfPassport().getUuid()).contactsUuid(i.getContacts().getUuid()).build();

        return individualDto;
    }

    @Override //получить всех клиентов
    public List<IndividualDto> getAll(){
        List<Individual> individualList= individualRepo.findAll();
        List<IndividualDto> individualDtoList = new ArrayList<>();

        //для каждого элемента individualList создадим объект типа IndividualDto, и присвоим ему значения из элемента individualList.
        // Потом  - поместим этот объект в лист AddressDto
        for(Individual i: individualList){
            IndividualDto individualDto = IndividualDto.builder().uuid(i.getUuid()).icp(i.getIcp()).name(i.getName()).
                    surname(i.getSurname()).patronymic(i.getPatronymic()).fullName(i.getFullName()).gender(i.getGender()).
                    placeOfBirth(i.getPlaceOfBirth()).countryOfBirth(i.getCountryOfBirth()).birthDate(i.getBirthDate()).documentsUuid(i.getDocuments().getUuid()).
                    rfPassportUuid(i.getRfPassport().getUuid()).contactsUuid(i.getContacts().getUuid()).build();
            individualDtoList.add(individualDto);
        }
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
        individualRepo.rewriteContactDocPassp(contactUuuid, documentuid, passpUuid, userFromDB.getUuid());
    }

    @Override //удалить пользователя по icp
    public void deleteIndivid(String icp) {
        Individual ind = individualRepo.findIndividualByIcp(icp).orElse(new Individual());
        individualRepo.deleteById(ind.getUuid());
    }

}
