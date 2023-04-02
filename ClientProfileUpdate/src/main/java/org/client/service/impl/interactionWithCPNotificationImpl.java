package org.client.service.impl;

import lombok.AllArgsConstructor;
import org.client.service.interactionWithCPNotification;
import org.springframework.web.client.RestTemplate;
@AllArgsConstructor
public class interactionWithCPNotificationImpl implements interactionWithCPNotification {
    private final RestTemplate restTemplate;
    private static final String CP_LOADER = "http://localhost:9092/api/clients";
    @Override
    public void generateCode() throws Exception {
        restTemplate.getForEntity(CP_LOADER,String.class);


    }
}
