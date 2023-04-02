package org.client.service;

import com.github.sonus21.rqueue.annotation.RqueueListener;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.client.dto.EmailUpdateDto;
import org.client.dto.Invoice;
import org.client.dto.PhoneNumberUpdateDto;

import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor

public class MessageListener {

    private final InteractionWithUserUpdateService interactionWithUserUpdateService;

    @RqueueListener(value = "${update.request.queue.phone1}")
    public void UpdatePhoneNumber(PhoneNumberUpdateDto phoneNumberUpdate, String icp) {
        try {

            interactionWithUserUpdateService.updatePhoneNumber(phoneNumberUpdate, icp);
        } catch (Exception e) {
            log.warn("Something WENT WRONG " + e);
        }

    }

    @RqueueListener(value = "${invoice.queue.delay}")
    public void generateInvoice(Invoice invoice) {
        log.info("Invoice {}", invoice);
    }

    @RqueueListener("${update.request.queue.email1}")
    public void UpdateEmail(EmailUpdateDto emailUpdateDto, String uuid) {
        try {

            interactionWithUserUpdateService.updateEmail(emailUpdateDto, uuid);
        } catch (Exception e) {
            log.warn("Something WENT WRONG " + e);
        }

    }


}
