package org.client;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.client.common.dto.IndividualDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@EnableKafka
@SpringBootApplication
public class App {

//    @KafkaListener(topics="load")
//    public void orderListener(ConsumerRecord<Long, IndividualDto> record){
//        System.out.println(record.key());
//        System.out.println(record.value());
//    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
