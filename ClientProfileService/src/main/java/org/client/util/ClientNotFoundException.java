package org.client.util;

public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException (String message) {
        super(message);
    }
}
