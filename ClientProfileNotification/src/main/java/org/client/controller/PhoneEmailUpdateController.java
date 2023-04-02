package org.client.controller;


import com.github.sonus21.rqueue.core.RqueueEndpointManager;
import com.github.sonus21.rqueue.core.RqueueMessageEnqueuer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.client.dto.EmailUpdateDto;
import org.client.dto.PhoneNumberUpdateDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.client.util.Checker.checkEm;
import static org.client.util.Checker.checkPhoneNum;


@RequiredArgsConstructor()
@RequestMapping("/update/contacts")
@RestController
@Slf4j
public class PhoneEmailUpdateController {

    // генератор очередей

    @NonNull RqueueMessageEnqueuer rqueueMessageEnqueuer;

    // Очереди
    @Value("${update.request.queue.phone1}")
    private String updateRequestQueueName;
    @Value("${update.request.queue.email1}")
    private String updateRequestQueueName1;

    //Задержка 15 секунд
    @Value("${invoice.queue.delay}")
    private Long invoiceDelay;
    @NonNull RqueueEndpointManager rqueueEndpointManager;




    @PostMapping(value = "/phone")
    public void updatePhoneNumber(@RequestBody PhoneNumberUpdateDto phoneNumberUpdate) throws Exception {
        if (phoneNumberUpdate.getValue() != null) {
            phoneNumberUpdate.setVerification(false);
            log.info("updating  " + phoneNumberUpdate.getUuid() + "  with number  " + phoneNumberUpdate.getValue());
            checkPhoneNum(phoneNumberUpdate);
            try {


                rqueueMessageEnqueuer.enqueueIn(updateRequestQueueName, phoneNumberUpdate, invoiceDelay);


            } catch (Exception e) {


            }
        }


    }

    @PostMapping(value = "/email")
    public void updateEmail(@RequestBody EmailUpdateDto emailUpdateDto) throws Exception {
        if (emailUpdateDto.getValue() != null) {
            emailUpdateDto.setVerification(false);
            checkEm(emailUpdateDto);
            log.info("updating  " + emailUpdateDto.getUuid() + "  with email  " + emailUpdateDto.getValue());
            try {

                log.info("адрес не подтверждён");
                rqueueMessageEnqueuer.enqueueIn(updateRequestQueueName1, emailUpdateDto, invoiceDelay);

            } catch (Exception e) {
                log.warn("Во время выполнения произошла ошибка \n" + e);
            }
        }
    }


}