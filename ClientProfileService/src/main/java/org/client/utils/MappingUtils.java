package org.client.utils;

import org.client.dto.AddressDto;
import org.client.dto.IndividualDto;
import org.client.dto.WalletDto;
import org.client.model.Address;
import org.client.model.Individual;
import org.client.model.Wallet;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class MappingUtils {

    public IndividualDto mapToIndividualDto(Individual individualEntity) {

        IndividualDto individualDto = new IndividualDto();
        individualDto.setIcp(String.valueOf(individualEntity.getIcp()));
        individualDto.setUuid(individualEntity.getUuid());
        individualDto.setName(individualEntity.getName());
        individualDto.setSurname(individualEntity.getSurname());
        individualDto.setPatronymic(individualEntity.getPatronymic());
        individualDto.setFullName(individualEntity.getFullname());
        individualDto.setGender(individualEntity.getGender());
        individualDto.setPlaceOfBirth(individualEntity.getPlaceofbirth());
        individualDto.setCountryOfBirth(individualEntity.getCountryofbirth());
        individualDto.setBirthDate(individualEntity.getBirthdate());

        WalletDto walletDto = mapToWalletDto(individualEntity.getWallet());
        individualDto.setWallet(Collections.singleton(walletDto));

        Set<Address> addressIndividual = individualEntity.getAddress();
        Set<AddressDto> addressDtoGeneral = new HashSet<AddressDto>();
        for(Address address : addressIndividual) {
            addressDtoGeneral.add(mapToAddressDto(address));
        }

        individualDto.setAddress(addressDtoGeneral);
        return individualDto;
    }

    public WalletDto mapToWalletDto(Wallet wallet) {
        WalletDto walletDto = new WalletDto();
        walletDto.setUuid(wallet.getUuid());
        walletDto.setIndividualUuid(wallet.getIndividual().getUuid());
        walletDto.setRubWallet(wallet.getRubWallet());
        walletDto.setDollarWallet(wallet.getDollarWallet());
        walletDto.setEuroWallet(wallet.getEuroWallet());
        return walletDto;
    }

    public AddressDto mapToAddressDto(Address address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setUuid(address.getUuid());
        addressDto.setIndividualUUID(address.getIndividual().getUuid());
        addressDto.setNotFormAddrName(address.getNotFormAddrName());
        addressDto.setAddressName(address.getAddressName());
        addressDto.setCountry(address.getCountry());
        addressDto.setZipCode(address.getZipCode());
        return addressDto;
    }

}
