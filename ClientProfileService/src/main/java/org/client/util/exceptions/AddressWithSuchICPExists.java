package org.client.util.exceptions;

public class AddressWithSuchICPExists extends RuntimeException {
    public AddressWithSuchICPExists(String message) {
        super(message);
    }
}
