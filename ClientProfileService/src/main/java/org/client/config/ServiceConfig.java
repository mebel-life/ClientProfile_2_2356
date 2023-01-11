package org.client.config;


import org.client.service.AddressService;
import org.client.service.IndividualService;
import org.client.service.impl.AddressServiceImpl;
import org.client.service.impl.IndividualServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ServiceConfig {

    @Bean
    public IndividualService individualService() {
        return new IndividualServiceImpl();
    }

    @Bean
    public AddressService addressService(IndividualService individualService) {
        return new AddressServiceImpl(individualService);
    }

}
