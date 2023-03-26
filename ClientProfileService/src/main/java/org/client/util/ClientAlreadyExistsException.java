package org.client.util;

public class ClientAlreadyExistsException extends RuntimeException {

    public ClientAlreadyExistsException (String message) {
        super(message);
    }
}
