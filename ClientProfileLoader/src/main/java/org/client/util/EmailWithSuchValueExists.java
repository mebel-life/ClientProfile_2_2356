package org.client.util;

import org.springframework.dao.DataIntegrityViolationException;

public class EmailWithSuchValueExists extends DataIntegrityViolationException {
    public EmailWithSuchValueExists(String msg) {
        super(msg);
    }
}
