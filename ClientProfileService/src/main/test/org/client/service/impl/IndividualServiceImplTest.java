package org.client.service.impl;

import org.client.common.dto.IndividualDto;
import org.client.common.dto.RFPassportDto;
import org.client.entity.Individual;
import org.client.service.IndividualService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@Component
class IndividualServiceImplTest {
    @Autowired
    IndividualService individualService;
    private ModelMapper modelMapper = new ModelMapper();
    public Individual convertToEntity(IndividualDto individualDto) {
        Individual individual = modelMapper.map(individualDto, Individual.class);
        return individual;
    }
    @Test
    void isClientArchived() {
        IndividualDto individualDto = IndividualDto.builder().name("dsada").passport(List.of(RFPassportDto.builder().series("1231").number("123123").build())).build();
        System.out.println(convertToEntity(individualDto));
    }
}