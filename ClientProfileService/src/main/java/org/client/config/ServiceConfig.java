package org.client.config;


import org.client.repository.AddressRepository;
import org.client.repository.IndividualRepository;
import org.client.repository.WalletRepository;
import org.client.service.AddressService;
import org.client.service.IndividualService;
import org.client.service.impl.AddressServiceImpl;
import org.client.service.impl.IndividualServiceImpl;
import org.client.utils.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ServiceConfig {

    private final IndividualRepository individualRepository;
    private final AddressRepository addressRepository;
    private final MappingUtils mappingUtils;

    @Autowired
    public ServiceConfig(IndividualRepository individualRepository, AddressRepository addressRepository, MappingUtils mappingUtils) {
        this.individualRepository = individualRepository;
        this.addressRepository = addressRepository;
        this.mappingUtils = mappingUtils;
    }

    @Bean
    public IndividualService individualService() {
        return new IndividualServiceImpl(individualRepository, mappingUtils);
    }

    @Bean
    public AddressService addressService(IndividualService individualService) {
        return new AddressServiceImpl(individualService);
    }

}
