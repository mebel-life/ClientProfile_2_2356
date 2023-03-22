package org.client.util;

import lombok.AllArgsConstructor;
import org.client.common.dto.IndividualDto;
import org.client.entity.Individual;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
@AllArgsConstructor
public class IndividualUtils {
    private static ModelMapper modelMapper;
    public static Individual convertToEntity(IndividualDto individualDto) {
        Individual individual = modelMapper.map(individualDto, Individual.class);
        return individual;
    }
}
