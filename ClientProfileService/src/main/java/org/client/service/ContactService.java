package org.client.service;

import org.client.common.entity.Contacts.PhoneNumber;

public interface ContactService {
    PhoneNumber getByValueUuid(String uuid);

}
