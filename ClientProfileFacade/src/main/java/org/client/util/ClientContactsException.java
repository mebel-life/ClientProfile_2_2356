package org.client.util;

public class ClientContactsException extends RuntimeException {

    private String message;

    public ClientContactsException(String message) {
        this.message = message;
    }
}
