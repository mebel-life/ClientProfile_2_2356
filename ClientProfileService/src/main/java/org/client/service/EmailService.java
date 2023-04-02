package org.client.service;

import org.client.common.entity.Contacts.Email;


public interface EmailService {
    Email getByValueUuid(String uuid);
}
