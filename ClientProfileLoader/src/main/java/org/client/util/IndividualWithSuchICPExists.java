package org.client.util;

import org.springframework.dao.DataIntegrityViolationException;

public class IndividualWithSuchICPExists extends DataIntegrityViolationException {
    public IndividualWithSuchICPExists(String msg) {
        super(msg);
    }
}