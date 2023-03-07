package org.client.util;

public class ClientInfoException extends RuntimeException {

    private String message;

    public ClientInfoException(String message) {
        this.message = message;
    }

    public ClientInfoException() {

    }
}
