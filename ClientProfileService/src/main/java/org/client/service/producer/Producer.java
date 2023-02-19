package org.client.service.producer;

import org.client.dto.AddressDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    private final KafkaTemplate<String, AddressDto> kafkaTemplate;

    @Autowired
    public Producer(KafkaTemplate<String, AddressDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(AddressDto addressDto) {
        if (addressDto != null) {
            Message<AddressDto> message = MessageBuilder
                    .withPayload(addressDto)
                    .setHeader(KafkaHeaders.TOPIC, "load")
                    .build();

            kafkaTemplate.send(message);

            LOGGER.info("The message has been sent");
        } else {
            LOGGER.warn("Object is null");
        }
    }
}
