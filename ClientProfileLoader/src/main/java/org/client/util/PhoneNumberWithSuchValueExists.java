package org.client.util;


import org.springframework.dao.DataIntegrityViolationException;

public class PhoneNumberWithSuchValueExists extends DataIntegrityViolationException {

    public PhoneNumberWithSuchValueExists(String msg) {
        super(msg);
    }
}
